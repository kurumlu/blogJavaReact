'use strict';

const React = require('react');
const ReactDOM = require('react-dom');

//const BrowserRouter = require('react-router-dom');
//const Router = require('react-router-dom');
//const Route = require('react-router-dom');
//const Link = require('react-router-dom');

const client = require('./client');
const follow = require('./follow'); // function to hop multiple links by "rel"

const _ = require('lodash');
//const Select = require('react-select');

const root = '/api';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import Select from 'react-select';
//import '../../..node_modules/react-select/dist/react-select.css';

class App extends React.Component {
	render(){
		return (
			<Router>
				<div>
					<Route exact path="/" component={LandingPage}/>
					<Route path="/addBlogItem" component={AddBlogItem}/> 
				</div>
			</Router>
		)
	}
}

class LandingPage extends React.Component {

	constructor(props) {
		super(props);
		this.state = {blogItems: [], attributes: [], pageSize: 2, links: {}};
		this.updatePageSize = this.updatePageSize.bind(this);
		this.onCreate = this.onCreate.bind(this);
		this.onDelete = this.onDelete.bind(this);
		this.onNavigate = this.onNavigate.bind(this);
	}

	// tag::follow-2[]
	loadFromServer(pageSize) {
		follow(client, root, [
			{rel: 'blogItems', params: {size: pageSize}}]
		).then(blogItemCollection => {
			return client({
				method: 'GET',
				path: blogItemCollection.entity._links.profile.href,
				headers: {'Accept': 'application/schema+json'}
			}).then(schema => {
				this.schema = schema.entity;
				return blogItemCollection;
			});
		}).done(blogItemCollection => {
			this.setState({
				blogItems: blogItemCollection.entity._embedded.blogItems,
				attributes: Object.keys(this.schema.properties),
				pageSize: pageSize,
				links: blogItemCollection.entity._links});
		});
	}
	// end::follow-2[]

	// tag::create[]
	onCreate(newBlogItem) {
		follow(client, root, ['blogItems']).then(blogItemCollection => {
			return client({
				method: 'POST',
				path: blogItemCollection.entity._links.self.href,
				entity: newBlogItem,
				headers: {'Content-Type': 'application/json'}
			})
		}).then(response => {
			return follow(client, root, [
				{rel: 'blogItems', params: {'size': this.state.pageSize}}]);
		}).done(response => {
			if (typeof response.entity._links.last != "undefined") {
				this.onNavigate(response.entity._links.last.href);
			} else {
				this.onNavigate(response.entity._links.self.href);
			}
		});
	}
	// end::create[]

	// tag::delete[]
	onDelete(blogItem) {
		client({method: 'DELETE', path: blogItem._links.self.href}).done(response => {
			this.loadFromServer(this.state.pageSize);
		});
	}
	// end::delete[]

	// tag::navigate[]
	onNavigate(navUri) {
		client({method: 'GET', path: navUri}).done(blogItemCollection => {
			this.setState({
				blogItems: blogItemCollection.entity._embedded.blogItems,
				attributes: this.state.attributes,
				pageSize: this.state.pageSize,
				links: blogItemCollection.entity._links
			});
		});
	}
	// end::navigate[]

	// tag::update-page-size[]
	updatePageSize(pageSize) {
		if (pageSize !== this.state.pageSize) {
			this.loadFromServer(pageSize);
		}
	}
	// end::update-page-size[]

	// tag::follow-1[]
	componentDidMount() {
		this.loadFromServer(this.state.pageSize);
	}
	// end::follow-1[]

	render() {
		return (
			<div>
				<AddBlogItem attributes={this.state.attributes} onCreate={this.onCreate}/>
				<BlogItemList blogItems={this.state.blogItems}
							links={this.state.links}
							pageSize={this.state.pageSize}
							onNavigate={this.onNavigate}
							onDelete={this.onDelete}
							updatePageSize={this.updatePageSize}/>
			</div>
		)
	}
}

// tag::add blog item[]
class AddBlogItem extends React.Component {

