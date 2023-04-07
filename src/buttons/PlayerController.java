package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import functions.MP3Player;

public class PlayerController implements ActionListener{

	private String name; //表明上一首，下一首或暂停
	
	public PlayerController(String name) {
		this.name = name;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MP3Player mp3 = MP3Player.getmp3(name);
		mp3.start();
	}

}
