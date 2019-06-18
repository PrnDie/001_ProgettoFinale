package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Control
{
	@Autowired
	private Serv serv;

	@GetMapping("/prova")
	
	public String Prova()
	{
		try {
			return serv.prova();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/")
	public String invoke() {
		return "Welcome ";
	}	
}
