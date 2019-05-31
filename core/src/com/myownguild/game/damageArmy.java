package com.myownguild.game;

import com.myownguild.game.screens.GameScreen;

import java.util.TimerTask;

public class damageArmy extends TimerTask {
    GameScreen game1;
    public damageArmy(GameScreen game){
        game1 = game;
    }

    @Override
    public void run() {
        game1.curHP -= game1.game.upgrade2*2+1;
        System.out.println("Attack speed is "+game1.game.attackSpeed);
    }
}
