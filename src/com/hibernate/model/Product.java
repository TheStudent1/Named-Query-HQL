package com.hibernate.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "product")
@NamedQueries({ @NamedQuery(name = "getAll", query = "from Product"),
		@NamedQuery(name = "getSup12A", query = "from Product where price>:price and name like :name"),
		@NamedQuery(name = "getPriceBetween100&1000", query = "from Product where price between :pr1 and :pr2"),
		@NamedQuery(name = "sumPricePerCat", query = "select SUM(price),category from Product group by category"),
		@NamedQuery(name = "getbyCat", query = "from Product where category =:category"),
		@NamedQuery(name = "deleteByFirstLetter", query = "delete from Product where name=:name"),
		@NamedQuery(name = "modifyPriceByCat", query = "UPDATE Product set price = price*0.1 + price where category=:category") })
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private double price;
	@Column
	private String category;

	public String toString() {
		return "Product: " + this.getId() + " Name:  " + this.getName() + " Price: " + this.getPrice() + " category: "
				+ this.getCategory();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
