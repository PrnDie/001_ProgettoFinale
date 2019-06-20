package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@RestController
public class Control
{
	@Autowired
	private Serv serv;

	@RequestMapping("/")
	public String Start()
	{
		return "Benvenuto, ";
	}
	
	@GetMapping("/media")
	public String Prova() throws Exception
	{
		return serv.media();
	}
	
	@GetMapping("/dati")  
    public ArrayList<Ripetitore> Dati(@RequestParam(value="filter", defaultValue="Std") String filter)
	{
        try {
			return serv.Dati(filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }
	
	@GetMapping("/metadati")
	public String MetaDati() throws Exception
	{		
		ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		mapper.toString();
		String json = mapper.writeValueAsString(serv.MetaDati());
		return json;   
	}
}