package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;

import functions.MP3Player;
import javazoom.jl.decoder.JavaLayerException;

public class PlayerStart implements ActionListener{

	private String name;
	public JButton pause = null;
	
	public PlayerStart(String name, JButton pause) {
		this.name = name;
		this.pause = pause;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MP3Player mp3;
		try {
			mp3 = new MP3Player(name);
			mp3.play();
			pause.setEnabled(true);
			pause.setText("暂停");
			pause.addActionListener(new PauseAL(pause));
		} catch (JavaLayerException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
	}
}


class PauseAL implements ActionListener {
	private JButton pause;
	
	public PauseAL(JButton pause) {
		this.pause = pause;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MP3Player mp3 = MP3Player.last;
		if(!mp3.Stopped()) {
			mp3.pause(true);
			pause.setText("继续");
		}
		else {
			mp3.pause(false);
			pause.setText("暂停");
		}
	}

}
