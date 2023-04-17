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
	
	public SongListWindow(Color color) {
		
	}
	
	public SongListWindow(Color color, File songdir) throws IOException {
		this.setBackground(color);
		SongListWindow.songdir = songdir.getCanonicalPath() + '/';
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
			sq++;
			SongInfo song = new SongInfo(sq, songfilename);
			song.addMouseListener(new MouseSelect());
			this.add(song);
		}
		this.setLayout(new GridLayout(sq, 1));
	}
	
	public SongListWindow(Color color, MusicSheet sheet) {
		this.setBackground(color);
		Map<String, String> musicmap = sheet.getMusicItems();
		Iterator<String>  MD5bag = musicmap.keySet().iterator();
		String md5 = null;
		String name = null;
		int sq = 0;
		while(MD5bag.hasNext()) {
			
			md5 = MD5bag.next();
			name = musicmap.get(md5);
			
			SongInfo song = new SongInfo(++sq, name, md5);
			song.addMouseListener(new MouseSelect());
			this.add(song);
		}
	}
	
	public void resetAll(File songdir, MusicSheet sheet) throws IOException {
		this.removeAll();
		if(songdir != null) {
			SongListWindow.songdir = songdir.getCanonicalPath() + '/';
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
				sq++;
				SongInfo song = new SongInfo(sq, songfilename);
				song.addMouseListener(new MouseSelect());
				this.add(song);
			}
			this.setLayout(new GridLayout(sq, 1));
		}else {
			Map<String, String> musicmap = sheet.getMusicItems();
			Iterator<String>  MD5bag = musicmap.keySet().iterator();
			String md5 = null;
			String name = null;
			int sq = 0;
			while(MD5bag.hasNext()) {
				md5 = MD5bag.next();
				name = musicmap.get(md5);
				SongInfo song = new SongInfo(++sq, name, md5);
				song.addMouseListener(new MouseSelect());
				this.add(song);
			}
		}
	}

}
