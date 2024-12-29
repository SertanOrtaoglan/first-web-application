package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	/*(120-122 arası yaptıklarımız)
	
	// /login(url) => com.in28minutes.springboot.myfirstwebapp.login.LoginController => login.jsp
	
	//http://localhost:8084/login?name=Ranga   (name'in değerini Url'den alıp @RequestParam ile buradaki methodumuza gönderilmesini sağlarız.)
	//Kontrolörümüzden(LoginController) JSP'mize(login.jsp) herhangi bir şey aktarmak istiyorsak(örneğimizdeki 'name' gibi) bu işlemi 'Model' kullanarak yapmamız gerekir. Bunun için 'ModalMap'ten yararlanacağız.
	//Yani controller'da, view(login.jsp) için herhangi bir şeyi kullanılabilir hale getirmek istiyorsak, bunu model'e koymamız gerekir.
	@RequestMapping("login")
	public String goToLoginPage(@RequestParam String name, ModelMap model) {
		model.put("name", name);    //'model'in içerisine adı 'name' olan ve değeri de 'name' olan bir 'attribute(özellik)' koymuş olduk.
		System.out.println("Request param is " + name);    //NOT RECOMMENDED FOR PRODUCTION CODE! (Çıktı console'a yazdırılır.)
		return "login";
	}
	*/
	
	
	/* Loglama yapma(123-step09)
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("login")
	public String goToLoginPage(@RequestParam String name, ModelMap model) {
		model.put("name", name);    
		
		//Application properties'te ayarladığımız logging level'a göre buradaki seviyelerden(debug,info,warn) birinin kodu çalışır.
		logger.debug("Request param is {}", name);           //'Debug' seviyesinde loglama yapar. Seviyeyi application properties'te belirleriz.(logging.level.com.in28minutes.springboot.myfirstwebapp = debug) Seviyeyi 'debug' yaparsak 'debug', 'info' ve 'warn' yani hepsi çalışır.
		logger.info("I want this printed at info level");    //'Info' seviyesinde loglama yapar. (logging.level.com.in28minutes.springboot.myfirstwebapp = info) Seviyeyi 'info' yaparsak 'info' ve 'warn' çalışır.
		logger.warn("I want this printed at warn level");    //'Warn' seviyesinde loglama yapar. (logging.level.com.in28minutes.springboot.myfirstwebapp = warn) Seviyeyi 'warn' yaparsak sadece 'warn' çalışır.
		System.out.println("Request param is " + name);      //NOT RECOMMENDED FOR PRODUCTION CODE! (Çıktı console'a yazdırılır.)
		
		return "login";
	}
	*/
	
	
	/*
	//Gereksiz satırları kaldırıp kodumuzu yeniden düzenlersek;
	@RequestMapping("login")
	public String goToLoginPage(@RequestParam String name, ModelMap model) {
		model.put("name", name);
		return "login";
	}
	*/
	
	
	//Login form oluşturma(125-step11)
	@RequestMapping("login")
	public String goToLoginPage() {
		return "login";
	}
	
	
	
	
	
	
	
	
	
	

}

