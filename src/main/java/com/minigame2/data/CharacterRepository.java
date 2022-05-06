package com.minigame2.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.minigame2.model.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {

}