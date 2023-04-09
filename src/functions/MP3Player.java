package functions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * 本地音乐播放功能类
 */
public class MP3Player extends Player {
	
	private boolean stopflag = false;				//暂停标志
	public static MP3Player last = null;			//指向最后一个MP3Player类的实例
	private Thread thread = new Thread(){			//音乐播放线程
		@Override
		public void run() {
			try {
				play(Integer.MAX_VALUE);
			} catch (JavaLayerException e) {
				e.printStackTrace();
			}
		}
	};
	
	/**
	 * 通过文件流构造MP3Player类对象
	 * @param fileInputStream 文件输出流
	 * @throws JavaLayerException 如果player类构造方法抛出异常
	 */
	public MP3Player(FileInputStream fileInputStream) throws JavaLayerException {
		super(fileInputStream);
		if(last != null) {
			last.thread.interrupt();
			last.close();
			last = null;
		}
		last = this;
	}

	
	/**
	 * 通过文件名构造MP3Player类对象
	 * @param filename 文件名
	 * @throws FileNotFoundException 如果filename指向的文件不存在
	 * @throws JavaLayerException 如果player类构造方法抛出异常
	 */
	public MP3Player(String filename) throws FileNotFoundException, JavaLayerException {
		this(new FileInputStream(filename));
	}
	
	
	/**
	 * @Override 重写父类的播放方法，使其在新线程中运行
	 */
	public void play() throws JavaLayerException {
		this.thread.start();
	}

	
	/**
	 * @Override 重写父类的播放帧方法，添加暂停标志
	 */
	protected boolean decodeFrame() throws JavaLayerException {
		while(this.stopflag) {
			try {
				Thread.sleep(500);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		return super.decodeFrame();
	}
	
	/**
	 * 设置暂停标志，控制播放暂停或继续
	 * @param flag true使播放暂停 false使播放继续
	 */
	public void pause(boolean flag) {
		this.stopflag = true;
	}
	
	/**
	 * 获取暂停标志
	 * @return true表示播放已被暂停
	 */
	public boolean Stopped() {
		return this.stopflag;
	}
}

