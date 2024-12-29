package com.in28minutes.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
	
	//"say-hello" => "Hello! What are you learning today?"
	
	// http://localhost:8084/say-hello
	@RequestMapping("say-hello")
	@ResponseBody
	public String sayHello() {
		return "Hello! What are you learning today?";
	}
	
	
	@RequestMapping("say-hello-html")
	@ResponseBody
	public String sayHelloHtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title> My first HTML Page - Changed </title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("My first html page with body - Changed");
		sb.append("</body>");
		sb.append("</html>");

		return sb.toString();
	}
	
	
	
	
	//"say-hello-jsp"(url) => sayHello.jsp (Yani "say-hello-jsp" url'sine istek atan birini 'sayHello.jsp' sayfasına yönlendireceğiz.)
	//  /src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp (Aşağıda  yazdığımız methodu bu oluşturduğumuz dosyaya yönlendireceğiz.)
	
	
	//Diyelim ki 10 JSP'm var.
	//  /src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
	//  /src/main/resources/META-INF/resources/WEB-INF/jsp/welcome.jsp
	//  /src/main/resources/META-INF/resources/WEB-INF/jsp/login.jsp
	//  /src/main/resources/META-INF/resources/WEB-INF/jsp/todos.jsp
	//Görüldüğü gibi değişen tek şey en son kısımda yazdığımız isimlerdir. Dolayısıyla en baştan ismin olduğu yere kadar olan kısmı 'application properties'de tanımlayabiliriz. Böylelikle her defasında aynı şeyi yazmakla uğraşmayız.
	
	
	@RequestMapping("say-hello-jsp")
	public String sayHelloJsp() {
		return "sayHello";   //Burada yazdığımız "sayHello" temel olarak JSP'nin adıdır. Yani "sayHello.jsp"den gelir. Sonuna ".jsp" yazmıyoruz.
	}
	
	
	
	
	
	
	
	
	
	
	

}
