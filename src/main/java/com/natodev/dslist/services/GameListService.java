package com.natodev.dslist.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.natodev.dslist.dto.GameListDTO;
import com.natodev.dslist.entities.GameList;
import com.natodev.dslist.repositories.GameListRepository;
import com.natodev.dslist.services.exceptions.ResourceNotFoundException;

@Service
public class GameListService {
	
	@Autowired
	private GameListRepository gameListRepository;
	
	@Transactional(readOnly = true)
	public List<GameListDTO> findAll(){
		
		List<GameList> gameLists = gameListRepository.findAll();
		List<GameListDTO> gameListsDTO = gameLists.stream()
				.map(gameList -> new GameListDTO(gameList))
				.toList();
		
		return gameListsDTO;
	}
	
	@Transactional(readOnly = true)
	public GameListDTO findById(Long id) {
	
		try {
			
			GameList gameList = gameListRepository.findById(id).get();
			GameListDTO gameListDTO = new GameListDTO(gameList);
			
			return gameListDTO;
			
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(id);
		}
	}

}
