package com.myownguild.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundsManager {
    private static Integer musicVolume = 0;
    private static Integer soundVolume = 10;

    Sound bgMusic = Gdx.audio.newSound(Gdx.files.internal("music/September.mp3"));
    long music = bgMusic.play((float) (musicVolume/100));

    Sound click = Gdx.audio.newSound(Gdx.files.internal("music/click.mp3"));

    public SoundsManager(){
        playMisic();
    }

    private void playMisic(){
        bgMusic.play((float) (musicVolume/100));
        bgMusic.loop((float) (musicVolume/100));
    }

    public void tap(){
        click.play((float) (soundVolume/100));
    }

    private void setMusicVolume1(int musicVolume){
        bgMusic.setVolume(music, musicVolume);
    }

    public Integer getMusicVolume() {
        return musicVolume;
    }

    public Integer getStringMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(Integer musicVolume) {
        this.musicVolume = musicVolume;

        setMusicVolume1(musicVolume);
    }

    public Integer getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(Integer soundVolume) {
        this.soundVolume = soundVolume;
    }
}
