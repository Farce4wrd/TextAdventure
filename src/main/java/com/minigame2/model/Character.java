package com.minigame2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="CHARACTER")
public class Character implements Serializable {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String name;
	private int hp;
	private int life;
	private int points;
	private int level;
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="character")
	private GameRoom location;
	private ArrayList<Item> inventory;
	@Transient
	private ReentrantLock lock;


	public Character() {
	}

	public Character(String name, GameRoom location, int hp, int life, int points, int level) {
		lock= new ReentrantLock();
		this.name = name;
		this.location = location;
		this.inventory = new ArrayList<Item>();
		this.hp = 50; //for now...
		this.points = points;
		this.level = 0;
	}

	public ArrayList<Item> getInventory()	{
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

	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
