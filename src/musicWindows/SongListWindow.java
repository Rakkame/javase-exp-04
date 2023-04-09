package musicWindows;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import buttons.PlayerStart;

public class SongListWindow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2822244591652092213L;
	
	public static String songdir = null;
	public static ArrayList<JButton> songButton = null;
	
	public SongListWindow(Color color, File songdir, PlayerController playctrl) {
		this.setBackground(color);
		SongListWindow.songdir = songdir.getName() + '/';
		String[] songlist = songdir.list((FilenameFilter) new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String name) {
				if(name.endsWith(".mp3"))
					return true;
				if(name.endsWith(".wav"))
					return true;
				if(name.endsWith(".flac"))
					return true;
				else
					return false;
			}
		});
		songButton = new ArrayList<JButton>(songlist.length);
		int sq = 0;
		for(String s : songlist)
		{
			JPanel song = new JPanel();
			song.setPreferredSize(new Dimension(900, 30));
			sq++;
			JLabel sequence = new JLabel(""+sq);
			sequence.setPreferredSize(new Dimension(25, 25));
			song.add(sequence);
			int division1 = s.indexOf('-');
			int division2 = s.lastIndexOf('.');
			
			JLabel songname = new JLabel(s.substring(division1+1, division2));
			JLabel singers = new JLabel(s.substring(0, division1));
			songname.setPreferredSize(new Dimension(400, 30));
			singers.setPreferredSize(new Dimension(250, 30));
			song.add(songname);
			song.add(singers);
			JButton playSong = new JButton("播放");
			songButton.add(playSong);
			playSong.setPreferredSize(new Dimension(60, 25));
			song.add(playSong);
			playSong.addActionListener(new PlayerStart(sq-1, SongListWindow.songdir+s));
			JButton addToFavour = new JButton("+");
			addToFavour.setPreferredSize(new Dimension(60, 25));
			song.add(addToFavour);
			this.add(song);
		}
	}

}
