package com.example.jihanki3000;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundsClass {
    private Boolean isMuted = false;
    private final static SoundsClass INSTANCE = new SoundsClass();
    private SoundsClass(){};

    public static SoundsClass getInstance(){

        return INSTANCE;
    }
    public void playClick(){

        playSound("src/main/resources/Sounds/113095__edgardedition__insertcoin.wav");
    }

    public void playInsertCoin(){

        playSound("src/main/resources/Sounds/271295__arnaud-coutancier__insert-coin.wav");
    }

    public void playThunk(){
        playSound("src/main/resources/Sounds/323721__reitanna__drop-thunk.wav");
    }

    public void playCoinReturn(){
        playSound("src/main/resources/Sounds/29649__tweeterdj__single-coin-return.wav");
    }


    public void setMute(){
        isMuted = true;
    }
    public void unMute(){
        isMuted = false;
    }

    public Boolean isMuted(){
        return isMuted;
    }
    public synchronized void playSound(String musicFile) {
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);

        if (isMuted){
            mediaPlayer.setVolume(0);
        }
        else{
            mediaPlayer.setVolume(0.5);
        }

        mediaPlayer.play();
    }
}
