package com.natodev.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.natodev.dslist.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
}