	constructor(props) {
		super(props);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleSubmit(e) {
		e.preventDefault();
		var newBlogItem = {};
		this.props.attributes.forEach(attribute => {
			newBlogItem[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
		});
		this.props.onCreate(newBlogItem);

		// clear out the dialog's inputs
		this.props.attributes.forEach(attribute => {
			ReactDOM.findDOMNode(this.refs[attribute]).value = '';
		});

		// Navigate away from the dialog to hide it.
		window.location = "#";
	}


	render() {
		//var inputs = this.props.attributes.map(attribute =>
		//	<p key={attribute}>
		//		<input type="text" placeholder={attribute} ref={attribute} className="field" />
		//	</p>
		//);

		console.log("elements...");
		var elements = [];

		_.each(this.props.attributes, (attribute)=> {
			if (attribute==='title') {
				elements.push(			
					<p key={attribute}>
						<input type="text" placeholder={attribute} ref={attribute} className="field" />
					</p>);
			}
			if (attribute==='description') {
				elements.push(			
					<p key={attribute}>
						<input type="textarea" rows="5"  cols="150" placeholder={attribute} ref={attribute} className="field" />
					</p>);
			}
			var options = [
				{ value: 'one', label: 'One' },
				{ value: 'two', label: 'Two' }
			  ];
			//logChange(val) {
			//console.log("Selected: " + JSON.stringify(val));
			//}
			if (attribute==='categories') {
				elements.push(			
					<p key={attribute}>	
						<Select name={attribute}  placeholder={attribute} ref={attribute} options={options} multi={true} placeholder="Select a category" />
					</p>);
			}
		});
		console.log(elements);

		return (
			//<Link to="/addBlogItem">
			<div>
				<button className="btn btn-default" >
					<a href="#createBlogItem">Add new item</a>
				</button>

				<div id="createBlogItem" className="modalDialog">
					<div>
						<a href="#" title="Close" className="close">X</a>
						<h2>Create new blog item</h2>
						<form>
							{elements}
							<button className="btn btn-default" onClick={this.handleSubmit}>Add</button>
						</form>
					</div>
				</div>
			</div>
			//</Link>
		)
	}

}
// end::add blog item

class BlogItemList extends React.Component {

	constructor(props) {
		super(props);
		this.handleNavFirst = this.handleNavFirst.bind(this);
		this.handleNavPrev = this.handleNavPrev.bind(this);
		this.handleNavNext = this.handleNavNext.bind(this);
		this.handleNavLast = this.handleNavLast.bind(this);
		this.handleInput = this.handleInput.bind(this);
	}

	// tag::handle-page-size-updates[]
	handleInput(e) {
		e.preventDefault();
		var pageSize = ReactDOM.findDOMNode(this.refs.pageSize).value;
		if (/^[0-9]+$/.test(pageSize)) {
			this.props.updatePageSize(pageSize);
		} else {
			ReactDOM.findDOMNode(this.refs.pageSize).value =
				pageSize.substring(0, pageSize.length - 1);
		}
	}
	// end::handle-page-size-updates[]

	// tag::handle-nav[]
	handleNavFirst(e){
		e.preventDefault();
		this.props.onNavigate(this.props.links.first.href);
	}

	handleNavPrev(e) {
		e.preventDefault();
		this.props.onNavigate(this.props.links.prev.href);
	}

	handleNavNext(e) {
		e.preventDefault();
		this.props.onNavigate(this.props.links.next.href);
	}

	handleNavLast(e) {
		e.preventDefault();
		this.props.onNavigate(this.props.links.last.href);
	}
	// end::handle-nav[]

	// tag::blogItem-list-render[]
	render() {
		var blogItems = this.props.blogItems.map(blogItem =>
			<BlogItem key={blogItem._links.self.href} blogItem={blogItem} onDelete={this.props.onDelete}/>
		);

		var navLinks = [];
		if ("first" in this.props.links) {
			navLinks.push(<button key="first" onClick={this.handleNavFirst}>&lt;&lt;</button>);
		}
		if ("prev" in this.props.links) {
			navLinks.push(<button key="prev" onClick={this.handleNavPrev}>&lt;</button>);
		}
		if ("next" in this.props.links) {
			navLinks.push(<button key="next" onClick={this.handleNavNext}>&gt;</button>);
		}
		if ("last" in this.props.links) {
			navLinks.push(<button key="last" onClick={this.handleNavLast}>&gt;&gt;</button>);
		}

		return (
			<div className = "panel panel-default">
				<div className = "panel-body">
					<input ref="pageSize" defaultValue={this.props.pageSize} onInput={this.handleInput}/>
					<table className = "table table-striped">
						<tbody>
							{blogItems}
						</tbody>
					</table>
					<div>
						{navLinks}
					</div>
				</div>
			</div>
		)
	}
	// end::blogItem-list-render[]
}

// tag::blogItem[]
class BlogItem extends React.Component {

	constructor(props) {
		super(props);
		this.handleDelete = this.handleDelete.bind(this);
	}

	handleDelete() {
		this.props.onDelete(this.props.blogItem);
	}

	render() {
		var creationDate = this.props.blogItem.createdOn.toString();
		var categories = _.each(this.props.blogItem.categories,(category)=> categories=`${category.name}, ${categories}`);
		return (
			<tr>
				<td>
					<div className = "container">
						<div className="row">
							<div className="col-md-8">
								<h4>{this.props.blogItem.title}</h4>
							</div>
						</div>
						<div className="row">
							<div className="col-md-4 create-text" >
								<small><strong><em>createdOn:</em> {creationDate}</strong></small>
							</div>
						</div>
						<div className="row">
							<div className="col-md-12 categories-text" >
								<small><strong><em>categories:</em> {categories}</strong></small>
							</div>
						</div>
						<div className="row">
							<div className="col-md-12">
								<em>{this.props.blogItem.description}</em>
							</div>
						</div>
						<div className="row">
							<div className="col-md-12">
								<button onClick={this.handleDelete}>Delete</button>
							</div>
						</div>
					</div>
				</td>
			</tr>
		)
	}
}
// end::blogItem[]

ReactDOM.render(
	<App />,
	document.getElementById('react')
)

