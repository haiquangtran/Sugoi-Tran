package tools;

import java.util.List;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {

	protected List<BufferedImage> images = new ArrayList<BufferedImage>();
	protected List<Long> times = new ArrayList<Long>();
	protected boolean running = false;
	protected long totalTime = 0;

	public Animation(){}

	public void addFrame(BufferedImage img, long stepTime){
		if(img!=null && !running){
			images.add(img);
			times.add(stepTime);
			totalTime += stepTime;
		}else if(running){
			System.err.println("Animations cannot be edited once started");
			try{
			throw new Exception();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}


	protected long startTime = 0;

	public void start(){
		running = true;
		startTime = System.currentTimeMillis();
	}

	public void stop(){
		running = false;
	}

	public void empty(){
		images = new ArrayList<BufferedImage>();
		times = new ArrayList<Long>();
		totalTime = 0;
	}


	/**
	 * If this animation has been started, return the next image in the animation.
	 * Otherwise, return the first image.
	 * @return: a bufferedimage
	 */
	public BufferedImage getImage(){
		if(!running){
			return images.get(0);
		}else{

			long curTime = (System.currentTimeMillis() - startTime) % totalTime ;
			for(int i=0; i<times.size(); i++){
				if(curTime - times.get(i) <= 0){
					return images.get(i);
				}else{
					curTime -= times.get(i);
				}
			}
		}
		return images.get(images.size()-1);
	}

}
