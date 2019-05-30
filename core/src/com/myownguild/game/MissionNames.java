package com.myownguild.game;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;

public class MissionNames {

    private ArrayList<String> missions = new ArrayList<String>();
    FileHandle handle = Gdx.files.internal("missions.txt");
    private StringSplit stringSplit = new StringSplit(handle.readString());

    public String mission(){
       return stringSplit.random();
    }

}
