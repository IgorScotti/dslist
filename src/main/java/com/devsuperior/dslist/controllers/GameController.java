package com.devsuperior.dslist.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.error.BeanNotFoundException;
import com.devsuperior.dslist.services.GameService;


@RestController
@RequestMapping(value = "/games")
public class GameController {

	@Autowired
	private GameService gameService;

	@GetMapping(value = "/{id}")
	public GameDTO finById(@PathVariable Long id) {		
		 
		 try {
			 gameService.findById(id);
		 }catch(NoSuchElementException e){
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND,"ID não encontrado");
			 
		 }
		 return gameService.findById(id);
	}

	@GetMapping
	public List<GameMinDTO> findAll() {
		List<GameMinDTO> result = gameService.findAll();
		return result;
	}

	 @ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<String>handleResponseStatusException(ResponseStatusException ex){
		return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
	}
}
