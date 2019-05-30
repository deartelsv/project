package com.myownguild.game;

public class SoundsManager {
    private static Integer musicVolume = 50;
    private static Integer soundVolume = 50;

    public Integer getMusicVolume() {
        return musicVolume;
    }

    public Integer getStringMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(Integer musicVolume) {
        this.musicVolume = musicVolume;
    }

    public Integer getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(Integer soundVolume) {
        this.soundVolume = soundVolume;
    }
}
