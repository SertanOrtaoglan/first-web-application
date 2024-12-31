package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;


@Controller
@SessionAttributes("name")
public class TodoController {

	private TodoService todoService;
	
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}


	//Url: /list-todos
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		List<Todo> todos = todoService.findByUsername("in28minutes");
		model.addAttribute("todos", todos);
		
		return "listTodos";
	} 
	
	
	//GET,POST(for 'add-todo' url)
	//Url: /add-todo
	//Bean'den(Todo) form'a bağlama işlemi bu methodla gerçekleşir.(İki yönlü bağlamadan birincisi)
	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
			
		String username = (String)model.get("name");
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);   //Burada tırnak içinde yazılan "todo" ifadesi todo.jsp içerindeki modelAttribute="todo" dan gelir. Yani ikisi birbiriyle eşleşiyor.
		return "todo";
	}

	
	//Url: /add-todo
	//Form'dan Bean(Todo)'e bağlama işlemi bu methodla gerçekleşir.((İki yönlü bağlamadan ikincisi)
	@RequestMapping(value="add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		if(result.hasErrors()) {
			return "todo";
			
		}
			
		String username = (String)model.get("name");
		todoService.addTodo(username, todo.getDescription(), LocalDate.now().plusYears(1), false);
			
		return "redirect:list-todos";
	}

	
	//Url: /delete-todo
		@RequestMapping("delete-todo")
		public String deleteTodo(@RequestParam int id) {
			//delete todo
			todoService.deleteById(id);
			
			return "redirect:list-todos";
		}
	
	
		
	
	
}

