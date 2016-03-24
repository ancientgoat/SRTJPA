package com.premierinc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Table(name="parent_table") // , schema="dumbschema")
public class ParentEntity {

	@Id
	private Long id;

	@Column(name="name")
	private String name;

	@Column(name="name_02")
	private String name02;

	@Column(name="name_03")
	private String name03;

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

	public String getName02() {
		return name02;
	}

	public ParentEntity setName02(String name02) {
		this.name02 = name02;
		return this;
	}

	public String getName03() {
		return name03;
	}

	public ParentEntity setName03(String name03) {
		this.name03 = name03;
		return this;
	}
}
