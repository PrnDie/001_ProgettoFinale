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

	@GetMapping("/media")
	public String Prova() throws Exception
	{
		return serv.media();
	}
	
	@GetMapping("/dati")
	public String Dati() throws Exception
	{
		ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		mapper.toString();
		String json = mapper.writeValueAsString(serv.dati());
		return json;
	}
	
	@GetMapping("/metadati")
	public String MetaDati() throws Exception
	{
		ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		mapper.toString();
		String json = mapper.writeValueAsString(serv.MetaDati());
		return json;    
	}
	
	@RequestMapping("/")
	public String invoke() {
		return "Welcome ";
	}	
}