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
	

	
	/**Inform the user the room has been visited
	 * 
	 * Method: @param room
	 *
	 * void
	 */
	public void setRoomVisited(GameRoom room) {
		room.setHasVisited();
	}
	
	/**leaves item from backpack in current room
	 * 
	 * Method: @param item
	 * Method: @param room
	 *
	 * void
	 */
	public void dropItemInRoom(Item item, GameRoom room) {
		room.addItem(item);
	}
	
	/**adds an item randomly into a randomly into a room
	 *  
	 * Method: @param item
	 * Method: @param room
	 * Method: @throws GameDataException
	 *
	 * void
	 */
	public void addItemInRoom(ArrayList<Item> item, ArrayList<GameRoom> room) throws GameDataException {
		Item item1 = item.get(0);
		Item item2 = item.get(1);
		Item item3 = item.get(2);
		
		room.get(1).addItem(item1);
		room.get(2).addItem(item2);
		room.get(3).addItem(item3);
		
	}
	
	/**remove item from room after player picks it up
	 * 
	 * Method: @param item
	 * Method: @param room
	 *
	 * void
	 */
	public void removeItemFromRoom(Item item, GameRoom room) {
		room.removeItem(item);
	}
	
	/**gets item from specific room
	 * 
	 * Method: @param room
	 * Method: @return
	 *
	 * ArrayList<Item>
	 */

	

	
	

}
