package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.*;
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
	

	@GetMapping("/metadati")
	public String MetaDati() throws Exception
	{
		ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		Ripetitore rip = serv.rip();		
		String json = mapper.writeValueAsString(rip);
		return json;
	}
		
	@RequestMapping("/")
	public String invoke()
	{
		return "Welcome";
	}
		
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public class EntityNotFoundException extends RuntimeException
	{
	    public EntityNotFoundException(String message) {
	        super(message);
	    }
	 
	    public EntityNotFoundException() {
	        super();
	    }	        
	}
}