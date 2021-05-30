package com.rgr.spring.datajpa.model;

import javax.persistence.*;

@Entity
@Table(name = "hobbyclass")
public class Hobbyclass {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "Name")
	private String title;

	@Column(name = "ID_Teacher")
	private int id_teacher;

	@Column(name = "ID_Category")
	private int id_category;

	public Hobbyclass() {

	}

	public Hobbyclass(String title, int id_teachertmp, int id_categorytmp) {
		this.title = title;
		this.id_teacher = id_teachertmp;
		this.id_category = id_categorytmp;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId_teacher() {
		return id_teacher;
	}

	public void setId_teacher(int id_teacher) { this.id_teacher = id_teacher;
	}

	public int getId_category() {
		return id_category;
	}

	public void setId_category(int id_category) {
		this.id_category = id_category;
	}

}
