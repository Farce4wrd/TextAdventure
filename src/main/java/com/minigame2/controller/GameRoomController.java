package com.minigame2.controller;

import com.minigame2.exception.GameDataException;
import com.minigame2.model.Character;
import com.minigame2.model.*;
import com.minigame2.service.CharacterService;
import com.minigame2.service.GameRoomService;
import com.minigame2.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class GameRoomController {
	
	@Autowired
	private GameRoomService gameRoomService;
	@Autowired
	private CharacterService characterService;
	@Autowired
	private ItemService itemService;

	
	
	@Autowired
	/**Main controller responsible for all calls
	 * 
	 * @param service
	 * @param itemService
	 * @param player
	 * @throws GameDataException
	 */
	public GameRoomController(GameRoomService gameRoomService, CharacterService characterService)// throws GameDataException 
	{
		this.characterService = characterService;
		this.gameRoomService = gameRoomService;
		this.itemService = itemService;
	
		//ControllerStart();
	}
	

	//////Pre-cursor for the UI
	public Character createCharacterAtBeginning(String name) {
		GameRoom location = this.gameRoomService.getRoom(1); //state
		Character chara = new Character(name,50,3,1,1);
		chara.setLocation(location);
		this.characterService.characterSave(chara);
		return chara;
		
	}
	
	/**Starts player combat loop
	 * 
	 * Method: @param Weapon weapon
	 * Method: @param Monster monster
	 * Method: @param Character character
	 * 
	 */
//	public String combat(Weapon weapon, Monster monster, Character character)
//	{
//		int monsterHp = monster.getHp();
//		Random rand = new Random();
//		int monsterDamage = rand.nextInt(monster.getDamage());
//		String monsterType = monster.getVariety();
//		
//		int playerHp = character.getHp();
//		int weaponDamage = Integer.parseInt(weapon.getDamage());
//		String weaponType = weapon.getVariety();
//		
//		while(monsterHp > 0)
//		{
//			if(weaponType.equalsIgnoreCase(monsterType))
//			{
//				character.setHp(playerHp - monsterDamage);
//				monster.setHp(monsterHp - weaponDamage);
//				return "Your hp: " + playerHp + "\nMonster hp: " + monsterHp;
//			}
//			else
//			{
//				character.setHp(playerHp - monsterDamage);
//				return "Your hp: " + playerHp + "\nMonster hp: " + monsterHp + "\nWrong weapon type! You did not do any damage";
//			}
//		}
//		if(monsterHp <= 0)
//		{
//			
//			return monster.getName() + " has been defeated!";
//		}
//		else
//		{
//			return "\n";
//		}
//	}
//	
	/**Player movement method
	 * 
	 * Method: @param GameRoom room
	 * Method: @param Character character
	 * Note: I un commented the exits arraylist getters and setters to make this work
	 */
	
	public String move(Character character, String direction)
	{
		Boolean isPresent = false;
		GameRoom currentLocation = character.getLocation();
		List<Exit> exits = currentLocation.getAllExitObject();
		for(Exit e : exits)
		{
			if((e.getDirection()).equalsIgnoreCase(direction))
			{
				isPresent = true;
				int i = e.getRoomId();
				character.setLocation(this.gameRoomService.getRoom(i));
				return this.gameRoomService.getRoom(i).getDescription();
			}
		}
		if(isPresent == false) {
			return direction+" does not exist here";
		}
		
		return character.getLocation().getDescription();
		//Change the UI of map to match the player location
	}

	/**
	 *
	 * @param character
	 * @return list of items within a room
	 */
	public List<String> inspectRoomItems(Character character){
		List<String> roomItems = new ArrayList<String>();
		GameRoom room = gameRoomService.getRoomWithItem(character.getLocation().getId());
		for(Item item: room.getItems()){
			roomItems.add(item.toString());
		}
		return roomItems;
	}

	 //Tester method for looking through any room with item
	 public List<String> checkAnyRoomItem(Character character){
		 List<String> roomItems = new ArrayList<String>();
		 GameRoom room = gameRoomService.getRoomWithItem(17);
		 for(Item item: room.getItems()){
			 roomItems.add(item.toString());
		 }
		 return roomItems;
	 }

	public String useItem(Character character, String item){
		List<Item> inventory = character.getInventory();
		for(Item piece: inventory){
			switch (piece.getVariety()){
				case "Consummable":
					//remove it from the database/character
					character.setHp(character.getHp()+15);
					break;
				case ""

			}
		}
	}
	
	/**Upgrade weapon 
	 * 
	 * Method: @param Character character
	 * Method: @param Weapon weapon
	 * Method: @param Item upgradeConsumable
	 * 
	 */
//	public String upgrade(Character character, Weapon weapon, Item upgradeConsumable)
//	{
//		ArrayList<Item> currentInventory = character.getInventory();
//		int weaponDamage = Integer.parseInt(weapon.getDamage()) ;
//		
//		if((upgradeConsumable.getName().equalsIgnoreCase("Big Box")) && (character.getInventory().contains(upgradeConsumable)
//				&& currentInventory.contains(weapon)))
//		{
//			weaponDamage+=15;
//			weapon.setDamage(""+weaponDamage);
//			return weapon.getName() + " has been upgraded. \n The current damage is now " + weapon.getDamage() + "\n";
//		}
//		else
//		{
//			return "Invalid option. \n You need to have the " + weapon.getName() + " and the Big Box in your inventory to upgrade the " + weapon.getName() + "\n";
//		}
//	}
//	
	/**Pickup an item from a room
	 * 
	 * Method: @param Character character
	 * Method: Item item
	 *  
	 */
	public String pickup(Character character, String item) {
		//gets room of the character loaded with items
		GameRoom location;
		try {
			location = this.gameRoomService.getRoomWithItem(character.getLocation().getId());
			List<Item> itemsFound = location.getItems();
			boolean isFound = false;
			for(Item it: itemsFound) {
				if(it.getName().equalsIgnoreCase(item)) {
					isFound = true;
					character.getInventory().add(it); //add the item to character's inventory
					location.removeItem(it);
					this.gameRoomService.addRoom(location);
					this.characterService.characterSave(character);
					String res =it.getName() + " has been added to your inventory!\n";	
					return res;
				}
			}
			if(isFound == false) {
				return "Invalid option. " + item + " is not in the " + location.getName();
			}
		} catch (NullPointerException e) {
			return "Cannot find that item within this room";
		}
		
		return "\n";
	}
	
	/**Drop item in a room
	 * 
	 * Method: @param Character character
	 * Method: @param Item item
	 * 
	 */
	
	public String drop(Character character, String item)
	{
		boolean isFound = false;
		try {
			GameRoom location = this.gameRoomService.getRoomWithItem(character.getLocation().getId());
			List<Item> characterInventory = character.getInventory();
			for(Item piece: characterInventory) {
				if(piece.getName().equalsIgnoreCase(item)) {
					isFound = true;
					location.addItem(piece); //add item to the current room
					characterInventory.remove(piece);

					characterService.characterSave(character); //save the character
					boolean result = this.gameRoomService.dropItemInRoom(location.getId(), piece);
					return item+ " has been added to the " + location.getName() + "!\n";
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return isFound ? "":"Invalid option. " + item + " is not in your inventory. \n";
	
	}

}
