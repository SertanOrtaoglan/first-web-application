package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

//Database(MySQL) 
//Biz ilk olarak 'static' bir 'List of Todos' oluşturacağız daha sonra gerçek bir veritabanı kullanımına geçeceğiz.
//Static List of todos => Database(H2, MySQL)

//JPA; bean'imizi(Todo) veritabanına(H2) eşlememizi sağlar. Bu işlemi '@Entity' annotation'ı ile yaparız. Yani bizim için H2 veritabanında 'Todo' adında bir tablo oluşturulur.

@Entity
public class Todo {

	@Id
	@GeneratedValue
	private int id;
	
	private String username;
	
	@Size(min=10, message="Enter atleast 10 characters") 
	private String description;
	
	private LocalDate targetDate;
	
	private boolean done;

	
	public Todo() {
		
	}
	
	public Todo(int id, String username, String description, LocalDate targetDate, boolean done) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this.done = done;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", username=" + username + ", description=" + description + ", targetDate="
				+ targetDate + ", done=" + done + "]";
	}
	
	
	
	
	

}
