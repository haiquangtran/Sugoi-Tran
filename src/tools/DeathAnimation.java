package tools;

import java.awt.image.BufferedImage;

public class DeathAnimation extends Animation {

	@Override
	public BufferedImage getImage(){
		if(!running){
			return images.get(0);
		}else{
			long curTime = (System.currentTimeMillis() - startTime) ;
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
