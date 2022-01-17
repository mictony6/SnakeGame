package com.mic.snake.sound;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound {
    String name;
    Clip clip;
    URL url;
    AudioInputStream stream;


    public Sound(String name){
        this.name = name;

    }

    public void play(){
        initializeClip();
        clip.start();

    }

    public void stop(){
        clip.stop();
    }

    public void loop(){

        initializeClip();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    private void initializeClip() {
        try
        {
            clip = AudioSystem.getClip();
            url = getClass().getResource("/music/"+this.name+".wav");
            assert url != null;
            stream = AudioSystem.getAudioInputStream(url);
            clip.open(stream);
            clip.addLineListener(new CloseClipWhenDone());
            setVolume(-12);


        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    public Clip getClip() {
        return clip;
    }

    void setVolume(int v){
        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(v);
    }
}


class CloseClipWhenDone implements LineListener
{
    @Override public void update(LineEvent event)
    {
        if (event.getType().equals(LineEvent.Type.STOP))
        {
            Line soundClip = event.getLine();
            soundClip.close();
        }
    }
}