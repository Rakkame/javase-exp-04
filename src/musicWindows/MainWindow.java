package musicWindows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import functions.Configuration;
import functions.PlayerController;
import listeners.FrameResize;


public class MainWindow {
	
	public static JFrame mainwindow = new JFrame("Ringo Music Player");
	public static JScrollPane musicSpane = null;
	
	public static void main(String[] args) throws IOException {
		
		
		
		Configuration data = new Configuration();
		data.updateConfig();
		
		mainwindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		//设置主窗口
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();//获得显示器大小对象
		Dimension frameSize = mainwindow.getSize();			//获得窗口大小对象
		frameSize.width = displaySize.width *2/3;			//窗口的大小设置为显示器长宽的三分之二
		frameSize.height = displaySize.height *2/3;
		mainwindow.setSize(frameSize);
		mainwindow.setLocation((displaySize.width /6), (displaySize.height /6)); //设置窗口居中显示器显示
		mainwindow.addComponentListener(new FrameResize(mainwindow));		//添加窗口大小监听器
		
		//初始化播放控制栏
		PlayerController playctrl = new PlayerController(new Color(0, 200, 140));
		mainwindow.add(playctrl, "South");
		
		//初始化歌单内歌曲列表显示栏
		SongListWindow songListPanel = new SongListWindow(new Color(175, 175, 175), Configuration.musicdir);
		musicSpane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
												JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		musicSpane.getVerticalScrollBar().setUnitIncrement(10);
		musicSpane.setViewportView(songListPanel);
		
		mainwindow.add(musicSpane, "Center");
		
		//初始化歌单列表显示栏
		JScrollPane sheetSpane = new JScrollPane(new SheetListWindow(new Color(123,210,170), Configuration.musicdir),
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sheetSpane.getVerticalScrollBar().setUnitIncrement(10);
		mainwindow.add(sheetSpane, "West");
		
		mainwindow.setVisible(true);

	}
	
	
	

}




