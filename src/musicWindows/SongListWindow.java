package musicWindows;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JPanel;

import listeners.MouseSelect;
import model.MusicSheet;

public class SongListWindow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2822244591652092213L;
	
	public static String songdir = null;
	public static String[] songlist = null;
	public static String[] md5list = null;
	
	public SongListWindow(Color color) {
		System.out.println("Nothing done");
	}
	
	public SongListWindow(Color color, File songdir) throws IOException {
		super(new GridLayout(0, 1));
		this.setBackground(color);
		SongListWindow.songdir = songdir.getCanonicalPath() + File.separator;
		songlist = songdir.list((FilenameFilter) new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String name) {
				if(name.endsWith(".mp3"))
					return true;
				if(name.endsWith(".wav"))
					return true;
				if(name.endsWith(".ogg"))
					return true;
				else
					return false;
			}
		});
		int sq = 0;
		for(String songfilename : songlist)
		{
			SongInfo song = new SongInfo(++sq, songfilename);
			song.addMouseListener(new MouseSelect());
			this.add(song);
		}
	}
	
	public SongListWindow(Color color, MusicSheet sheet) {
		super(new GridLayout(0, 1));
		this.setBackground(color);
		Map<String, String> musicmap = sheet.getMusicItems();
		int length = musicmap.keySet().toArray().length;
		Iterator<String> iter = musicmap.keySet().iterator();
		md5list = new String[length];
		int i = 0;
		while(iter.hasNext()) {
			md5list[i++] = iter.next();
		}
		String name = null;
		int sq = 0;
		for(String md5 : md5list)
		{
			name = musicmap.get(md5);
			SongInfo song = new SongInfo(++sq, name, md5);
			song.addMouseListener(new MouseSelect());
			this.add(song);
		}
	}

}
