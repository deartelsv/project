package com.myownguild.game;

import java.util.Random;

public class Missons {

    private int lvl = 0;
    private int reward = 50;
    private String name = null;
    private MissionNames missonsName;

    private Random r;

    private int guildLvl;

    public Missons (int guildLvl){
        this.guildLvl = guildLvl;
        init();
    }

    private void init(){
        lvl = getMissionLvl(guildLvl);
        //reward = getMissionReward(lvl);
        //name = "Mission "+lvl+1;

    }

    public void updateGuildLvl(int guildLvl){
        this.guildLvl = guildLvl;
        lvl = getMissionLvl(guildLvl);
    }

    private int getMissionLvl(int guildLvl){
        return guildLvl;
    }

    private Integer getMissionReward(int lvl){
        return lvl * 50;
    }

    public Integer getLvl() {
        return lvl;
    }

    public Integer getReward() {
        int lvl = (int)(guildLvl / 3)+1;
        return reward * lvl;
    }

    public String getName() {
        return "Mission "+lvl;
    }

    public Integer getHp(){return guildLvl * 25;}

    public void pass(){}
}
