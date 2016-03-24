package com.premierinc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Table(name = "parent_table") // , schema="dumbschema")
public class ParentEntity {

	@Id
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "name_given")
	private String nameGiven;

	@Column(name = "name_family")
	private String nameFamily;

	public Long getId() {
		return id;
	}

	public ParentEntity setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public ParentEntity setName(String name) {
		this.name = name;
		return this;
	}

	public String getNameGiven() {
		return nameGiven;
	}

	public ParentEntity setNameGiven(String inNameGiven) {
		this.nameGiven = inNameGiven;
		return this;
	}

	public String getNameFamily() {
		return nameFamily;
	}

	public ParentEntity setNameFamily(String inNameFamily) {
		this.nameFamily = inNameFamily;
		return this;
	}

	public String getFullName() {
		return String.format("%s-%s-%s", getName(), getNameGiven(), getNameFamily());
	}
}
