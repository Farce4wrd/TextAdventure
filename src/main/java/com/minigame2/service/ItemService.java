package com.minigame2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minigame2.data.ItemRepository;
import com.minigame2.model.GameRoom;
import com.minigame2.model.Item;

@Service
public class ItemService {
	private ItemRepository itemRepository;

	@Autowired
	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	
	/**This code shows the name of the item
	 * 
	 * Method: @param item
	 *
	 * void
	 */
	public void inspect(Item item) {
		System.out.println(item.getItemDescription());
	}
	
	/**
	 * Method: @param item
	 */
	public Item addItem(Item item) {
		return this.itemRepository.save(item);
	}
	
	public Item getItem(int id) {
		Optional<Item> itemContainer =  itemRepository.findById(id);
		if(itemContainer.isPresent()) {
			Item item = itemContainer.get();
			return item;
		}else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param room
	 * @return
	 */
	public List<Item> getItemsById(GameRoom room){
		return this.itemRepository.findByroom(room);
		
	}
	
	public void removeItemFromRoom(GameRoom room) {
		 this.itemRepository.removeByroom(room);
	}
	
	public List<Item> getWeapons(){
		return this.itemRepository.findByVariety("Weapon");
	}
	
	
	
	/**returns items in a list
	 * 
	 * Method: @return
	 *
	 * ArrayList<Item>
	 */
	public List<Item> getAllItems() {
		List<Item> items = new ArrayList<Item>();
		for(Item item: this.itemRepository.findAll()) {
			items.add(item);
		}
		return items;
	}
	
	/**Removes item from the backpack and places it inside a room
	 * 
	 * Method: @param item
	 *
	 * void
	 */
//	public void removeItem(Item item) {
//		for(Item it: player.getBackpack()) {
//			if(it.getName().equals(item.getName())) {
//				player.removeFromBackpack(it);
//				
//			}
//		}
//	}
//	
	
	
	
	

}
