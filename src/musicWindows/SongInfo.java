package musicWindows;

import java.awt.Dimension;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import listeners.FrameResize;
import listeners.MusicDownloadListener;
import listeners.PlayerStart;


public class SongInfo extends JPanel {

	
	private static final long serialVersionUID = 1L;
	
	private Dimension size 			= new Dimension(FrameResize.framsize.width*4/5, 30);//900,30
	private Dimension namesize 		= new Dimension(FrameResize.framsize.width/3, 30);	//400,30
	private Dimension singersize 	= new Dimension(FrameResize.framsize.width/6, 30);	//250,30
	private Dimension buttonsize 	= new Dimension(90, 30);							//60,25
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
	}
	
	public SongInfo(int sq, String songinfo, String md5, String directory){
		this.setPreferredSize(size);
		JLabel sequence = new JLabel(""+sq);
		sequence.setPreferredSize(sqsize);
		this.add(sequence);
		
		int division1 = songinfo.indexOf('-');
		int division2 = songinfo.lastIndexOf('.');
		String singername = (division1 >= 0)? songinfo.substring(0, division1) : "佚名";
		String songname = (division1 >= 0|| division2 >= 0)? songinfo.substring(division1+1, division2) : songinfo.substring(division1+1);
		JLabel songName = new JLabel(songname);
		JLabel singerName = new JLabel(singername);
		songName.setPreferredSize(namesize);
		singerName.setPreferredSize(singersize);
		this.add(songName);
		this.add(singerName);
		
		JButton playSong = new JButton("播放");
		playSong.setPreferredSize(buttonsize);
		playSong.addActionListener(new PlayerStart(sq-1, songname, md5));
		this.add(playSong);
		
		File file = new File(directory + File.separator + songinfo);
		JButton download = new JButton();
		if(!file.exists()) {
			download.setText("下载");
			download.addActionListener(new MusicDownloadListener(md5, directory));
		}else {
			download.setText("下载完成");
			download.setEnabled(false);
		}
		download.setPreferredSize(buttonsize);
		this.add(download);
	}
	
	public void resize() {
		
	}

}
