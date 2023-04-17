package functions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration extends File {

	private static final long serialVersionUID = 2068576379387362808L;
	public static String sourcePath;
	public static File config;
	public static File musicdir;
	public static File iconsdir;
	public static String serverURL = "http://119.167.221.16:38080/music.server";

	public Configuration(String pathname) throws IOException {
		super(pathname);
		if(!this.exists())
			this.mkdirs();
		Configuration.config = new File(pathname + File.separator + ".config");
		Configuration.musicdir = new File(pathname + File.separator + "song");
		Configuration.iconsdir = new File(pathname + File.separator + "icons");
		if(!Configuration.config.exists())
			this.initConfig();
		if(!Configuration.iconsdir.exists())
			Configuration.iconsdir.mkdir();
		if(!Configuration.musicdir.exists())
			Configuration.musicdir.mkdir();
	}
	
	public static void loadConfig(Properties pro) {
		try {
			FileOutputStream output = new FileOutputStream(Configuration.config);
			pro.store(output, null);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void initConfig() throws IOException {
		Configuration.config.createNewFile();
		Properties pro = new Properties();
		pro.setProperty("database.path", this.getCanonicalPath());
		pro.setProperty("serverURL", serverURL);
		FileOutputStream output = new FileOutputStream(config);
		pro.store(output, null);
	}
	
	public void updateConfig() {
		
	}
	
	public void storeConfig() {
		
	}
}
