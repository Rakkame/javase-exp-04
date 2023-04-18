package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;

import javax.swing.JButton;

import functions.Configuration;
import functions.MP3Player;
import functions.PlayerController;
import httpclient.FileDownloader;
import javazoom.jl.decoder.JavaLayerException;

public class PlayerStart implements ActionListener{

	private int sequence = 0;
	private String name = null;
	private String md5 = null;
	
	public PlayerStart(int sequence, String name) {
		this.sequence = sequence;
		this.name = name;
	}
	
	public PlayerStart(int sequence, String name, String md5) {
		this.md5 = md5;
		this.name = name;
		this.sequence = sequence;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		PlayerController.currentSequence = this.sequence; 
		try {
			if(md5 != null)
				new BufferToPlayer(md5, e).start();
			else if(name != null)
				new MP3Player(this.name).play();
			PlayerController.enableAllButtons();
			PlayerController.pause.setText("暂停");
			PlayerController.label.setText(name);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (JavaLayerException e2) {
			e2.printStackTrace();
		}
	}
}

class BufferToPlayer extends Thread{
	String md5;
	ActionEvent e;
	public BufferToPlayer(String md5, ActionEvent e) {
		this.md5 = md5;
		this.e = e;
	}
	@Override
	public void run() {
		String query = Configuration.serverURL + "/downloadMusic";
		Object o = e.getSource();
		JButton button = null;
		if(o instanceof JButton) {
			button = (JButton)o;
			button.setText("缓冲中");
		}
		ByteArrayInputStream buffer = FileDownloader.getMusicStream(query,  md5);
		button.setText("播放");
		try {
			new MP3Player(buffer).play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}
}


