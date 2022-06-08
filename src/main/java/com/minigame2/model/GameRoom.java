package com.minigame2.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "ROOM")
@NamedQueries({
		@NamedQuery(name = "GameRoom.countBy", query = "select count(g) from GameRoom g")
})

public class GameRoom {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String name;
	@Lob
	private String description;
	private String hasvisited;
	
	@OneToMany(mappedBy="room", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Item> items;
	
	@OneToMany(mappedBy="room", cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch= FetchType.EAGER)
	private List<Exit> exits = new ArrayList<>();
	
	@OneToMany(mappedBy="room", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<Monster> monsters;
	
	@OneToOne(mappedBy="location", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Character character;
	
	
	
	/**non-parameter Constructor
	 *  
	 */
	public GameRoom() {
		
	}
	
	/**Major GameRoom Constructor
	 *
	 * @param name
	 * @param description
	 * @param hasVisited
	 */
	public GameRoom(String name, String description, String hasVisited) {
		super();
		this.name = name;
		this.description = description;
		this.hasvisited = hasVisited;
	}
	
	/**
	 * sets the character
	 */
	public void setCharacter(Character character) {
		this.character= character;
	}
	/**
	 * @returns the character
	 */
	public Character getCharacter() {
		return this.character;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the hasVisited
	 */
	public String isHasVisited() {
		return hasvisited;
	}
	/**
	 *
	 */
	public void setHasVisited() {
		this.hasvisited = "TRUE";
	}
	
	/**To add items to a room
	 * 
	 * Method: @param item
	 *
	 * void
	 */
	public void addItem(Item item) {
		this.items.add(item);
		item.setRoom(this);
	}
	
	
	/**To remove items from room
	 * 
	 * Method: @param item
	 *
	 * void
	 */
	public void removeItem(Item item) {
		this.items.remove(item);
		item.setRoom(null);
	}
//	public void removeItem(Item item) {
//		for(int i = 0; i<this.items.size(); i++) {
//			Item it = items.get(i);
//			if(it.getName().equals(item.getName())) {
//				this.items.remove(it);
//			}
//		}
//	}
	
	/**Returns all items inside a room
	 * 
	 * Method: @return
	 *
	 * ArrayList<Item>
	 */
	public List<Item> getItems(){
		return this.items;
	}
	
	/**To get exits directions like {West,North, East, South} on a room-by-room basis
	 * 
	 * Method: @return
	 *
	 * ArrayList<String>
	 */
	public ArrayList<String> getExits(){
		ArrayList<String> exitDirections = new ArrayList<String>();
		for(Exit ex: exits) {
			String direction = ex.getDirection();
			exitDirections.add(direction);
		}
		return exitDirections;
	}
	
	/**To retrieve all exit objects
	 * 
	 * Method: @return
	 *
	 * ArrayList<Exit>
	 */
	public List<Exit> getAllExitObject(){
		return this.exits;
	}
	
//	@Override
//	public String toString() {
//		return "GameRoom [id=" + id + ", name=" + name + ", description=" + description + ", hasvisited=" + hasvisited
//				+ ", character=" + character.getName() + "]";
//	}

	public List<Monster> getMonsters() {
		return monsters;
	}

	public void setMonsters(ArrayList<Monster> monsters) {
		this.monsters = monsters;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

}
