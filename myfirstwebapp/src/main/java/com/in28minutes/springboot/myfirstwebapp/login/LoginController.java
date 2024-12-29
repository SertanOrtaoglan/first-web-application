package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")       //Değerleri session içerisine koymanın yolu '@SessionAttributes' annotation'ı kullanmaktır. Hangi özelliği(attribute) session'a koymak istiyorsak(örneğin: "name") onu parantez içerisine yazarız. Daha sonra bu session içerisine koyduğumuz "name" attribute'unu buradan başka hangi controller'da kullanmak istiyorsak(mesela 'TodoController') o controller içerisine de aynı burada yazdığımız şekilde yazarak[@SessionAttributes("name") şeklinde] kullanımı gerçekleştirebiliriz. 
public class LoginController {
	
	//Authentication işlemi yapacağız. "name:in28minutes password: dummy" girilirse giriş sayfasına girmesine izin vereceğiz. Aksi taktirde login sayfasına geri yönlendireceğiz. Bunun işlem için "AuthenticationService" adında yeni bir class oluşturup gerekli methodları yazıp buraya enjekte ederiz.
	private AuthenticationService authenticationService;
	
	
	public LoginController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	
	//login
	//Şu an bu methodumuz "GET ve POST" isteklerini karşılıyor.[@RequestMapping("login") => GET,POST] Bu methodun sadece "GET" isteklerini karşılamasını istiyoruz. Bunun için annotation'ı "@RequestMapping(value = "login", method = RequestMethod.GET)" şeklinde yazarız. Böylelikle bu method sadece "GET" isteklerini karşılayacaktır.
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String goToLoginPage() {
		return "login";
	}
	
	//login?name=Ranga(RequestParam) ==> Sorgu parametrelerini(?name=Ranga) ve form datalarını "RequestParam" annotation'ı ile yakalayabiliriz.
 	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String goToWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {    //Form'a girilen name ve password'ü RequestParam ile yakalayıp ardından 'model' içerisine eklemek ve JSP'de göstermek istiyoruz.
 		
 		//Authentication işlemini uygularsak;
 		if(authenticationService.authenticated(name, password)) {
 			model.put("name", name);
 	 		
 	 		return "welcome";
 		}
 	
 		model.put("errorMessage", "Invalid Credentials! Please try again.");
 		return "login";
	}
	
 	
	
 	
 	
}

