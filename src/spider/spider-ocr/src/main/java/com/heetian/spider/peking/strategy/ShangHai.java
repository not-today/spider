package com.heetian.spider.peking.strategy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.heetian.spider.ocr.exception.ImageConverte;
import com.heetian.spider.ocr.util.ImageUtils;
import com.heetian.spider.ocr.util.ResultProcess;
import com.heetian.spider.ocr.util.ValidateType;
/**
 * 江西省验证码识别程序
 * @author tst
 *
 */
public class ShangHai extends AbstractRecognized{
	public ShangHai(){
		init("stencil"+File.separator+"jiangxi",Color.BLACK,distiguingsh_many);
	}
	public static void main(String[] args) {
		ShangHai sh = new ShangHai();
		File dir = new File("C:\\Users\\tst\\Desktop\\pic");
		for(File pic :dir.listFiles()){
			if(!pic.isFile())
				continue;
			try {
				sh.pretreatment(ImageIO.read(pic), null);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}

	}
	Peking_copy pc = new Peking_copy();
	static int x = 1;
	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		int w = img.getWidth();
		int h = img.getHeight();
		img = img.getSubimage(1, 1, w-2, h-2);
		w = img.getWidth();
		h = img.getHeight();
		for(int x=0;x<w;x++){
			for(int y=0;y<h;y++){
				int rgb = img.getRGB(x, y);
				if(ImageUtils.clearColor(rgb, 500))
					img.setRGB(x, y, Color.WHITE.getRGB());
				else
					img.setRGB(x, y, rgb);
			}
		}
		
		/*ImageUtils.smoothFilter(img,Color.WHITE,7);
		try {
			img = recgLine(img);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ImageUtils.smoothFilter(img,Color.WHITE,7);
		img = ImageUtils.kmeansFilter(img);
		ImageUtils.smoothFilter(img,Color.WHITE,7);
		try {
			ImageUtils.copyFileImg(img, new File("C:\\Users\\tst\\Desktop\\pic\\sub1\\"+(x++)+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		return img;
	}
	/**
	 * 识别直线
	 * 
	 * @param bufferedImage
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage recgLine(BufferedImage bufferedImage) throws IOException {

		Date startTime = new Date();

		final int width = bufferedImage.getWidth();
		final int height = bufferedImage.getHeight();

		List<int[]> pixels = new ArrayList<int[]>();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				if (Color.WHITE.getRGB() == bufferedImage.getRGB(w, h))
					continue;
				pixels.add(new int[] { w, h });
			}
		}

		for (int i = 0; i < pixels.size(); i++) {
			// 起始点
			int[] start = pixels.get(i);
			int x0 = start[0], y0 = start[1];
			for (int j = pixels.size() - 1; j > i; j--) {
				// 结束点
				int[] end = pixels.get(j);
				int x1 = end[0], y1 = end[1];

				if (x1 == x0 || y1 == y0)
					continue;

				int n = Math.max(Math.abs(x1 - x0), Math.abs(y1 - y0));
				if (n < 30)
					continue;

				List<int[]> list = ddaLine(x0, y0, x1, y1);
				int count = 0;
				for (int[] pixel : list) {
					if (Color.WHITE.getRGB() == bufferedImage.getRGB(pixel[0], pixel[1]))
						count++;
				}

				if (count > 5)
					continue;

				for (int[] pixel : list) {
					bufferedImage.setRGB(pixel[0], pixel[1], Color.WHITE.getRGB());
				}

			}

		}
		Date endTime = new Date();
		System.out.println(endTime.getTime() - startTime.getTime());
		return bufferedImage;
	}
	/**
	 * 根据两个点像素坐标，计算直线上所有像素点
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static List<int[]> ddaLine(int x0, int y0, int x1, int y1) {
		List<int[]> pixels = new ArrayList<int[]>();
		// 像素差
		int dx = x1 - x0, dy = y1 - y0;
		// 像素个数
		int n = Math.max(Math.abs(dx), Math.abs(dy));
		// 像素步长
		float stepx = (float) dx / n, stepy = (float) dy / n;

		for (int k = 0; k <= n; k++) {
			pixels.add(new int[] { Math.round(x0 + k * stepx), Math.round(y0 + k * stepy) });
		}

		return pixels;
	}
	@Override
	protected BufferedImage[] division(BufferedImage img, Map<String, String> recogScop) throws ImageConverte {
		List<BufferedImage> list = projectionCluster(img, Color.WHITE.getRGB());
		return listToArray(list);
	}
	@Override
	protected ResultProcess distinguish(Map<String, String> recogScop,String picName, String picType, BufferedImage... imgs) throws ImageConverte {
		/*String result = "";
		for(BufferedImage img:imgs){
			@SuppressWarnings("unchecked")
			String tmp = ImageUtils.recognizedByPixelMatch(ImageUtils.getPixel(img, Color.BLACK.getRGB()), stencil);
			if(tmp.equals("+")){
				if(!result.contains(tmp))
					result = result + tmp;
			}else{
				result = result + tmp;
			}
		}
		if(result.trim().equals(""))
			return null;
		char chs[] = result.toCharArray();
		if(chs.length!=3)
			throw new ImageConverte("识别结果字符数不等于3");*/
		String chs[] = {"0","+","0"};
		return ResultProcess.newInstance(ValidateType.count, chs);
	}
	public char[] convert(char[] results){
		char[] rs = new char[3];
		if(results[4]=='n'){
			rs[0] = results[0];
			rs[1] = results[1];
			rs[2] = results[2];
		}else if(results[0]=='n'){
			if(results[1]=='-'){
				rs[0] = results[4];
				rs[1] = '+';
				rs[2] = results[2];
			}else{
				rs[0] = results[4];
				rs[1] = '-';
				rs[2] = results[2];
			}
		}else if(results[2]=='n'){
			if(results[1]=='-'){
				rs[0] = results[0];
				rs[1] = '+';
				rs[2] = results[4];
			}else{
				rs[0] = results[0];
				rs[1] = '-';
				rs[2] = results[4];
			}
		}
		return rs;
	}
}
