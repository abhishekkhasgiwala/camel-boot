package com.example.pojo;

import java.io.Serializable;

public class Student implements Serializable {

	private long id;
	private String name;
	private String subject;

	public Student() {
	}

	public Student(long id, String name, String subject) {
		this.id = id;
		this.name = name;
		this.subject = subject;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", subject=" + subject + "]";
	}
}
