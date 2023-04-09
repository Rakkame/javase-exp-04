package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import functions.MP3Player;
import javazoom.jl.decoder.JavaLayerException;
import musicWindows.PlayerController;

public class PlayerStart implements ActionListener{

	private int sequence = 0;
	private String name = null;
	
	public PlayerStart(int sequence, String name) {
		this.sequence = sequence;
		this.name = name;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		PlayerController.currentSequence = this.sequence; 
		MP3Player mp3 = null;
		try {
			mp3 = new MP3Player(this.name);
			mp3.play();
			PlayerController.enableAllButtons();
			PlayerController.pause.setText("暂停");
		} catch (JavaLayerException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
	}
}




