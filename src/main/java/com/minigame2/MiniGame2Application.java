package com.minigame2;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.minigame2.data.CharacterRepository;
import com.minigame2.data.GameRoomRepository;
import com.minigame2.data.ItemRepository;
import com.minigame2.data.MonsterRepository;
import com.minigame2.exception.GameDataException;
import com.minigame2.model.Character;
import com.minigame2.model.GameRoom;
import com.minigame2.model.Item;
import com.minigame2.service.CharacterService;
import com.minigame2.service.GameRoomService;
import com.minigame2.service.ItemService;
import com.minigame2.view.Adventure;

import com.minigame2.view.Adventure;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;






@SpringBootApplication
public class MiniGame2Application{
	private static Logger LOG = LoggerFactory.getLogger(MiniGame2Application.class);
	
	@Autowired
    private ApplicationContext appContext;

	public static void main(String[] args) throws GameDataException {
		Application.launch(Adventure.class, args);
		//ConfigurableApplicationContext context = SpringApplication.run(MiniGame2Application.class, args);
		//GameView game = context.getBean(GameView.class);
		//game.start();
		
		
		
	}
	@Bean
	CommandLineRunner commandLineRunner(ItemRepository itemRepo,GameRoomRepository roomRepo, MonsterRepository monsterRepo, CharacterRepository characterRepo) {
		return args ->{
			
			
			
//			GameRoomService gr = new GameRoomService(roomRepo);
//			ItemService itemService = new ItemService(itemRepo);
//			CharacterService characterService = new CharacterService(characterRepo);
//			//GameRoom games = gr.getRoom(16);
//			GameRoom location = gr.getRoomWithItem(1);
//			Character chara = new Character("Josh",location,50,3,1,1);
//			List<Item> characterInventory = chara.getInventory();
//			String item ="bandage";
//			
//			
//			
//			System.out.println(chara);
//			System.out.println(gr.getRoomWithItem(1));
		
		};
	}
	
	
	
	
	

}
