package Model;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * The Judge class gives the functions of a judge.
 *
 * @author Lahiru
 */
public class Judge extends Person {

    /**
     * Creates a new Judge
     *
     * @param name name of the Judge
     */
    public Judge(String name) {
        super(name);
        TrackPeople.addJudge(this);                                             // keep track on the Judge created
    }

    /* blow the whistle */
    public boolean doWhistle() {
        AudioInputStream audioStream = null;
        try {
            URL url = getClass().getResource("/Extra/whistle.wav");
            audioStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                audioStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return true;

    }

}
