package musicWindows;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.*;

import buttons.PlayerController;
import functions.MP3Player;

public class MainWindow {

	public static void main(String[] args) {
		
		JFrame mainwindow = new JFrame("Music Listener");
		mainwindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();//获得显示器大小对象
		Dimension frameSize = mainwindow.getSize();			//获得窗口大小对象
		System.out.println("Screen:" + displaySize);
		frameSize.width = displaySize.width *2/3;			//窗口的大小设置为显示器长宽的三分之二
		frameSize.height = displaySize.height *2/3;
		mainwindow.setSize(frameSize);
		mainwindow.setLocation((displaySize.width /6), (displaySize.height /6)); //设置窗口居中显示器显示
		
		JPanel player = new JPanel();
		mainwindow.add(player, "South");
		Color bg = new Color(0, 200, 140);
		player.setBackground(bg);
		player.setPreferredSize(new Dimension(player.getSize().width, 70));
		JButton pause = new JButton("播放");
		JButton last = new JButton("上一曲");
		JButton next = new JButton("下一曲");
		player.add(last);
		player.add(pause);
		player.add(next);
		File songdir = new File("E:/projectJava/javase-exp-04/song");
		MP3Player mp3 = MP3Player.getmp3(songdir.getName()+'/'+"高橋李依、大西沙織、赤尾ひかる - 涙はみせない (不要流泪).mp3");
		pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!mp3.isPlaying) {
					mp3.start();
					pause.setText("停止");
				}
				else {
					try {
						mp3.isPlaying = false;
						pause.setText("播放");
						MP3Player.player.close();
						mp3.interrupt();
						
					} catch (IllegalMonitorStateException e2) {
						System.err.println("Illegal!");
						e2.printStackTrace();
					}
					
				}
			}
		});
		
		JPanel songlist = new JPanel();
		mainwindow.add(songlist);
		for(String s : songdir.list((FilenameFilter) new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if(name.endsWith(".wav"))
					return true;
				else if(name.endsWith(".mp3"))
					return true;
				else if(name.endsWith(".flac"))
					return true;
				else
					return false;
			}
		})) {
			JPanel song = new JPanel(new GridLayout(1, 5));
			JLabel songname = new JLabel(s.substring(0, s.length()-4));
			songname.setPreferredSize(new Dimension(songlist.getSize().width, 20));
			song.add(songname);
			JButton playSong = new JButton("播放");
			song.add(playSong);
			playSong.addActionListener(new PlayerController(songdir.getName()+'/'+s));
			songlist.add(song);
		}
		
		JPanel jpl = new JPanel(new GridLayout(5, 1));
		JButton jbl1 = new JButton("歌单1");
		JButton jbl2 = new JButton("歌单2");
		JButton jbl3 = new JButton("歌单3");
		JButton jbl4 = new JButton("歌单4");
		JButton jbl5 = new JButton("歌单5");
		jpl.add(jbl1);
		jpl.add(jbl2);
		jpl.add(jbl3);
		jpl.add(jbl4);
		jpl.add(jbl5);
		mainwindow.add(jpl, "West");
		
		mainwindow.setVisible(true);
	}
}


