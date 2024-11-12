package com.natodev.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natodev.dslist.dto.GameMinDTO;
import com.natodev.dslist.entities.Game;
import com.natodev.dslist.repositories.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	public List<GameMinDTO> findAll() {

		List<Game> games = gameRepository.findAll();
		List<GameMinDTO> gamesDTO = games.stream()
				.map(game -> new GameMinDTO(game))
				.toList();

		return gamesDTO;
	}

}
