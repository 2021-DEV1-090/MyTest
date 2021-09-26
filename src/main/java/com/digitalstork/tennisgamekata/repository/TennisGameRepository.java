package com.digitalstork.tennisgamekata.repository;

import com.digitalstork.tennisgamekata.model.TennisGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TennisGameRepository extends JpaRepository<TennisGame, Long> {
}
