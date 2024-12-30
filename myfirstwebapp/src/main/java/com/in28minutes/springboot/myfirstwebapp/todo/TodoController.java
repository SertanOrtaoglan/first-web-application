package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


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
		@RequestMapping(value="add-todo", method = RequestMethod.GET)
		public String showNewTodoPage() {
			return "todo";
		}
	
	
	//Url: /add-todo
		@RequestMapping(value="add-todo", method = RequestMethod.POST)
		public String addNewTodo(@RequestParam String description, ModelMap model) {
			
			String username = (String)model.get("name");   //'model' kullanarak login olan kullanıcının name'ini alıyoruz. Ve String dönüşümü yaparak aşağıda parametre olarak geçiyoruz.
			todoService.addTodo(username, description, LocalDate.now().plusYears(1), false);
			
			return "redirect:list-todos";
		}
		
		
		
		
		
		
		

}
