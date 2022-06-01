package com.minigame2.data;

import com.minigame2.model.GameRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRoomRepository extends JpaRepository<GameRoom, Integer>{
	
	GameRoom findByitems(Integer item);
	
	@Query("SELECT roo FROM GameRoom roo LEFT JOIN FETCH roo.items items WHERE roo.id = ?1")
	GameRoom findRoomByitems(int id);
	
	GameRoom findById(int id);
	
}
