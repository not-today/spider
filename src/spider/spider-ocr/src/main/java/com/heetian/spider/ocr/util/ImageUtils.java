package com.heetian.spider.ocr.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import com.heetian.spider.ocr.util.PixelInfo.Point;
import com.heetian.spider.peking.strategy.CallUtil;

public class ImageUtils {
	/**
	 * 重命名文件
	 * @param oldpath
	 * @param oldname
	 * @param newpath
	 * @param newname
	 * @throws IOException
	 */
	public static void renameFile(String oldpath,String oldname,String newpath,String newname) throws IOException{
		checkStrISNull(oldpath,"被重名文件所在目录不能为空");
		checkStrISNull(oldname,"被重名文件名不能为空");
		checkStrISNull(newpath,"重名文件所在目录不能为空");
		checkStrISNull(newname,"重名文件名不能为空");
        if(oldname.equals(newname))
        	throw new IOException("被重名文件名和重名文件名相同");
        File oldtmp = new File(oldpath);
        if(!oldtmp.exists())
        	oldtmp.mkdirs();
        File oldfile=new File(oldtmp,oldname); 
        if(!oldfile.exists()){
        	throw new IOException("被重名文件不存在");
        }
        File newtmp = new File(newpath);
        if(!newtmp.exists())
        	newtmp.mkdirs();
        File newfile=new File(newtmp,newname); 
        if(newfile.exists())
        	throw new IOException("重名文件已经存在");
        oldfile.renameTo(newfile); 
	}
	/**
	 * 重命名文件
	 * @param oldfile
	 * @param newfile
	 * @throws IOException
	 */
	public static void renameFile(File oldfile,File newfile) throws IOException{
		if(oldfile==null)
			throw new IOException("被重名文件不能为空");
		if(newfile==null)
			throw new IOException("重名文件不能为空");
		renameFile(oldfile.getParent(), oldfile.getName(), newfile.getParent(), newfile.getName());
	}
	/**
	 * 重命名文件
	 * @param oldfile
	 * @param newfile
	 * @throws IOException
	 */
	public static void renameFile(String oldfile,String newfile) throws IOException{
		if(oldfile==null||"".equals(oldfile.trim()))
			throw new IOException("被重名文件不能为空");
		if(newfile==null||"".equals(newfile.trim()))
			throw new IOException("重名文件不能为空");
		File oldf = new File(oldfile);
		File newf = new File(newfile);
		renameFile(oldf.getParent(), oldf.getName(), newf.getParent(), newf.getName());
	}
	public static void copyFile(String oldpath,String oldname,String newpath,String newname,boolean force) throws IOException {
		checkStrISNull(oldpath,"被复制文件所在目录不能为空");
		checkStrISNull(oldname,"被复制文件名不能为空");
		checkStrISNull(newpath,"复制文件所在目录不能为空");
		checkStrISNull(newname,"复制文件名不能为空");
        if(oldname.equals(newname))
        	throw new IOException("被复制文件名和复制文件名相同");
        File oldtmp = new File(oldpath);
        if(!oldtmp.exists())
        	oldtmp.mkdirs();
        File oldfile=new File(oldtmp,oldname); 
        if(!oldfile.exists()){
        	throw new IOException("被复制文件不存在");
        }
        File newtmp = new File(newpath);
        if(!newtmp.exists())
        	newtmp.mkdirs();
        File newfile=new File(newtmp,newname); 
    	if(newfile.exists()){
    		if(!force)
    			throw new IOException("复制文件已经存在");
    		newfile.delete();
    	}
    	writeFile(oldfile, newfile);
	}
	public static void copyFileImg(BufferedImage img,String formate,String newpath,String newname,boolean force) throws IOException {
		checkStrISNull(newpath,"复制文件所在目录不能为空");
		checkStrISNull(newname,"复制文件名不能为空");
		if(img==null)
			throw new IOException("被复制图片为null");
		File newtmp = new File(newpath);
        if(!newtmp.exists())
        	newtmp.mkdirs();
        File newfile=new File(newtmp,newname); 
		if(!force){
			if(newfile.exists())
				throw new IOException("复制图片已经存在");
		}
		ImageIO.write(img, formate, newfile);
	}
	public static void copyFileImg(BufferedImage img,String formate,String newpath,String newname) throws IOException {
		copyFileImg(img, formate, newpath, newname, true);
	}
	public static void copyFileImg(BufferedImage img,String newpath,String newname) throws IOException {
		copyFileImg(img, "png", newpath, newname, true);
	}
	public static void copyFileImg(BufferedImage img,File file) throws IOException {
		copyFileImg(img, "png", file.getParent(), file.getName(), true);
	}
	public static void copyFileImgshield(BufferedImage img,File file){
		try {
			copyFileImg(img, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void checkStrISNull(String str,String meg) throws IOException {
		if(str==null||"".equals(str.trim()))
			throw new IOException(meg);
	}
	public static void copyFile(File oldfile,File newfile,boolean force) throws IOException{
		if(oldfile==null)
			throw new IOException("被复制文件不能为空");
		if(newfile==null)
			throw new IOException("复制文件不能为空");
		copyFile(oldfile.getParent(), oldfile.getName(), newfile.getParent(), newfile.getName(),force);
	}
	public static void copyFile(String oldfile,String newfile,boolean force) throws IOException{
		if(oldfile==null||"".equals(oldfile.trim()))
			throw new IOException("被复制文件不能为空");
		if(newfile==null||"".equals(newfile.trim()))
			throw new IOException("复制文件不能为空");
		File oldf = new File(oldfile);
		File newf = new File(newfile);
		copyFile(oldf.getParent(), oldf.getName(), newf.getParent(), newf.getName(),force);
	}
	private static void writeFile(File read,File write) throws IOException{
		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(read));
		BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(write));
		byte[] buff = new byte[1024*1024];
		int leng = 0;
		try {
			while((leng=bin.read(buff))!=-1){
				bout.write(buff,0,leng);
			}
			bout.flush();
		} catch (IOException e) {
			throw new IOException(e);
		}finally{
			if(bin!=null){
				try {
					bin.close();
				} catch (IOException e) {
					if(bout!=null){
						try {
							bout.close();
						} catch (IOException e1) {
						}
					}
				}
			}
		}
	}
	/**
	 * 移除背景色
	 * 使用条件：
	 * 1背景色是纯色
	 * 
	 * @param image
	 * @throws IOException
	 */
	public static void clearBackground(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		// 按每个颜色统计其出现次数
		Map<Integer, PixelInfo> map = new HashMap<Integer, PixelInfo>();
		int index = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int rgb = image.getRGB(x, y);
				PixelInfo stat = map.get(rgb);
				if (map.get(rgb) == null) {
					map.put(rgb, new PixelInfo(rgb, x, y, index++));
					continue;
				}
				// 添加坐标点(GDVerfyStat实体类中计算了像素点数量)
				stat.addPoint(x, y);
			}
		}
		int sum = 0;
		int pixelTypeNum = 0;
		int max_count = 0;
		int max_key = 0;
		for (int rgb : map.keySet()) {
			PixelInfo pixelInfo = map.get(rgb);
			int cur_count = pixelInfo.getCount();
			sum = sum+cur_count;
			pixelTypeNum = pixelTypeNum +1;
			if(max_count<cur_count){
				max_count = cur_count;
				max_key = rgb;
			}
		}
		List<PixelInfo> list = new ArrayList<PixelInfo>();
		list.add(map.remove(max_key));//添加背景色到集合
		int avg_count = (sum-max_count)/(pixelTypeNum-1);//求的除去背景色外的像素点出现的次数的平均数
		
