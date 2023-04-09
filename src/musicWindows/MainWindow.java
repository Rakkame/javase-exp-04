package musicWindows;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.*;

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
		
		//设置主窗口
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();//获得显示器大小对象
		Dimension frameSize = mainwindow.getSize();			//获得窗口大小对象
		frameSize.width = displaySize.width *2/3;			//窗口的大小设置为显示器长宽的三分之二
		frameSize.height = displaySize.height *2/3;
		mainwindow.setSize(frameSize);
		mainwindow.setLocation((displaySize.width /6), (displaySize.height /6)); //设置窗口居中显示器显示
		
		//初始化播放控制栏
		PlayerController playctrl = new PlayerController(new Color(0, 200, 140));
		mainwindow.add(playctrl, "South");
		
		//初始化曲库显示栏
		SongListWindow songlist = new SongListWindow(new Color(175, 175, 175), songdir, playctrl);
		mainwindow.add(songlist, "Center");
		
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


