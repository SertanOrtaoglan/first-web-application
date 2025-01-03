package com.in28minutes.springboot.myfirstwebapp.security;

import static org.springframework.security.config.Customizer.withDefaults;   //'formLogin' için default değerleri kullanmak istiyoruz. Bu default değerler Customizer'daki static bir yöntem içerisinde tanımlanmıştır. Bu static method'un adı 'withDefaults()'dur. Dolayısıyla 'withDefaults'ı kullanabilmek için bu paketi import etmemiz gerekir.

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	//LDAP or Database('username' ve 'password'leri bu ikisinden birini kullanarak saklarız.)
	//Bu projemizde 'username' ve 'password'leri tutmak için 'InMemory' kullanacağız.
	
	//InMemoryUserDetailsManager
	//InMemoryUserDetailsManager(UserDetails... users)
	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		
		UserDetails userDetails1 = createNewUser("in28minutes", "dummy");
		UserDetails userDetails2 = createNewUser("ranga", "dummydummy");
		
		return new InMemoryUserDetailsManager(userDetails1, userDetails2);
	}

	//Kullancı yaratan bir yöntem oluşturduk. Ve yukarıdaki 'createUserDetailsManager()' yöntemi içerisinde bu kullanıcıları yarattık.
	private UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder
				= input -> passwordEncoder().encode(input);
		
		UserDetails userDetails = User.builder()
									.passwordEncoder(passwordEncoder)
									.username(username)
									.password(password)
									.roles("USER", "ADMIN")
									.build();
		return userDetails;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Configuring Spring Security to get H2-console Working
	
	//Spring Security default olarak 2 özellik sağlar.(Biz bunları dilersek yapılandırıp değiştirebiliriz.) Bu 2 özelliği sağlayan şey "SecurityFilterChain"dir.
	//1- All URLs are protected (by default)
	//2- A login form is shown for unauthorized requests (by default)
	
	//(3) Dolayısıyla H2 console'a bağlanabilmemiz için CSRF'i devre dışı(disable) bırakmamız gerekir.
	//(4) Ve ayrıca H2 web siteleri 'Frames' denilen bir şeyden yararlanır. Spring Security default olarak bu 'frames'lere(çerçevelere) izin vermez. Dolayısıyla bu 'frames'lere de izin verildiğinden emin olmamız gerekir.
	
	//Tüm bu işlemleri(yukarıdaki 4 madde) "SecurityFilterChain" içerisinde tanımlayıp yapılandırarak halledeceğiz.(İlk 2 madde default olarak "SecurityFilterChain" tarafından sağlanır. Biz uygulamamız için ek olarak son 2 maddeyi de ekleyeceğiz.)
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated());    //Gelen tüm request'lerin authenticate olmasını(doğrulanmasını) istiyoruz. Dolayısıyla bu kodu yazarız.(Madde 1)
		http.formLogin(withDefaults());                        //Gelen unauthorized request'leri(yetkisiz istekleri) direkt olarak login form'una yönlendirmek istiyoruz. Dolayısıyla bu kodu yazarız.(Madde 2)  NOT:Bu kodu ayrıca static method'u import etmekle uğraşmayıp direkt olarak 'http.formLogin(Customizer.withDefaults())' şeklinde de yazabiliriz.
		
		
		http.csrf(csrf -> csrf.disable());   //CSRF'i devre dışı bırakıyoruz.(Madde 3)
		
		http.headers(header -> header.frameOptions(frameOptions -> frameOptions.disable()));   //Uygulamamızda 'frame' kullanımını etkinleştiriyoruz.(Madde 4)
		
		
		return http.build();  
	}
	
	
	
	
	

}


