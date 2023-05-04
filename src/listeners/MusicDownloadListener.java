package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;

import functions.Configuration;
import httpclient.FileDownloader;

public class MusicDownloadListener implements ActionListener {
	
	private String md5;
	private String filepath;
	
	public MusicDownloadListener(String md5, String path) {
		this.md5 = md5;
		this.filepath = path;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		File file = new File(filepath);
		if(!file.exists())
			file.mkdirs();
		new Thread() {
			@Override
			public void run() {
				JButton jb = null;
				if(e.getSource() instanceof JButton) {
					jb = (JButton) e.getSource();
					jb.setText("下载中");
				}
				FileDownloader.downloadMusicFile(Configuration.serverURL + "/downloadMusic", md5, filepath);
				jb.setText("下载完成");
				jb.setEnabled(false);
			}
		}.start();
		
	}

}
