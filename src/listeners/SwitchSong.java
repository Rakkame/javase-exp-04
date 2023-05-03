package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import functions.MP3Player;
import functions.PlayerController;
import javazoom.jl.decoder.JavaLayerException;
import musicWindows.SongListWindow;

public class SwitchSong implements ActionListener {
	private int change;
	/**
	 * 构造方法
	 * @param change +1 下一曲，-1 上一曲
	 */
	public SwitchSong(int change) {
		this.change = change;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//获取歌曲列表
		String[] list = PlayerController.list;
		//获取应当切换的歌
		PlayerController.currentSequence
				= (PlayerController.currentSequence+this.change+list.length)%list.length;
		//调用MP3Player播放
		try {
			if(PlayerController.pathOrMD5) {
				MP3Player mp3 = new MP3Player(SongListWindow.songdir +list[PlayerController.currentSequence]);
				mp3.play();
			} else {
				new BufferToPlayer(list[PlayerController.currentSequence], e).start();
			}
			PlayerController.enableAllButtons();
			PlayerController.pause.setText("暂停");
		} catch (JavaLayerException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
	}
}
