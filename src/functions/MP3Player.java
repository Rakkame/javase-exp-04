package functions;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MP3Player extends Thread {
	public static Player player = null;
	public boolean isPlaying;
	public String filename;
	public static MP3Player mp3 = null;
	
	public static MP3Player getmp3(String filename) {
		if(mp3 != null)
			return mp3;
		else {
			mp3 = new MP3Player(filename);
			return mp3;
		}
	}
	
	private MP3Player(String filename) {
		this.filename = filename;
		this.isPlaying = false;
	}
	
	@Override
	public void run() {
		this.isPlaying = true;
		try {
			FileInputStream fileInputStream = new FileInputStream(filename);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			player = new Player(bufferedInputStream);
			this.isPlaying = true;
			player.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
