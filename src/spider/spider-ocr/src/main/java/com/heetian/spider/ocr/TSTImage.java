package com.heetian.spider.ocr;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.util.Hashtable;

public class TSTImage extends BufferedImage {
	public TSTImage(int width, int height, int imageType) {
		super(width, height, imageType);
	}
	public TSTImage(ColorModel cm, WritableRaster raster, boolean isRasterPremultiplied, Hashtable<?, ?> properties) {
		super(cm, raster, isRasterPremultiplied, properties);
	}
	public TSTImage(int width, int height, int imageType, IndexColorModel cm) {
		super(width, height, imageType, cm);
	}
	

}
