/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.payroll;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;


import lombok.Data;

/**
 * @author Aliye Kurumlu
 */
// tag::code[]
@Data
@Entity
public class BlogItem {

	private @Id @GeneratedValue Long id;
	@Column(length=50) 
	@NotNull
	private String title;
	private Date createdOn;
	//private Object  image;
	@Column(length=1000) 
	@NotNull
	private String description;
	@OneToMany(cascade=CascadeType.ALL ,targetEntity=CategoryItem.class )
    private List<CategoryItem> categories;


	private BlogItem() {}

	public BlogItem(String title, String description, List<CategoryItem> categories) {
		this.title = title;
		this.createdOn = new Date();
		this.description = description;
		this.categories = categories; 
	}
}
// end::code[]