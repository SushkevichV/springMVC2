package by.sva.springMVC2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondController {
	
	@GetMapping("/home")
	public String home() {
		return "second/home";
	}

}
