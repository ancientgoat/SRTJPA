package com.premierinc.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Table(name = "child_table") // , schema="dumbschema")
public class ChildEntity {

	@Id
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "name_given")
	private String nameGiven;

	@Column(name = "name_family")
	private String nameFamily;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private ParentEntity parent = null;

	public Long getId() {
		return id;
	}

	public ChildEntity setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public ChildEntity setName(String name) {
		this.name = name;
		return this;
	}

	public String getNameGiven() {
		return nameGiven;
	}

	public ChildEntity setNameGiven(String nameGiven) {
		this.nameGiven = nameGiven;
		return this;
	}

	public String getNameFamily() {
		return nameFamily;
	}

	public ChildEntity setNameFamily(String nameFamily) {
		this.nameFamily = nameFamily;
		return this;
	}

	public ParentEntity getParent() {
		return parent;
	}

	public ChildEntity setParent(ParentEntity parent) {
		this.parent = parent;
		return this;
	}

	public boolean haveParent(){
		return null != this.parent;
	}

	public String getFullName() {
		return String.format("%s-%s-%s", getName(), getNameGiven(), getNameFamily());
	}
}

