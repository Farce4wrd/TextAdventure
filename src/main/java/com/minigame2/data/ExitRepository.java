package com.minigame2.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minigame2.model.Exit;

@Repository
public interface ExitRepository extends JpaRepository<Exit, Integer>{

}
