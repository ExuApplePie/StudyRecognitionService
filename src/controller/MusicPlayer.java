package controller;

import java.io.IOException;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.core.io.JavaSoundAudioIO;
import net.beadsproject.beads.data.Sample;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.Glide;
import net.beadsproject.beads.ugens.SamplePlayer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 84038001
 */
public class MusicPlayer {

    AudioContext ac;
    String sourceFile;
    SamplePlayer sp;
    Gain g;
    Glide gainValue;
    JavaSoundAudioIO jsaIO;

    public MusicPlayer() {
        jsaIO = new JavaSoundAudioIO();
        JavaSoundAudioIO.printMixerInfo();
        jsaIO.selectMixer(2); //this is system specific, perhaps 0 or 1 is deafult audio sound device?
//        jsaIO.chooseMixerCommandLine(); //use this if I ran from the command line?
        ac = new AudioContext(jsaIO);
    }

    public void playMusic(String path, double volume, boolean loop) {
        sourceFile = System.getProperty("user.dir") + "/audioFiles/" + path;
        try {
            sp = new SamplePlayer(ac, new Sample(sourceFile));
        } catch (IOException e) {
            System.err.println(e);
            System.exit(0);//change this probably? when audio file isn't found
        }
        sp.setKillOnEnd(!loop);
        gainValue = new Glide(ac, (float) volume, 20);
        g = new Gain(ac, 1, gainValue);
        g.addInput(sp);
        ac.out.addInput(g);
        ac.start();
    }

    public void pauseMusic() {
        ac.stop();
    }

    public void shuffleMusic() {

    }

    public void repeatMusic() {

    }

    public void skipSong() {

    }

    public void playLastSong() {

    }

    public void setVolume(int volume) {

    }

    public String getSong() {
        return "";
    }
}
