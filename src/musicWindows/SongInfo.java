package musicWindows;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import listeners.FrameResize;
import listeners.PlayerStart;


public class SongInfo extends JPanel {

	
	private static final long serialVersionUID = 1L;
	
	private Dimension size 			= new Dimension(FrameResize.framsize.width*2/3, 30);//900,30
	private Dimension namesize 		= new Dimension(FrameResize.framsize.width/3, 30);	//400,30
	private Dimension singersize 	= new Dimension(FrameResize.framsize.width/6, 30);	//250,30
	private Dimension buttonsize 	= new Dimension(60, 30);							//60,25
	private Dimension sqsize 		= new Dimension(30, 30);							//25,25
	
	public SongInfo(int sq, String songinfo){
		int division1 = songinfo.indexOf('-');
		int division2 = songinfo.lastIndexOf('.');
		
		this.setPreferredSize(size);
		JLabel sequence = new JLabel(""+sq);
		sequence.setPreferredSize(sqsize);
		this.add(sequence);
		
		JLabel songname = new JLabel(songinfo.substring(division1+1, division2));
		JLabel singers = new JLabel(songinfo.substring(0, division1));
		
		songname.setPreferredSize(namesize);
		singers.setPreferredSize(singersize);
		
		this.add(songname);
		this.add(singers);
		JButton playSong = new JButton("播放");
		playSong.setPreferredSize(buttonsize);
		this.add(playSong);
		playSong.addActionListener(new PlayerStart(sq-1, SongListWindow.songdir+songinfo));
		JButton addToFavour = new JButton("+");
		addToFavour.setPreferredSize(buttonsize);
		this.add(addToFavour);
	}
	
	public SongInfo(int sq, String songinfo, String md5){
		this.setPreferredSize(size);
		JLabel sequence = new JLabel(""+sq);
		sequence.setPreferredSize(sqsize);
		this.add(sequence);
		
		int division1 = songinfo.indexOf('-');
		int division2 = songinfo.lastIndexOf('.');
		String singername = (division1 >= 0)? songinfo.substring(0, division1) : "佚名";
		String songname = (division1 >= 0)? songinfo.substring(division1+1, division2) : songinfo.substring(division1+1);
		JLabel songName = new JLabel(songname);
		JLabel singerName = new JLabel(singername);
		
		songName.setPreferredSize(namesize);
		singerName.setPreferredSize(singersize);
		
		this.add(songName);
		this.add(singerName);
		JButton playSong = new JButton("播放");
		playSong.setPreferredSize(buttonsize);
		playSong.addActionListener(new PlayerStart(md5, sq-1));
		this.add(playSong);
		JButton addToFavour = new JButton("+");
		addToFavour.setPreferredSize(buttonsize);
		this.add(addToFavour);
	}
	
	public void resize() {
		
	}

}
