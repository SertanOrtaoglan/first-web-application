package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	
	private static List<Todo> todos = new ArrayList<>();
	
	private static int todosCount = 0;
	
	static {
		todos.add(new Todo(++todosCount, "in28minutes", "Get AWS Certified", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount, "in28minutes", "Learn DevOps", LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todosCount, "in28minutes", "Learn Full Stack Development", LocalDate.now().plusYears(3), false));
	}
	
	public List<Todo> findByUsername(String username) {
		return todos;
	}
	
	
	public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++todosCount, username, description, targetDate, done);
		todos.add(todo);
	}
	
	
	public void deleteById(int id) {
		//todo'nun ne zaman kaldırılacağı koşulunu 'predicate' ile tanımlarız. Koşulumuz ise ==> todo.getId() == id olacaktır.
		//predicate'i lamba function ile tanımlarız. Lambda ifadesini "todo -> todo.getId() == id" şeklinde yazmamız gerekir.
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
		
	}


	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}


	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId());   //Update yapmak için ilk olarak listeden mevcut todo'yu siliyoruz.(id'sine göre)
		todos.add(todo);            //Daha sonra listemize yeni bir todo ekliyoruz.
	}
	
	

}

