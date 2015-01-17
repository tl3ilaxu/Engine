package com.tleilaxu.graphics.images;

public class ImageEditor {
	
	public static Image resizeImage(Image image,int w2,int h2) {
	    int[] temp = new int[w2*h2];
	    int w1 = image.getWidth();
	    int h1 = image.getHeight();
	    int x_ratio = (int)((w1<<16)/w2) +1;
	    int y_ratio = (int)((h1<<16)/h2) +1;
	    int x2, y2 ;
	    for (int i=0;i<h2;i++) {
	        for (int j=0;j<w2;j++) {
	            x2 = ((j*x_ratio)>>16) ;
	            y2 = ((i*y_ratio)>>16) ;
	            temp[(i*w2)+j] = image.getPixels()[(y2*w1)+x2] ;
	        }                
	    }                
	    return new Image(w2, h2, temp);
	}
	public static Image blendImages(Image... images){
		return null;
		//TODO: fix it
	}
	public static Image getRedChannel(Image image){
		return null;
		//TODO: fix this
	}
	public static Image getGreenChannel(Image image){
		return null;
		//TODO: fix this
	}
	public static Image getBlueChannel(Image image){
		return null;
		//TODO: fix this
	}
}
