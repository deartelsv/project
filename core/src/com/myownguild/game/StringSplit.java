package com.myownguild.game;

import java.util.Random;

public class StringSplit {

    private String delimeter = "!"; // Разделитель
    private String []subStr;
    private String text;

    public StringSplit(String text){
        this.text = text;

        subStr = text.split(delimeter);
    }

    public String random(){
        Random r = new Random();
        int i = r.nextInt((subStr.length+1 - 0) + 1) + subStr.length;
        return subStr[i];
    }

}
