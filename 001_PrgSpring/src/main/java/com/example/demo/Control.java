package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@RestController
public class Control
{
	@Autowired
	private Serv serv;

	@GetMapping("/prova")
	public String Prova() throws Exception
	{
		return serv.prova();
	}
	
	@GetMapping("/MetaDati")
	public String MetaDati() throws Exception
	{
		ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		mapper.toString();
		String json = mapper.writeValueAsString(serv.rip());
		System.out.println(json);
		return json;
	}
	
	@RequestMapping("/")
	public String invoke() {
		return "Welcome ";
	}	
}