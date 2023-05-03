package functions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import listeners.SwitchSong;

public class PlayerController extends JPanel {

	/**
	 * 版本号
	 */
	private static final long serialVersionUID = -8011781365303920633L;
	
	/**当前播放歌的歌单列表*/
	public static String[] list;
	
	/**在线播放或本地文件播放*/
	public static boolean pathOrMD5 = true;
	
	/**当前播放歌曲的序号*/
	public static int currentSequence = 0;
	
	/**控制按钮：暂停/播放*/
	public static JButton pause = new JButton("播放");
	
	/**控制按钮：上一曲*/
	public static JButton last = new JButton("上一曲");
	
	/**控制按钮：下一曲*/
	public static JButton next = new JButton("下一曲");
	
	/**标签：当前播放歌曲的名称*/
	public static JLabel label = new JLabel("");
	
	/**使所有控制按钮失效*/
	public static void disableAllButtons() {
		pause.setEnabled(false);
		last.setEnabled(false);
		next.setEnabled(false);
	}
	/**使所有控制按钮生效*/
	public static void enableAllButtons() {
		pause.setEnabled(true);
		last.setEnabled(true);
		next.setEnabled(true);
	}

	public PlayerController(Color color) {
		super(new GridLayout(2,1));
		this.setBackground(color);
		this.setPreferredSize(new Dimension(this.getSize().width, 70));
		this.add(label);
		JPanel control = new JPanel();
		control.add(last);
		control.add(pause);
		control.add(next);
		this.add(control);
		PlayerController.disableAllButtons();
		pause.addActionListener(new PauseAL());
		last.addActionListener(new SwitchSong(-1));
		next.addActionListener(new SwitchSong(+1));
	}

}

/**播放暂停监听器*/
class PauseAL implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MP3Player mp3 = MP3Player.last;
		if(mp3.Stopped()) {
			mp3.pause(false);
			PlayerController.pause.setText("暂停");
		}else {
			mp3.pause(true);
			PlayerController.pause.setText("继续");
		}
	}
}
