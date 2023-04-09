package musicWindows;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.*;

import buttons.PlayerStart;

public class MainWindow {
	
	public static File loadSongDirectory() {
		String usrdir = System.getProperty("user.dir");
		File config = new File(usrdir + "/.config");
		File songdir = null;
		Properties pro = new Properties();
		if(config.exists()) {
			
			InputStream input;
			try {
				input = new FileInputStream(config);
				pro.load(input);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			songdir = new File(pro.getProperty("songdir"));
		} else {
			songdir = new File("./song");
			try {
				config.createNewFile();
				pro.setProperty("songdir", songdir.getAbsolutePath());
				FileOutputStream output = new FileOutputStream(config);
				pro.store(output, "config");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return songdir;
	}

	public static void main(String[] args) {
		
		File songdir = loadSongDirectory();
		
		JFrame mainwindow = new JFrame("Music Listener");
		mainwindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();//获得显示器大小对象
		Dimension frameSize = mainwindow.getSize();			//获得窗口大小对象
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
		pause.setEnabled(false);
		last.setEnabled(false);
		next.setEnabled(false);
		

		
		JPanel songlist = new JPanel();
		songlist.setBackground(new Color(175, 175, 175));
		mainwindow.add(songlist, "Center");
		int sq = 1;
		for(String s : songdir.list((FilenameFilter) new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String name) {
				if(name.endsWith(".mp3"))
					return true;
				else
					return false;
			}
		}))
		{
			JPanel song = new JPanel();
			JLabel sequence = new JLabel(""+sq);
			sq++;
			sequence.setPreferredSize(new Dimension(25, 25));
			song.add(sequence, 0);
			int division = s.indexOf('-');
			
			JLabel songname = new JLabel(s.substring(division+1, s.length()-4));
			song.setPreferredSize(new Dimension(900, 30));
			song.add(songname);
			JLabel singers = new JLabel(s.substring(0, division));
			singers.setPreferredSize(new Dimension(250, 30));
			song.add(singers);
			songname.setPreferredSize(new Dimension(400, 30));
			JButton playSong = new JButton("播放");
			playSong.setPreferredSize(new Dimension(60, 25));
			song.add(playSong);
			playSong.addActionListener(new PlayerStart(songdir.getName()+'/'+s, pause));
			JButton addToFavour = new JButton("+");
			addToFavour.setPreferredSize(new Dimension(60, 25));
			song.add(addToFavour);
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


