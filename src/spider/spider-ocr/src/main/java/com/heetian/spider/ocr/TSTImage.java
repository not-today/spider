package com.heetian.spider.ocr;

import java.awt.image.BufferedImage;

public class TSTImage extends BufferedImage {
	BufferedImage img;
	public TSTImage(BufferedImage img){
		super(0, 0, 0);
		this.img = img;
	}
/*	public TSTImage(int width, int height, int imageType) {
		super(width, height, imageType);
	}
	public TSTImage(ColorModel cm, WritableRaster raster, boolean isRasterPremultiplied, Hashtable<?, ?> properties) {
		super(cm, raster, isRasterPremultiplied, properties);
	}
	public TSTImage(int width, int height, int imageType, IndexColorModel cm) {
		super(width, height, imageType, cm);
	}*/
	
}
