package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")       //Değerleri session içerisine koymanın yolu '@SessionAttributes' annotation'ı kullanmaktır. Hangi özelliği(attribute) session'a koymak istiyorsak(örneğin: "name") onu parantez içerisine yazarız. Daha sonra bu session içerisine koyduğumuz "name" attribute'unu buradan başka hangi controller'da kullanmak istiyorsak(mesela 'TodoController') o controller içerisine de aynı burada yazdığımız şekilde yazarak[@SessionAttributes("name") şeklinde] kullanımı gerçekleştirebiliriz. 
public class WelcomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String goToWelcomePage(ModelMap model) {
		model.put("name", "in28minutes");   //Şimdilik sabit kodlanmış bir isim(in28minutes) gönderiyoruz. İlerleyen bölümlerde bunu düzelteceğiz.
		return "welcome";
	}
	
	
	
 	
}
