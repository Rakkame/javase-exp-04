package functions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import musicWindows.PlayerController;

/**
 * 本地音乐播放功能类
 * @author 拾柒
 */
public class MP3Player extends Player {
	
	private boolean stopflag = false;				//暂停标志
	public static MP3Player last = null;			//指向最后一个MP3Player类的实例
	private Thread thread = null;					//音乐播放线程
		
	
	/**
	 * 通过文件流构造MP3Player类对象
	 * @param fileInputStream 文件输出流
	 * @throws JavaLayerException 如果player类构造方法抛出异常
	 */
	public MP3Player(FileInputStream fileInputStream) throws JavaLayerException {
		super(fileInputStream);
		this.thread = new Thread(){
			@Override
			public void run() {
				try {
					play(Integer.MAX_VALUE);
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}
			}
		};
		if(last != null) {
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
	 * @Override 重写close方法，添加音乐播放完毕后的后续处理
	 */
	public synchronized void close()
	{
		PlayerController.pause.setEnabled(false);
		super.close();
	}
	
	/**
	 * 设置暂停标志，控制播放暂停或继续
	 * @param flag true使播放暂停 false使播放继续
	 */
	public void pause(boolean flag) {
		this.stopflag = flag;
	}
	
	/**
	 * 获取暂停标志
	 * @return true表示播放已被暂停
	 */
	public boolean Stopped() {
		return this.stopflag;
	}
}

