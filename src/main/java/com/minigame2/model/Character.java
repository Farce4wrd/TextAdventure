package com.minigame2.model;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="CHARACTER")
public class Character {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String name;
	private int hp;
	private int life;
	private int points;
	private int level;
	@OneToOne
	@JoinColumn(name="room_id")
	private GameRoom location = new GameRoom();
	@Transient
	private List<Item> inventory;
	@Transient
	private ReentrantLock lock;

	public Character() {
	}

	public Character(String name, int hp, int life, int points, int level) {
		lock= new ReentrantLock();
		this.name = name;
		this.inventory = new ArrayList<Item>();
		this.hp = hp; //for now...
		this.life= life;
		this.points = points;
		this.level = level;
	}

	public List<Item> getInventory()	{
		lock.lock();
		try {
			return this.inventory;
		}finally {
			lock.unlock();
		}
	}
	
	public GameRoom getLocation() {
		return location;
	}
	
	public void setLocation(GameRoom location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void setInventory(List<Item> characterInventory) {
		this.inventory = characterInventory;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	@Override
//	public String toString() {
//		return "Character [id=" + id + ", name=" + name + ", hp=" + hp + ", life=" + life + ", points=" + points
//				+ ", level=" + level + ", location=" + location.getName() + ", inventory=" + inventory + "]";
//	}
//
	
}
