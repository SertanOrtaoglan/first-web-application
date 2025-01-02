package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	
	//Bu yöntem ile artık model'den "name" session'ını almamıza gerek yoktur. Bunun yerine login olan kullanıcının username'ini SecurityContextHolder'dan alırız. Ve aşağıda hangi yöntemlerde kullanılacaksa oraya göndeririz.('username'i)
	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	} 

	
	//Url: /list-todos
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		String username = getLoggedInUsername(model);
		List<Todo> todos = todoService.findByUsername(username);    //Burada daha önceden sabit bir şekilde yazarak verdiğimiz username'i[findByUsername("in28minutes") şeklinde] artık SecurityContextHolder'dan(yani login olurken girilen username'i) alıp verebiliriz. Bunun için yukarıda ' getLoggedInUsername()' adında bir yöntem oluşturduk. 
		model.addAttribute("todos", todos);
		
		return "listTodos";
	}

	
	//GET,POST(for 'add-todo' url)
	//Url: /add-todo
	//Bean'den(Todo) form'a bağlama işlemi bu methodla gerçekleşir.(İki yönlü bağlamadan birincisi)
	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
			
		String username = getLoggedInUsername(model);
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);   //Burada tırnak içinde yazılan "todo" ifadesi todo.jsp içerindeki modelAttribute="todo" dan gelir. Yani ikisi birbiriyle eşleşiyor.
		return "todo";
	}

	
	//Url: /add-todo
	//Form'dan Bean(Todo)'e bağlama işlemi bu methodla gerçekleşir.(İki yönlü bağlamadan ikincisi)
	@RequestMapping(value="add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		if(result.hasErrors()) {
			return "todo";
			
		}
			
		String username = getLoggedInUsername(model);
		todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
			
		return "redirect:list-todos";
	}
	
	
	//Url: /delete-todo
		@RequestMapping("delete-todo")
		public String deleteTodo(@RequestParam int id) {
			
			todoService.deleteById(id);
			return "redirect:list-todos";
		}
	
	
	//Url: /update-todo
		@RequestMapping(value="update-todo", method = RequestMethod.GET)
		public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
			
			Todo todo = todoService.findById(id);
			model.addAttribute("todo", todo);      //Burada tırnak içinde yazılan "todo" ifadesi todo.jsp içerindeki modelAttribute="todo" dan gelir. Yani ikisi birbiriyle eşleşiyor.
					
			return "todo";
		}
	
	
	//Url: /update-todo
	@RequestMapping(value="update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
			
		if(result.hasErrors()) {
			return "todo";
				
		}
				
		String username = getLoggedInUsername(model);
		todo.setUsername(username);     //username'i gizli(hidden) bir değişken olarak koymak istemiyoruz. Bu yüzden SecurityContextHolder'dan username'i alıp, 'todo'ya set ederiz.
		todoService.updateTodo(todo);
				
		return "redirect:list-todos";
	}
	
	
	
	
}


