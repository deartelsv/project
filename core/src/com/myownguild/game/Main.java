package com.myownguild.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.myownguild.game.screens.MenuScreen;

import java.util.Map;

public class Main extends Game {

	public final static String GAME_NAME = "My Own Guild"; //для пк версии, название
	public final static int WIDTH = 720; // ширина
	public final static int HEIGHT = 1280; // высота

	public SoundsManager soundsManager;
	public Preferences preferences;

	public String guildName = "YourGuild";



	public Integer upgrade1 = 0;
	public Integer upgrade2 = 0;
	public Integer upgrade3 = 0;
	public Integer upgrade4 = 0;

	public Integer powerTouch = upgrade1*2+1;

	public int guildLvl = 0;

	public Integer gold = 200;
	public Integer day = 0;

	public Missons missons = new Missons(guildLvl);
	public String missionName;
	public Integer missionLvl;
	public Integer missionReward;
	public Integer missionHp;

	public Integer multiclick = 1;
	public Integer attackSpeed = 2000;


	public Integer counterMissions = 0;

	public void plusMission (){
		counterMissions++;
		if (counterMissions == 3){
			day++;
			counterMissions /= 3;
		}
	}


	public void tap(){
		missionHp -= 1;
		System.out.println(missionHp);
	}

	public void newMisson(){

		guildLvl++;
		missons.updateGuildLvl(guildLvl);
		initMission();
		//gold += missons.getReward();
		System.out.println(guildLvl+"!");
		System.out.println(gold+"g");
	}

	public void initMission(){
		missionName = missons.getName();
		missionLvl = missons.getLvl();
		missionReward = missons.getReward();
		missionHp = missons.getHp();
	}







	public int curArmy = upgrade2*2+1;
	public int maxArmy;
	public int powerForEach = 1;

	public int power = curArmy * powerForEach;


	private boolean paused; // стоит ли на паузе игра, для проверок



	@Override
	public void create () {
		System.out.println("Created");
		load();
	    newMisson();
	    maxArmy = guildLvl * 4;
		if (soundsManager == null) {
			soundsManager = new SoundsManager();
		}



		this.setScreen(new MenuScreen(this));

	}



	//
	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	//
	private void load(){

	}

	@Override
	public void dispose () {

	}
}
