package by.sva.springMVC2.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

//!!! не запускать update project, слетит проект
//если проект не запускается, скопировать из springMVC файл web.xml в папку WEB-INF, исправить содержимое и запустить. Потом удалить

//В настройках сервера в moduls путь установить "/", иначе ссылки в html-файлах не работают. Или скорректировать ссылки - добавить префикс "/springMVC2"
//Если пусть установить "/springMVC2", то работают ссылки даже между проектами
@Controller
//@RequestMapping("/first") 	// если раскомментировать, то все ссылки получат префикс "/first"
								// и нужно будет скорректировать ссылки в файлах представлений: добавить /first
public class FirstController {
	
	@GetMapping("/hello") 	// переход на страницу hello по запросу /hello
							// или /first/hello если @RequestMapping("/first") раскомментирован
	public String helloPage() {
		return "first/hello";
	}
	
	@GetMapping("/goodbye")
	public String goodbyePage() {
		return "first/goodbye";
	}
	
	@GetMapping("/request") // отправка запроса с параметрами /request?name=V&surname=S
	// получает весь запрос от клиента (все-все-все, включая версию браузера) и можно обращаться к любому параметру запроса
	// !!! реализован вывод в консоль
	public String request(HttpServletRequest rq) {
		String name = rq.getParameter("name"); // при отсутствии искомого параметра задаст значение поумолчанию и продолжит работу
		String surname = rq.getParameter("surname"); // при отсутствии искомого параметра задаст значение поумолчанию и продолжит работу
		System.out.println("Hello, "+name+ " "+surname);
		return "first/hello";
	}
	
	@GetMapping("/request2") // тоже самое другим способом
	// получает только описанные параметры, при отсутствии обязательного параметра свалится
	// !!! реализован вывод в консоль
							//обязательный параметр						необязательный параметр 
	public String request2(@RequestParam("name") String name, @RequestParam(value = "surname", required = false) String surname) { // получает только параметры, указанные в @RequestParam
							// обратить внимание на синтаксис обязательного и необязательного параметра
		
//		String name = rq.getParameter("name"); // присваивание происходит в аннотации @RequestParam
//		String surname = rq.getParameter("surname"); // присваивание происходит в аннотации @RequestParam
		System.out.println("Hello, "+name+ " "+surname);
		return "first/hello";
	}
	
// динамическое создание параграфа html-страницы <p th:text="${message}"/>
	// подключение thymeleaf в html-файле: <html lang="en" xmlns:th="http://thymeleaf.org">
	@GetMapping("/dynamic") // отправка запроса с параметрами /dynamic?name=Vladislav&surname=Sushkevich
	public String dynamic(@RequestParam(value = "name", required = false) String name, // required = false не выдаст ошибки, если не будет значения
			              @RequestParam(value = "surname", required = false) String surname,
			              Model model) { // model будет передана в представление, где ее значения будут отображены с помощью thymeleaf
		model.addAttribute("message", "Hello, "+name+" "+surname); // ключ - "message", значение - "Hello, "+name+" "+surname
		return "first/hello";
	}
	
	@GetMapping("/calc") // отправка запроса с параметрами /calc?a=2&b=3&action=mult
	public String calculator(@RequestParam(value = "a") int a,
			                 @RequestParam(value = "b") int b,
			                 @RequestParam(value = "action") String action,
			                 Model model) {
		double result=0;
		switch(action) {
			case "mult": 
				result=a*b;
				model.addAttribute("message", a+"*"+b+"="+result);
				break;
			case "div":
				result=a/(double)b;
				model.addAttribute("message", a+"/"+b+"="+result);
				break;
			case "plus":
				result=a+b;
				model.addAttribute("message", a+"+"+b+"="+result);
				break;
			case "minus":
				result=a-b;
				model.addAttribute("message", a+"-"+b+"="+result);
				break;
			default:
				result=0;
				model.addAttribute("message", "No arguments");
		}
		
		return "first/calculator";
	}

}
