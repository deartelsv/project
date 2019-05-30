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
        game1.curHP -= game1.game.curArmy * 2 + 1 ;
    }
}
