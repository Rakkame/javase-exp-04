package listeners;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Music;
import model.MusicSheet;
import musicWindows.MainWindow;

public class MouseSelect implements MouseListener {
	
	private MusicSheet sheet = null;
	private Music music = null;
	private File dir = null;
	
	private static Border selected;
	private static Border unselected;
	
	static {
	selected = new LineBorder(new Color(0,0,0));
	unselected = new EmptyBorder(1, 1, 1, 1);
	}
	
	public MouseSelect() {}
	
	public MouseSelect(MusicSheet sheet) {
		this.sheet = sheet;
	}
	public MouseSelect(Music music) {
		this.music = music;
	}
	public MouseSelect(File file) {
		this.dir = file;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(sheet != null) {
			try {
				MainWindow.songListPanel.resetAll(dir, sheet);
				MainWindow.songListPanel.repaint();
				MainWindow.mainwindow.setVisible(true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(music != null) {
			//todo
		}else if(dir != null){
			//todo
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//nothing
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//nothing
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JComponent cmp = (JComponent) e.getComponent();
		cmp.setBorder(selected);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JComponent cmp = (JComponent) e.getComponent();
		cmp.setBorder(unselected);
	}

}
