package musicWindows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import functions.MP3Player;

public class PlayerController extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8011781365303920633L;
	public static int currentSequence = 0;
	public static JButton pause = new JButton("播放");
	public static JButton last = new JButton("上一曲");
	public static JButton next = new JButton("下一曲");
	
	public static void disableAllButtons() {
		pause.setEnabled(false);
		last.setEnabled(false);
		next.setEnabled(false);
	}
	public static void enableAllButtons() {
		pause.setEnabled(true);
		last.setEnabled(true);
		next.setEnabled(true);
	}

	public PlayerController(Color color) {
		this.setBackground(color);
		this.setPreferredSize(new Dimension(this.getSize().width, 70));
		this.add(last);
		this.add(pause);
		this.add(next);
		PlayerController.disableAllButtons();
		pause.addActionListener(new PauseAL(pause));
		last.addActionListener(new SwitchSong(false));
		next.addActionListener(new SwitchSong(true));
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

class SwitchSong implements ActionListener {
	private boolean nextOrLast;
	
	/**
	 * @param true下一曲，false上一曲
	 */
	public SwitchSong(boolean flag) {
		this.nextOrLast = flag;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int pointer = 0;
		if(this.nextOrLast) {
			pointer = PlayerController.currentSequence + 1;
		} else {
			pointer = PlayerController.currentSequence - 1;
		}
		pointer = pointer%(SongListWindow.songButton.size());
		SongListWindow.songButton.get(pointer).getActionListeners()[0].actionPerformed(e);
	}
	
}
