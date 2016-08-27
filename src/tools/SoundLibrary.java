package tools;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;

import main.Constants;

public class SoundLibrary {



	/** Play the sound at the given filepath */
	public static synchronized void playSound(final String url) {
		try {
			Clip sound = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
			sound.open(AudioSystem.getAudioInputStream(new File(Constants.ASSETS+ File.separatorChar +url)));
			sound.start();
		}
		catch(Exception e){
		}
	}
}
