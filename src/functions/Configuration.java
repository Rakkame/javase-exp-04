package functions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

	public String sourcePath;
	public static File config;
	public static File musicdir;
	public static File iconsdir;
	public static String serverURL = "http://119.167.221.16:38080/music.server";

	public Configuration() throws IOException {
		sourcePath = this.getPath() + "database";
		Configuration.config = new File(sourcePath  + ".config");
		Configuration.musicdir = new File(sourcePath + File.separator + "song");
		Configuration.iconsdir = new File(sourcePath + File.separator + "icons");
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
		pro.setProperty("database.path", sourcePath);
		pro.setProperty("serverURL", serverURL);
		FileOutputStream output = new FileOutputStream(config);
		pro.store(output, null);
	}
	
	public void updateConfig() {
		
	}
	
	public void storeConfig() {
		
	}
	
	public String getPath()
	{
		String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		if(System.getProperty("os.name").contains("dows"))
		{
			path = path.substring(1,path.length());
		}
		if(path.contains("jar"))
		{
			path = path.substring(0,path.lastIndexOf("."));
			return path.substring(0,path.lastIndexOf("/"));
		}
		if(path.contains("bin/")) {
			return path.replaceAll("bin/", "");
		}
		else
			return path.replace("target/classes/", "");
	}
	
}
