package listeners;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

public class FrameResize implements ComponentListener {
	 //让窗口响应大小改变事件
	
		public JFrame window;
		public static Dimension framsize = null;
		private int count = 0;
		public int frequence = 10;
		
		public FrameResize(JFrame mainwindow) {
			this.window = mainwindow;
			FrameResize.framsize = mainwindow.getSize();
		}
		
		@Override
		public void componentResized(ComponentEvent e) {
			FrameResize.framsize = window.getSize();
			count += 1;
			if(count > frequence) {
				System.out.println("resized:"+FrameResize.framsize);
				count = 0;
			}
	    }
		
		@Override
		public void componentMoved(ComponentEvent e) {}
		@Override
		public void componentShown(ComponentEvent e) {}
		@Override
		public void componentHidden(ComponentEvent e) {}
}
