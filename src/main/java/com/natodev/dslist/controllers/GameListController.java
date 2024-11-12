package com.natodev.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.natodev.dslist.dto.GameListDTO;
import com.natodev.dslist.dto.GameMinDTO;
import com.natodev.dslist.services.GameListService;
import com.natodev.dslist.services.GameService;

@RestController
@RequestMapping(value = "/gameLists")
public class GameListController {

	@Autowired
	private GameListService gameListService;
	
	@Autowired
	private GameService gameService;

	@GetMapping
	public List<GameListDTO> findAll() {

		List<GameListDTO> gameLists = gameListService.findAll();
		return gameLists;
	}

	@GetMapping(value = "/{id}")
	public GameListDTO findById(@PathVariable Long id) {

		GameListDTO gameList = gameListService.findById(id);
		return gameList;
	}
	
	@GetMapping(value = "{gameListId}/games")
	public List<GameMinDTO> findByList(@PathVariable Long gameListId) {

		List<GameMinDTO> gameLists = gameService.findByGameList(gameListId);
		return gameLists;
	}

}
