package Model;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * The Spectator class gives the functions of a judge.
 *
 * @author Lahiru
 */
public class Spectator extends Person implements Observer {                     // Spectator should observe the ScoreBoard

    /**
     * Creates a new Spectator
     *
     * @param name Name of the Spectator
     */
    public Spectator(String name) {
        super(name);
        TrackPeople.addSpectator(this);                                         // keep track on the Spectator created
    }

    private void focusOnSwimmer() {
        AudioInputStream audioStream = null;
        try {
            URL url = getClass().getResource("/Extra/FinalCheer.wav");
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
        System.out.println("Spectator " + getName() + "   %&*shout");

    }

    @Override
    public void update(Observable o, Object arg) {
        focusOnSwimmer();
    }

}
