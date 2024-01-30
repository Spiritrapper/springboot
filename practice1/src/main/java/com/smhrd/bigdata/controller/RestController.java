package com.smhrd.bigdata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhrd.bigdata.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Controller @ResponseBody
@RequiredArgsConstructor
public class RestController {
	
	private final BoardRepository boardRepository;
	
	
	 @GetMapping("/") 
	 public String index() { 
		 return "hello"; 
	}
	

}
