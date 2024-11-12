package com.natodev.dslist.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.natodev.dslist.dto.GameDTO;
import com.natodev.dslist.dto.GameMinDTO;
import com.natodev.dslist.entities.Game;
import com.natodev.dslist.projections.GameMinProjection;
import com.natodev.dslist.repositories.GameRepository;
import com.natodev.dslist.services.exceptions.ResourceNotFoundException;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll() {

		List<Game> games = gameRepository.findAll();
		List<GameMinDTO> gamesDTO = games.stream()
				.map(game -> new GameMinDTO(game))
				.toList();

		return gamesDTO;
	}

	@Transactional(readOnly = true)
	public GameDTO findById(Long id) {

		try {

			Game game = gameRepository.findById(id).get();
			GameDTO gameDTO = new GameDTO(game);

			return gameDTO;

		} catch (NoSuchElementException error) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findByGameList(Long id){
		
		List<GameMinProjection> gameMinProjections = gameRepository.searchGameList(id);
		
		if(gameMinProjections.isEmpty()) {
			throw new ResourceNotFoundException(id);
		}
		
		List<GameMinDTO> gamesDTO = gameMinProjections.stream()
				.map(gameMinProjection -> new GameMinDTO(gameMinProjection))
				.toList();
		
		return gamesDTO;
	}

}
