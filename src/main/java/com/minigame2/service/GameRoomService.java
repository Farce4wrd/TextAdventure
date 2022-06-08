package com.minigame2.service;

import com.minigame2.data.GameRoomRepository;
import com.minigame2.exception.GameDataException;
import com.minigame2.model.GameRoom;
import com.minigame2.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GameRoomService {

	private GameRoomRepository gameRoomRepository;
	
	@Autowired
	public GameRoomService(GameRoomRepository repo) {
		this.gameRoomRepository = repo;
	}
	
	
	
	/**Creates the room object
	 * 
	 * Method: @param id
	 * Method: @param name
	 * Method: @param description
	 * Method: @param hasVisited
	 * Method: @param items
	 * Method: @param exits
	 *
	 * void
	 */

	//Adds room to the database
	public GameRoom addRoom(GameRoom room) {
		return gameRoomRepository.save(room);
	}

	/**
	 *
	 * @param roomId
	 * @param item
	 * @return
	 */
	public Boolean dropItemInRoom(int roomId, Item item){
		//Optional<GameRoom> room = this.gameRoomRepository.findRoomByitems(roomId);
		GameRoom room = this.gameRoomRepository.findRoomByitems(roomId);
		//GameRoom room = room.get();
		room.addItem(item);
		this.gameRoomRepository.save(room);
		return true;
		//return "Mission Accomplished! Room saved";
	}


	//retrieves rooms from the database
	public List<GameRoom> getRooms(){
		return this.gameRoomRepository.findAll();
	}
	//deletes room from the database
	public void deleteRoom(GameRoom room) {
		this.gameRoomRepository.delete(room);
	}
	//gets room based on id (primary key)
	public GameRoom getRoom(int id)
	{
		return this.gameRoomRepository.findById(id);
	}
	
	//gets room with item loaded
	public GameRoom getRoomWithItem(int id) {
		return this.gameRoomRepository.findRoomByitems(id);
	}
	
	//remove item from a room
	

	
	/**Inform the user the room has been visited
	 * 
	 * Method: @param room
	 *
	 * void
	 */
	public void setRoomVisited(GameRoom room) {
		room.setHasVisited();
	}

	

	
	

}
