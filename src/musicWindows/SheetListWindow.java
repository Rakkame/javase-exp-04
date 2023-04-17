package musicWindows;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.httpclient.HttpException;

import httpclient.MusicSheetTaker;
import listeners.MouseSelect;
import model.MusicSheet;

public class SheetListWindow extends JPanel {

	private static final long serialVersionUID = 4323319482781016969L;
	
	List<MusicSheet> list = null;
	
	public SheetListWindow(Color color, File sheetdir) throws HttpException, IOException {
		super(new GridLayout(0, 1));
		this.setBackground(color);
		this.list = MusicSheetTaker.queryMusicSheets("http://119.167.221.16:38080/music.server/queryMusicSheets?type=all", 80);
		
		JLabel nativesheet = new JLabel("本地音乐");
		nativesheet.addMouseListener(new MouseSelect(sheetdir));
		this.add(nativesheet);
		
		for(int i=0; i<list.size(); ++i) {
			MusicSheet sheet = list.get(i);
			
			if(sheet.getMusicItems().isEmpty()) {
				continue;
			}
			
			JLabel sheetLabel = new JLabel(sheet.getName());
			sheetLabel.addMouseListener(new MouseSelect(list.get(i)));
			this.add(sheetLabel);
		}
		this.setSize(this.getWidth()*5/4, this.getHeight());
	}
	
}
