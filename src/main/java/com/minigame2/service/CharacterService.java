package com.minigame2.service;

import com.minigame2.data.CharacterRepository;
import com.minigame2.exception.GameDataException;
import com.minigame2.model.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {
	
	private final CharacterRepository characterRepository;
	
	@Autowired
	public CharacterService(CharacterRepository characterRepository) {
		this.characterRepository = characterRepository;
	}
	public void characterSave(Character character)
	 {
		 this.characterRepository.save(character);
	 }
	
	//get a character from database
	public Character getCharacter(int id) throws GameDataException {
		return this.characterRepository.findById(id).orElseThrow(() ->
		new GameDataException());
	}
	
}
