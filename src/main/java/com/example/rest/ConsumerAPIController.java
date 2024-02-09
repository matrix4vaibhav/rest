package com.example.rest;

// Java Program to illustrate Regular Controller
// Consume REST API

// Importing required classes
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// Annotation
@Controller
@RequestMapping("/Api")

// Class
public class ConsumerAPIController {

	// Annotation
	@GetMapping public String get(Model model)
	{

		// Creating an instance of RestTemplateProvider
		// class
		RestTemplateProvider restTemplate
			= new RestTemplateProvider();

		model.addAttribute("user",
						restTemplate.getUserData());
		model.addAttribute("model", new UserData());
		return "GetData";
	}

	// Annotation
	@PostMapping
	public String post(@ModelAttribute("model")
					UserData user, Model model)
	{

		RestTemplateProvider restTemplate = new RestTemplateProvider();

		ResponseEntity<UserData> response = restTemplate.post(user);

		model.addAttribute("user", response.getBody());
		model.addAttribute("headers",
						response.getHeaders() + " "
							+ response.getStatusCode());
		return "GetData";
	}
}
