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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Greg Turnquist / Aliye Kurumlu
 */
// tag::code[]
@Component
public class DatabaseLoader implements CommandLineRunner {

	private final BlogItemRepository repository;
	private final CategoryItemRepository catItemRepository;

	@Autowired
	public DatabaseLoader( BlogItemRepository repository, CategoryItemRepository catItemRepository) {
		this.repository = repository;
		this.catItemRepository = catItemRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		List<CategoryItem> categories = new ArrayList<CategoryItem>();
		categories.add (new CategoryItem ("plant"));
		categories.add (new CategoryItem ("Angiosperms"));
		categories.add (new CategoryItem ("Monocots"));
		categories.add (new CategoryItem ("Asparagales"));
		categories.add (new CategoryItem ("Asphodelaceae"));
		categories.add (new CategoryItem ("Asphodeloideae"));
		categories.add (new CategoryItem ("Aloe"));
		this.repository.save(new BlogItem("Aloe", "Aloe vera is a plant species of the genus Aloe. It grows wild in tropical climates around the world and is cultivated for agricultural and medicinal uses. Aloe is also used for decorative purposes and grows successfully indoors as a potted plant. It is found in many consumer products including beverages, skin lotion, cosmetics, or ointments for minor burns and sunburns. There is little scientific evidence of the effectiveness or safety of Aloe vera extracts for either cosmetic or medicinal purposes. Studies finding positive evidence are frequently contradicted by other studies.",categories));
		
		categories = new ArrayList<CategoryItem>();
		categories.add (new CategoryItem ("plant"));
		categories.add (new CategoryItem ("Angiosperms"));
		categories.add (new CategoryItem ("Eudicots"));
		categories.add (new CategoryItem ("Rosids"));
		categories.add (new CategoryItem ("Fabales"));
		categories.add (new CategoryItem ("Fabaceae"));
		categories.add (new CategoryItem ("Faboideae"));
		categories.add (new CategoryItem ("Trifolium"));
		categories.add (new CategoryItem ("T. pratense"));
		this.repository.save(new BlogItem("Red Clover", "Trifolium pratense, the red clover, is a herbaceous species of flowering plant in the bean family Fabaceae, native to Europe, Western Asia and northwest Africa, but planted and naturalised in many other regions. It is a herbaceous, short-lived perennial plant, variable in size, growing to 20–80 cm tall. The leaves are alternate, trifoliate (with three leaflets), each leaflet 15–30 mm long and 8–15 mm broad, green with a characteristic pale crescent in the outer half of the leaf; the petiole is 1–4 cm long, with two basal stipules that are abruptly narrowed to a bristle-like point. The flowers are dark pink with a paler base, 12–15 mm long, produced in a dense inflorescence, and are mostly visited by bumblebees.",categories));
		
		categories = new ArrayList<CategoryItem>();
		categories.add (new CategoryItem ("plant"));
		categories.add (new CategoryItem ("Angiosperms"));
		categories.add (new CategoryItem ("Eudicots"));
		categories.add (new CategoryItem ("Rosids"));
		categories.add (new CategoryItem ("Rosales"));
		categories.add (new CategoryItem ("Rosaceae"));
		categories.add (new CategoryItem ("Filipendula"));
		categories.add (new CategoryItem ("F. ulmaria"));
		this.repository.save(new BlogItem("Meadowsweet", "Filipendula ulmaria, commonly known as meadowsweet or mead wort, is a perennial herb in the family Rosaceae that grows in damp meadows. It is native throughout most of Europe and Western Asia (Near east and Middle east). It has been introduced and naturalised in North America. Meadowsweet has also been referred to as queen of the meadow, pride of the meadow, meadow-wort, meadow queen, lady of the meadow, dollof, meadsweet, and bridewort.",categories));

		categories = new ArrayList<CategoryItem>();
		categories.add (new CategoryItem ("plant"));
		categories.add (new CategoryItem ("Angiosperms"));
		categories.add (new CategoryItem ("Magnoliids"));
		categories.add (new CategoryItem ("Laurales"));
		categories.add (new CategoryItem ("Lauraceae"));
		categories.add (new CategoryItem ("Laurus"));
		categories.add (new CategoryItem ("L. nobilis"));
		this.repository.save(new BlogItem("Bay Laurel", "Bay Laurel, Laurus nobilis, is an aromatic evergreen tree or large shrub with green, glabrous leaves, in the flowering plant family Lauraceae. It is native to the Mediterranean region and is used as bay leaf for seasoning in cooking. Its common names include bay laurel, sweet bay, bay (esp. United Kingdom),[1]:84 true laurel, Grecian laurel,[2] laurel tree or simply laurel. Laurus nobilis figures prominently in classical Greek, Roman, and Biblical culture. Worldwide, many other kinds of plants in diverse families are also called 'bay' or 'laurel', generally due to similarity of foliage or aroma to Laurus nobilis, and the full name is used for the California bay laurel (Umbellularia), also in the family Lauraceae.",categories));

		categories = new ArrayList<CategoryItem>();
		categories.add (new CategoryItem ("plant"));
		categories.add (new CategoryItem ("Angiosperms"));
		categories.add (new CategoryItem ("Eudicots"));
		categories.add (new CategoryItem ("Caryophyllales"));
		categories.add (new CategoryItem ("Amaranthaceae"));
		categories.add (new CategoryItem ("Chenopodium"));
		categories.add (new CategoryItem ("C. album"));
		this.repository.save(new BlogItem("Lamb's Quarters", "Lamb's Quarters, Chenopodium album,  is a fast-growing weedy annual plant in the genus Chenopodium. Though cultivated in some regions, the plant is elsewhere considered a weed. Common names include lamb's quarters, melde, goosefoot and fat-hen, though the latter two are also applied to other species of the genus Chenopodium, for which reason it is often distinguished as white goosefoot. It is sometimes also called pigweed. However, pigweed is also a name for a few weeds in the family Amaranthaceae; it is for example used for the redroot pigweed (Amaranthus retroflexus). Chenopodium album is extensively cultivated and consumed in Northern India as a food crop, and in English texts it may be called by its Hindi name bathua or bathuwa (बथुआ) (Marathi:चाकवत).[6] It is called pappukura in Telugu, paruppukkirai in Tamil, kaduoma in Kannada, vastuccira in Malayalam, and chakvit in Konkani.",categories));

		categories = new ArrayList<CategoryItem>();
		categories.add (new CategoryItem ("plant"));
		categories.add (new CategoryItem ("Angiosperms"));
		categories.add (new CategoryItem ("Eudicots"));
		categories.add (new CategoryItem ("Asterids"));
		categories.add (new CategoryItem ("Apiales"));
		categories.add (new CategoryItem ("Apiaceae"));
		categories.add (new CategoryItem ("Selineae"));
		categories.add (new CategoryItem ("Cnidium"));
		this.repository.save(new BlogItem("Cnidium", "Cnidium is a genus of flowering plant in the Apiaceae, native to Eurasia, Africa and North America. It has 4 or 5 species. Cnidium monnieri (L.) Cuss. is one of the most widely used traditional herbal medicines and its fruits have been used to treat a variety of diseases in China, Vietnam, and Japan. As of this writing, 350 compounds have been isolated and identified from C. monnieri, including the main active constituent, coumarins. In vitro and in vivo studies suggest that osthole and other coumarin compounds possess wide range of pharmacological properties for the treatment of female genitals, male impotence, frigidity, skin-related diseases, and exhibit strong antipruritic, anti-allergic, antidermatophytic, antibacterial, antifungal, anti-osteoporotic effects. Although coumarins have been identified as the main active constituents responsible for the observed pharmacological effects, the molecular mechanisms of their actions are still unknown.",categories));

		categories = new ArrayList<CategoryItem>();
		categories.add (new CategoryItem ("plant"));
		categories.add (new CategoryItem ("Angiosperms"));
		categories.add (new CategoryItem ("Monocots"));
		categories.add (new CategoryItem ("Commelinids"));
		categories.add (new CategoryItem ("Poales"));
		categories.add (new CategoryItem ("Poaceae"));
		categories.add (new CategoryItem ("Panicoideae"));
		categories.add (new CategoryItem ("Andropogoneae"));
		categories.add (new CategoryItem ("Andropogoninae"));
		categories.add (new CategoryItem ("Cymbopogon"));
		this.repository.save(new BlogItem("Lemon-grass", "Cymbopogon, better known as lemongrass, is a genus of Asian, African, Australian, and tropical island plants in the grass family. Some species (particularly Cymbopogon citratus) are commonly cultivated as culinary and medicinal herbs because of their scent, resembling that of lemons (Citrus limon). Common names include lemon grass, lemongrass, barbed wire grass, silky heads, citronella grass, cha de Dartigalongue, fever grass, tanglad, hierba Luisa, or gavati chahapati, amongst many others.",categories));

		//filling data for the Categories table
		this.catItemRepository.save(new CategoryItem ("Plant"));
		this.catItemRepository.save(new CategoryItem ("Angiosperms"));
		this.catItemRepository.save(new CategoryItem ("Monocots"));
		this.catItemRepository.save(new CategoryItem ("Commelinids"));
		this.catItemRepository.save(new CategoryItem ("Poales"));
		this.catItemRepository.save(new CategoryItem ("Poaceae"));
		this.catItemRepository.save(new CategoryItem ("Panicoideae"));
		this.catItemRepository.save(new CategoryItem ("Andropogoneae"));
		this.catItemRepository.save(new CategoryItem ("Andropogoninae"));
		this.catItemRepository.save(new CategoryItem ("Cymbopogon"));
		this.catItemRepository.save(new CategoryItem ("Cnidium"));
		this.catItemRepository.save(new CategoryItem ("Selineae"));
		this.catItemRepository.save(new CategoryItem ("Apiaceae"));
		this.catItemRepository.save(new CategoryItem ("Apiales"));
		this.catItemRepository.save(new CategoryItem ("Eudicots"));
		this.catItemRepository.save(new CategoryItem ("Chenopodium"));
		this.catItemRepository.save(new CategoryItem ("C. album"));
		this.catItemRepository.save(new CategoryItem ("Caryophyllales"));
		this.catItemRepository.save(new CategoryItem ("L. nobilis"));
		this.catItemRepository.save(new CategoryItem ("F. ulmaria"));
		this.catItemRepository.save(new CategoryItem ("Filipendula"));
		this.catItemRepository.save(new CategoryItem ("T. pratense"));
		this.catItemRepository.save(new CategoryItem ("Aloe"));
	}
}
// end::code[]