		for (int rgb : map.keySet()) {
			PixelInfo pixelInfo = map.get(rgb);
			int cur_count = pixelInfo.getCount();
			if(cur_count<avg_count){//几个关键字符的颜色肯定大于像素点的平局次数（除去背景色）
				list.add(pixelInfo);
			}
		}
		for(PixelInfo pixelTmp :list){
			for (Point p : pixelTmp.getPoints()) {
				image.setRGB(p.getX(), p.getY(), 0xFFFFFF);
			}
		}
		/*List<PixelInfo> list = new ArrayList<PixelInfo>();
		for (int rgb : map.keySet()) {
			list.add(map.get(rgb));
		}
		Collections.sort(list);
		// 清除背景色
		for (Point p : list.get(0).getPoints()) {
			bufferedImage.setRGB(p.getX(), p.getY(), 0xFFFFFF);
		}*/
	}
	/**
	 * 降噪。原理：一个像素点周边两圈的像素点中的像素rgb颜色值等于当前像素点值的数量少于5，则认为该点为噪点
	 * @param image
	 * @throws IOException
	 */
	public static void filterBySmooth(BufferedImage image){
		int width = image.getWidth();
		int height = image.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				int rgb = image.getRGB(w, h);
				// 周围2圈相同颜色像素点个数
				int count = 0;
				for (int x = w - 2; x <= w + 2; x++) {
					for (int y = h - 2; y <= h + 2; y++) {
						if (x < 0 || y < 0 || x > width - 1 || y > height - 1) {
							continue;
						}
						count += image.getRGB(x, y) == rgb ? 1 : 0;
					}
				}
				if (count <= 5)
					image.setRGB(w, h, 0xFFFFFF);
			}
		}
	}
	/**
	 * 降噪。原理：一个像素点周边两圈的像素点中的像素rgb颜色值等于当前像素点值的数量少于pixelNumber，则认为该点为噪点
	 * @param image
	 * @throws IOException
	 */
	public static void filterBySmooth(BufferedImage image,int pixelNumber){
		int width = image.getWidth();
		int height = image.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				int rgb = image.getRGB(w, h);
				// 周围2圈相同颜色像素点个数
				int count = 0;
				for (int x = w - 2; x <= w + 2; x++) {
					for (int y = h - 2; y <= h + 2; y++) {
						if (x < 0 || y < 0 || x > width - 1 || y > height - 1) {
							continue;
						}
						count += image.getRGB(x, y) == rgb ? 1 : 0;
					}
				}
				if (count <= pixelNumber)
					image.setRGB(w, h, 0xFFFFFF);
			}
		}
	}
	/**
	 * 切割图片，并获得图片子集
	 * 原理：处理的是二值化图片，且文字颜色各不相同，由图片宽为x逐次扫描到的字符为顺序切只获取前四个字符，第一个字符为背景舍去
	 * @param image
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage[] subImageByRGB(BufferedImage image){
		int width = image.getWidth();
		int height = image.getHeight();
		// 按每个颜色统计其出现次数、最小X坐标、最大X坐标、最小Y坐标、最大Y坐标
		Map<Integer, PixelInfo> map = new HashMap<Integer, PixelInfo>();
		int index = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int rgb = image.getRGB(x, y);
				PixelInfo stat = map.get(rgb);
				if (map.get(rgb) == null) {
					map.put(rgb, new PixelInfo(rgb, x, y, index++));
					continue;
				}
				// 记录每种颜色像素点坐标(GDVerfyStat中计算了最小坐标、最大坐标、自左向右出现顺序、坐标点个数等值)
				stat.addPoint(x, y);
			}
		}
		// 根据次数将颜色进行排序
		List<PixelInfo> list = new ArrayList<PixelInfo>();
		for (int rgb : map.keySet()) {
			list.add(map.get(rgb));
		}
		Collections.sort(list);
		// 背景色（白色）最多，其次就是文字，最后是未清除的噪点
		BufferedImage subImages[] = new BufferedImage[3];
		int completeSubImageFlag = 0;
		for (int i = 1; i < list.size(); i++) {
			if(i>5){
				break;
			}
			PixelInfo stat = list.get(i);
			if(stat.getIndex()==1||stat.getIndex()==2||stat.getIndex()==3){
				int w = stat.getMaxX() - stat.getMinX() + 1;
				int h = stat.getMaxY() - stat.getMinY() + 1;
				BufferedImage subImage = new BufferedImage(w, h,BufferedImage.TYPE_BYTE_BINARY);
				for (Point point : stat.getPoints()) {
					subImage.setRGB(point.getX() - stat.getMinX(),point.getY() - stat.getMinY(), 0xFFFFFF);
				}
				int id = stat.getIndex();
				if(id==1){
					subImages[stat.getIndex()-1] = subImage;
					completeSubImageFlag++;
				}else if(id==2){
					subImages[stat.getIndex()-1] = subImage;
					completeSubImageFlag++;
				}else if(id==3){
					subImages[stat.getIndex()-1] = subImage;
					completeSubImageFlag++;
				}
			}
		}
		if(completeSubImageFlag==3)
			return subImages;
		else
			return null;
	}
	/**
	 * 获得一张图片的像素点，并且存放到二维数组中，且：一维数组中存放的是height不动，width变化的像素数组
	 * @param image
	 * @return
	 */
	public static int[][] getPixel(BufferedImage image,int color_rgb) {
		if(image==null)
			return null;
		int width = image.getWidth();
		int height = image.getHeight();
		int[][] pixel = new int[height][width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if(color_rgb==image.getRGB(x, y)){
					pixel[y][x] = 1;
				}
			}
		}
		//printImage(pixel);
		return pixel;
	}
	public static String recognizedByPixelMatch(int[][] pixel,Map<String, List<int[][]>> stencil){
		if(pixel==null)
			return null;
		Set<String> keySet = stencil.keySet();
		double max = 0;
		String content = "";
		for(String key :keySet){
			List<int[][]> stencilPixels_SingleChars = stencil.get(key);
			double maxProba = 0;
			for(int x=0;x<stencilPixels_SingleChars.size();x++){
				int[][] stencilPixels_SingleChar = stencilPixels_SingleChars.get(x);
				double currentProba = matchPixel(stencilPixels_SingleChar, pixel);
				if(maxProba<currentProba){//同一类型的字符，某字符与模版字符几个匹配。则只需匹配第一个就行。因此不需要等于号
					maxProba = currentProba;
				}
			}
			if(max<=maxProba){//此处多了个等于号。如果某个字符的数组与模版数组中的几个模版获得的概率一致，则第一个会被后面的字符模版替换掉。
				max = maxProba;
				content = key;
			}
		}
		if(max<=0.4)
			return "";
		return content;
	}
	public static double matchPixel(int[][] stencil,int pixel[][]){
		int total = 0;
		int match_total = 0;
		int height = stencil.length<pixel.length?stencil.length:pixel.length;
		for (int y = 0; y <height; y++) {
			int width = stencil[y].length<pixel[y].length?stencil[y].length:pixel[y].length;
			for (int x = 0; x <width; x++) {
				if(stencil[y][x]==pixel[y][x]){
					match_total++;
				}
				total++;
			}
		}
		return ((double)match_total)/total;
	}
	/**
	 * 去除模版重复的字符图片
	 * @param dirPath
	 */
	public static void removeStencil(String dirPath) {
		File dir = new File(dirPath);
		for (File file1 : dir.listFiles()) {
			if (file1.exists() && file1.isFile()) {
				try {
					BufferedImage image1 = ImageIO.read(file1);
					int[][] file1pixel = ImageUtils.getPixel(image1, Color.BLACK.getRGB());
					for (File file2 : dir.listFiles()) {
						if (!file1.getName().equals(file2.getName()) && file2.isFile()) {
							try {
								BufferedImage image2 = ImageIO.read(file2);
								int[][] file2pixe = ImageUtils.getPixel(image2, Color.BLACK.getRGB());
								double tmp = ImageUtils.matchPixel(file2pixe, file1pixel);
								if(tmp==1){
									file2.delete();
									System.out.println("删除："+file2.getName());
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	/**
	 * 打印二值化图片的像素
	 * @param pixel
	 */
	public static void printImage(int[][] pixel){
		for (int y = 0; y < pixel.length; y++) {
			for (int x = 0; x < pixel[y].length; x++) {
				if(pixel[y][x]==1){
					System.out.print("*");
				}else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
	public static BufferedImage readImage(String path) throws IOException{
		InputStream in = CallUtil.class.getClassLoader().getResourceAsStream(path);
		if(in==null){
			try {
				in = new FileInputStream(path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return ImageIO.read(in);
	}
	/**
	 * 获取父目录下的所有资源文件File对象，先搜索相对路劲，然后搜索绝对路劲
	 * @param dirPath
	 * @return
	 */
	public static File[] foundFiles(String dirPath){
		if(dirPath==null||"".equals(dirPath))
			return null;
		File dirs = new File(dirPath);
		File[] files = dirs.listFiles();
		if(files==null||files.length==0){
			ClassLoader cl = ImageUtils.class.getClassLoader();
			if(dirPath.contains("\\"))
				dirPath = dirPath.replaceAll("\\\\", "/");
			URL url = cl.getResource(dirPath);
			if(url==null)
				return null;
			dirs = new File(url.getPath());
		}
		return dirs.listFiles();
	}
	/**
	 * 
	 * @param rgb
	 * @param matchValue  当此值大于等于383时，则rgb获得的颜色代码大于等于matchRGBCode，返回true，若此值小于383时，则rgb获得的颜色代码小于matchRGBCode，返回true
	 * @return
	 */
	public static boolean clearColor(int rgb, int matchValue) {
		int cur_rgbValue = getColorRGBCode(rgb);
		if (matchValue > (255 + 255 + 255 + 1) / 2) {
			if (cur_rgbValue >= matchValue) {
				return true;
			}
		} else {
			if (cur_rgbValue <= matchValue) {
				return true;
			}
		}
		return false;
	}
	private static int getColorRGBCode(int rgb){
		Color color = new Color(rgb);
		int rgbSub = color.getRed() + color.getGreen() + color.getBlue();
		return rgbSub;
	}
	/**
	 * 获取数组不等于0的下标首尾开始位置
	 * @param image
	 * @param isXorY  x、y。x为width上的。y为height上的
	 * @return
	 */
	public static LinkedList<Integer> indexFlag(BufferedImage image, char isXorY) {
		int[] arr = haveFunctionPixel(image, isXorY);
		LinkedList<Integer> indexs = new LinkedList<>();
		boolean first = true;
		boolean type = true;
		for (int x = 0; x < arr.length; x++) {
			if (first) {
				if (arr[x] != 0) {
					indexs.add(x);
					first = false;
				}
			} else {
				if (type) {
					if (arr[x] == 0) {
						indexs.add(x - 1);
						type = false;
					} else {
						if (x == arr.length - 1) {
							indexs.add(x);
							type = false;
						}
					}
				} else {
					if (arr[x] != 0) {
						indexs.add(x);
						type = true;
					}
				}
			}
		}
		return indexs;
	}
	public static int[] haveFunctionPixel(BufferedImage image, char isXorY) {
		int width = image.getWidth();
		int height = image.getHeight();
		int pixels[] = new int[width];
		if (isXorY == 'x') {
			pixels = new int[width];
			for (int x = 0; x < width; x++) {
				int num = 0;
				for (int y = 0; y < height; y++) {
					if (image.getRGB(x, y) == Color.BLACK.getRGB()) {
						num++;
					}
				}
				pixels[x] = num;
			}
		} else if (isXorY == 'y') {
			pixels = new int[height];
			for (int y = 0; y < height; y++) {
				int num = 0;
				for (int x = 0; x < width; x++) {
					if (image.getRGB(x, y) == Color.BLACK.getRGB()) {
						num++;
					}
				}
				pixels[y] = num;
			}
		}
		return pixels;
	}
	public static BufferedImage split(BufferedImage image, int x_start, int x_end,char isSplitXOrY) {
		BufferedImage tmp = null;
		if ('x' == isSplitXOrY) {
			tmp = image.getSubimage(x_start, 0, x_end - x_start + 1, image.getHeight());
		} else if ('y' == isSplitXOrY) {
			tmp = image.getSubimage(0, x_start, image.getWidth(), x_end - x_start + 1);
		}
		return tmp;
	}
	public static BufferedImage splitByY(BufferedImage image) {
		ImageUtils.filterBySmooth(image,2);
		LinkedList<Integer> insSub = ImageUtils.indexFlag(image, 'y');
		if (insSub.size() >= 2) {
			BufferedImage tmp = ImageUtils.split(image, insSub.get(0), insSub.get(1), 'y');
			return tmp;
		}
		return null;
	}
	public static BufferedImage cutImageByY(BufferedImage image) {
		LinkedList<Integer> insSub = ImageUtils.indexFlag(image, 'y');
		int length = insSub.size();
		if (length >= 2) {
			BufferedImage tmp = ImageUtils.split(image, insSub.get(0), insSub.get(length-1), 'y');
			return tmp;
		}
		return null;
	}
	/**
	 * Kmeans 聚类分析
	 * 
	 * @param bufferedImage
	 * @param dir
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage kmeansFilter(BufferedImage bufferedImage) {
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		List<Pixel> pixels = new ArrayList<Pixel>();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				int rgb0 = bufferedImage.getRGB(w, h);
				if (rgb0 == -1)
					continue;// 白色背景
				// 计算像素点周围平均颜色
				List<Integer> list = new ArrayList<Integer>();
				list.add(rgb0);
				for (int x = w - 3; x <= w + 3; x++) {
					for (int y = h - 3; y <= h + 3; y++) {
						if (x < 0 || y < 0 || x > width - 1 || y > height - 1) {
							continue;
						}
						int rgb1 = bufferedImage.getRGB(x, y);
						if (rgb1 != -1 && isSimilarColor(rgb0, rgb1))
							list.add(rgb1);
					}
				}
				pixels.add(new Pixel(w, h, calAvgColor(list)));
			}
		}
		//Date start = new Date();
		// Kmeans聚类分析
		Kmeans kmeans = new Kmeans(pixels, 4);
		List<Pixel>[] results = kmeans.execute();
		//Date end = new Date();
		//System.out.println(end.getTime() - start.getTime());
		// 求取分组前几种颜色平均值
		int[] rgbs = new int[results.length];
		for (int i = 0; i < results.length; i++) {
			if (results[i].size() < 50) {
				rgbs[i] = -1;
				continue;
			}
			List<Integer> list = new ArrayList<Integer>();
			for (Pixel p : results[i]) {
				list.add(p.getRGB());
			}
			rgbs[i] = calAvgColor(list); // 求平均值
		}
		// 修正颜色
		for (int i = 0; i < results.length; i++) {
			for (Pixel p : results[i]) {
				bufferedImage.setRGB(p.getX(), p.getY(), rgbs[i]);
			}
		}
		return bufferedImage;
	}
	/**
	 * 去除单独噪点
	 * 
	 * @param bufferedImage
	 * @param dir
	 * @param fileName
	 */
	public static BufferedImage smoothFilter(BufferedImage bufferedImage){
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				int rgb = bufferedImage.getRGB(w, h);
				if (rgb == -1)
					continue;
				boolean bool = true;
				int i = 0;
				while (true) {
					int x = w + i;
					if (x > width - 1 || bufferedImage.getRGB(x, h) != rgb) {
						break;
					}
					if (h - 1 >= 0 && bufferedImage.getRGB(x, h - 1) == rgb) {
						bool = false;
						break;
					}
					if (h + 1 < height && bufferedImage.getRGB(x, h + 1) == rgb) {
						bool = false;
						break;
					}
					i++;
				}
				if (bool)
					bufferedImage.setRGB(w, h, 0xFFFFFF);
			}
		}
		return bufferedImage;
	}
	/**
	 * 清楚噪点，当像素与该像素点周围一圈不同的背景色，若和背景色相同的数量大于groundCount，认为是噪点
	 * @param img
	 * @param background   背景色
	 * @param groundCount  该像素点周围一圈和背景色相同大于该数为噪点的条件
	 */
	public static void smoothFilter(BufferedImage img,Color background,int groundCount){
		int width = img.getWidth();
		int height = img.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				int rgb = img.getRGB(w, h);
				if (rgb == background.getRGB())
					continue;
				int count = 0;
				int bountorycount = 0;
				for(int x=w-1;x<=w+1;x++){
					for(int y=h-1;y<=h+1;y++){
						if(x<0||x>width-1||y<0||y>height-1){
							++bountorycount;
							continue;
						}
						if(img.getRGB(x, y)==background.getRGB())
							count++;
					}
				}
				if (count+bountorycount>=groundCount){
					img.setRGB(w, h, 0xFFFFFF);
				}
			}
		}
	}
	private static boolean isSimilarColor(int rgb1, int rgb2) {

		Color c1 = new Color(rgb1);
		float[] hsb1 = Color.RGBtoHSB(c1.getRed(), c1.getGreen(), c1.getBlue(), null);
		int h1 = Math.round(hsb1[0] * 100);
		Color c2 = new Color(rgb2);
		float[] hsb2 = Color.RGBtoHSB(c2.getRed(), c2.getGreen(), c2.getBlue(), null);
		int h2 = Math.round(hsb2[0] * 100);
		if (Math.abs(h1 - h2) <= 10) {
			return true;
		}
		return false;
	}
	/**
	 * 计算平均颜色
	 * 
	 * @param list
	 * @return
	 */
	private static int calAvgColor(List<Integer> list) {
		if (list == null || list.size() == 0) {
			return 0;
		}
		int sumR = 0, sumG = 0, sumB = 0;
		for (int rgb : list) {
			Color c = new Color(rgb);
			sumR += c.getRed();
			sumG += c.getGreen();
			sumB += c.getBlue();
		}
		return new Color(sumR / list.size(), sumG / list.size(), sumB / list.size()).getRGB();
	}
}
