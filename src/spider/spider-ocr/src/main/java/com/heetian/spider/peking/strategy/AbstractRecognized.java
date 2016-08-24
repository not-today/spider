package com.heetian.spider.peking.strategy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.imageio.ImageIO;

import com.heetian.spider.ocr.exception.ImageConverte;
import com.heetian.spider.ocr.util.ImageUtils;
import com.heetian.spider.ocr.util.ResultProcess;
/**
 * 
 * @author tst
 *
 */
public abstract class AbstractRecognized implements Recognized {
	private String stencilType;
	protected String stencilPath;
	protected Color chColor;
	private boolean useStencil = true;
	@SuppressWarnings("rawtypes")
	protected Map stencil = new HashMap();
	/**
	 * 是否调用init()方法，true调用
	 */
	private boolean init = false;
	public void singleStencil(){
		this.stencilType = distiguingsh_sigle;
	}
	public void manyStencil(){
		this.stencilType = distiguingsh_many;
	}
	public void forbiddenStencil(){
		useStencil = false;
	}
	public BufferedImage[] isOrigalPic(Map<String, String> recogScop){
		recogScop.put(isOriglePic, isOriglePic);
		return null;
	}
	@SuppressWarnings("unchecked")
	public void init(String stencilPath,Color chColor,String stencilType) {
		if(!useStencil)
			return;
		this.stencilPath = stencilPath;
		this.chColor = chColor;
		this.stencilType = stencilType;
		try {
			if(this.stencilPath==null||"".equals(this.stencilPath)){
				throw new ImageConverte("stencilPath 参数不能为空");
			}
			if(this.chColor==null){
				throw new ImageConverte("chColor 参数不能为空");
			}
			File[] files = ImageUtils.foundFiles(this.stencilPath);
			if(distiguingsh_sigle.equals(this.stencilType)){
				for(File dir:files){
					int[][]  pixel = ImageUtils.getPixel(ImageIO.read(dir),chColor.getRGB());
					stencil.put(dir.getName().split("\\.")[0], pixel);
				}
			}else if(distiguingsh_many.equals(this.stencilType)){
				for(File dir:files){
					List<int[][]> pixels = new ArrayList<>();
					for(File file :dir.listFiles()){
						int[][]  pixel = ImageUtils.getPixel(ImageIO.read(file),chColor.getRGB());
						pixels.add(pixel);
					}
					stencil.put(dir.getName(), pixels);
				}
			}else{
				throw new ImageConverte("stencilType 参数不能为其他值");
			}
			init = true;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	@Override
	public String recognition(BufferedImage img, String picName,String picType) throws ImageConverte {
		Map<String, String> recogScop = new HashMap<String, String>();
		recogScop.put(Recognized.picType, picName);
		if(!this.init&&this.useStencil)
			throw new ImageConverte("调用init()方法初始化配置文件");
		if(img==null)
			throw new ImageConverte("image is null;");
		if(picName==null||"".equals(picName))
			throw new ImageConverte("image name is null;");
		if(picName.contains("."))
			picName = picName.split("\\.")[0];
		recogScop.put(Recognized.picName, picName);
		img = pretreatment(img,recogScop);
		if(img==null)
			throw new ImageConverte("对图片进行二值化的结果不能为空，也就是pretreatment方法需要返回值");
		BufferedImage[] imgSubs = division(img,recogScop);
		ResultProcess rp = null;
			if(isOriglePic.equals(recogScop.get(isOriglePic))){
				rp = distinguish(recogScop,picName, "png", img);
			}else{
				if(imgSubs==null||imgSubs.length<=0)
					return null;
				rp = distinguish(recogScop,picName, "png", imgSubs);
			}
		try{
			return rp.process();
		} catch (Exception e) {
			throw new ImageConverte(e);
		}
	}
	public BufferedImage[] listToArray(List<BufferedImage> imgs){
		if(imgs==null||imgs.size()<=0)
			return null;
		BufferedImage[] tmp = new BufferedImage[imgs.size()];
		for(int x=0;x<imgs.size();x++){
			tmp[x] = imgs.get(x);
		}
		return tmp;
	}
	
	/**
	 * 预处理图片，即对图片进行处理，得到二值化图片
	 * @param recogScop 
	 * @param image
	 * @throws ImageConverte
	 */
	protected abstract BufferedImage pretreatment(BufferedImage img, Map<String, String> recogScop) throws ImageConverte;
	/**
	 * 对图片进行字符分割，得到单个字符图片对象集合(集合的字符顺序和原图字符顺序要一致)，也可以不分割返回null
	 * @param img
	 * @param recogScop 
	 * @param isUsepic 使用原图还是切割后的子图 YES为原图
	 * @return
	 * @throws ImageConverte
	 */
	protected abstract BufferedImage[] division(BufferedImage img, Map<String, String> recogScop) throws ImageConverte;
	/**
	 * 对图片进行识别，（单个或者多个）并返回结果
	 * @param image
	 * @param picName
	 * @param picType
	 * @return
	 * @throws ImageConverte
	 */
	protected abstract ResultProcess distinguish(Map<String, String> recogScop,String picName,String picType,BufferedImage ...imgs) throws ImageConverte;
	public String[] chaArrToStrArr(char[] chs){
		String tmp[] = new String[chs.length];
		for(int x=0;x<chs.length;x++)
			tmp[x] = String.valueOf(chs[x]);
		return tmp;
	}
	/**
	 * 根据字符连通性切割
	 * @param image
	 * @return
	 */
	public static List<BufferedImage> twoPassCluster(BufferedImage image, int bg){
		Map<String,Integer> map = new LinkedHashMap<String, Integer>();
		int lastSign = 0;
		for (int w = 0; w < image.getWidth(); w++) {
			for (int h = 0; h < image.getHeight(); h++) {
				int rgb = image.getRGB(w, h);
				if(rgb == bg) continue;
				// 循环计算相邻标记
				Integer minSign = null;
				List<Integer> signs = new ArrayList<Integer>();
				for(int x = w - 1; x <= w; x++){
					for(int y = h - 1; y <= h + 1; y++){
						Integer sign = map.get(x+"-"+y);
						if(sign == null) continue;
						signs.add(sign);
						minSign = minSign == null || sign < minSign ? sign : minSign;
					}
				}
				// 如果相邻像素点未标记，则使用  lastSign + 1 作为本像素点标记
				if(minSign == null){
					map.put(w + "-" + h, ++lastSign);
					continue;
				}
				// 如果相邻像素点有标记，则使用相邻像素点的最小标记作为本像素点标记
				map.put(w + "-" + h, minSign);
				// 如果相邻像素点有多个标记，则需记录这些像素点之间的关系（代表这些像素点需要合并）
				signs.remove(minSign);
				
				for(Integer sign : signs ){
					for(String key : map.keySet()){
						if(map.get(key) == sign) map.put(key, minSign);
					}
				}
			}
		}
		// 将sign相同的像素点存储在一个List中
		Map<Integer, List<int[]>> clusters = new LinkedHashMap<Integer, List<int[]>>();
		for(String key : map.keySet()){
			Integer sign = map.get(key);
			int x = Integer.parseInt(key.substring(0, key.indexOf("-")));
			int y = Integer.parseInt(key.substring(key.indexOf("-") + 1));
			
			if(clusters.get(sign) != null){
				clusters.get(sign).add(new int[]{x,y});
				continue;
			}
			List<int[]>  cluster = new ArrayList<int[]>();
			cluster.add(new int[]{x,y});
			clusters.put(sign, cluster);
		}
		// 切分字符
		List<BufferedImage> results = new ArrayList<BufferedImage>();
		for(Entry<Integer, List<int[]>> entry : clusters.entrySet()){
			List<int[]> cluster = entry.getValue();
			int minX = -1, minY = -1, maxX = -1, maxY = -1;
			for(int[] pixel : cluster){
				minX = pixel[0] < minX || minX == -1 ? pixel[0] : minX;
				minY = pixel[1] < minY || minY == -1 ? pixel[1] : minY;
				maxX = pixel[0] > maxX || maxX == -1 ? pixel[0] : maxX;
				maxY = pixel[1] > maxY || maxY == -1 ? pixel[1] : maxY;
			}
			BufferedImage binaryBufferedImage = new BufferedImage(maxX - minX + 1, maxY - minY + 1, BufferedImage.TYPE_BYTE_BINARY);
			for(int[] pixel : cluster){
				binaryBufferedImage.setRGB(pixel[0] - minX , pixel[1] - minY, 0xFFFFFF);
			}
			results.add(binaryBufferedImage);
		}
		return results;
	}
	/**
	 * 根据字符颜色切图(用于纯色字符)
	 * @param image
	 * @param bg
	 * @return
	 */
	public static List<BufferedImage> colorClusters(BufferedImage image, int bg){
		// 将颜色相同的像素点存储在一个List中
		Map<Integer, List<int[]>> clusters = new LinkedHashMap<Integer, List<int[]>>();
		for (int w = 0; w < image.getWidth(); w++) {
			for (int h = 0; h < image.getHeight(); h++) {
				
				int rgb = image.getRGB(w, h);
				if(rgb == bg) continue;
				
				if(clusters.get(rgb) != null){
					clusters.get(rgb).add(new int[]{w,h});
					continue;
				}
				List<int[]>  cluster = new ArrayList<int[]>();
				cluster.add(new int[]{w,h});
				clusters.put(rgb, cluster);
			}
		}
		// 切分字符
		List<BufferedImage> results = new ArrayList<BufferedImage>();
		for(Entry<Integer, List<int[]>> entry : clusters.entrySet()){
			List<int[]> cluster = entry.getValue();
			int minX = -1, minY = -1, maxX = -1, maxY = -1;
			for(int[] pixel : cluster){
				minX = pixel[0] < minX || minX == -1 ? pixel[0] : minX;
				minY = pixel[1] < minY || minY == -1 ? pixel[1] : minY;
				maxX = pixel[0] > maxX || maxX == -1 ? pixel[0] : maxX;
				maxY = pixel[1] > maxY || maxY == -1 ? pixel[1] : maxY;
			}
			
			BufferedImage binaryBufferedImage = new BufferedImage(maxX - minX + 1, maxY - minY + 1, BufferedImage.TYPE_BYTE_BINARY);
			for(int[] pixel : cluster){
				binaryBufferedImage.setRGB(pixel[0] - minX , pixel[1] - minY, 0xFFFFFF);
			}
			results.add(binaryBufferedImage);
		}
		return results;
	}
	/**
	 * 按X轴投影切分字符
	 * @param image
	 * @param bg
	 * @return
	 */
	public static  List<BufferedImage> projectionCluster(BufferedImage image, int bg){
		// 切分字符
		Map<String,Integer> map = new LinkedHashMap<String, Integer>();
		int lastSign = 0,lastW = -1;
		for (int w = 0; w < image.getWidth(); w++) {
			for (int h = 0; h < image.getHeight(); h++) {
				
				int rgb = image.getRGB(w, h);
				if(rgb == bg) continue;
				
				if(lastW == -1 || w - lastW > 1)  lastSign++;
				map.put(w + "-" + h, lastSign);
				lastW = w;
			}
		}
		// 将sign相同的像素点存储在一个List中
		Map<Integer, Set<String>> clusters = new LinkedHashMap<Integer, Set<String>>();
		for(String key : map.keySet()){
			Integer sign = map.get(key);
			if(clusters.get(sign) != null){
				clusters.get(sign).add(key);
				continue;
			}
			Set<String>  cluster = new HashSet<String>();
			cluster.add(key);
			clusters.put(sign, cluster);
		}
		// 切分字符
		List<BufferedImage> results = new ArrayList<BufferedImage>();
		for(Entry<Integer, Set<String>> entry : clusters.entrySet()){
			Set<String> cluster = entry.getValue();
			if(cluster.size() < 10) continue;					// 删除碎片
			int minX = -1, minY = -1, maxX = -1, maxY = -1;		// 计算最小、最大 X坐标、Y坐标
			for(String item : cluster){
				int x = Integer.parseInt(item.substring(0,item.indexOf("-")));
				int y =  Integer.parseInt(item.substring(item.indexOf("-") + 1));
				minX = x < minX || minX == -1 ? x : minX;
				minY = y < minY || minY == -1 ? y : minY;
				maxX = x > maxX || maxX == -1 ? x : maxX;
				maxY = y > maxY || maxY == -1 ? y : maxY;
			}
			BufferedImage binaryBufferedImage = new BufferedImage(maxX - minX + 1, maxY - minY + 1, BufferedImage.TYPE_BYTE_BINARY);
			for(int x = 0; x < binaryBufferedImage.getWidth(); x++){
				for(int y = 0; y< binaryBufferedImage.getHeight(); y++){
					String key = (x + minX) + "-" + (y + minY);
					binaryBufferedImage.setRGB(x , y , cluster.contains(key) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
				}
			}
			results.add(binaryBufferedImage);
		}
		return results;
	}
	/**
	 * 单字符模版识别
	 * @param piexel
	 * @param map_num
	 * @return
	 */
	public String recognizedByPixelMatch(int[][] piexel,Map<String, int[][]> map_num){
		if(piexel==null)
			return "";
		Set<Entry<String, int[][]>> ents = map_num.entrySet();
		double maxProba = -1;
		String key = null;
		for(Entry<String, int[][]> ent:ents){
			double currentProba = ImageUtils.matchPixel(ent.getValue(), piexel);
			if(maxProba<=currentProba){
				maxProba = currentProba;
				key = ent.getKey();
			}
		}
		return key;
	}
	public static BufferedImage splitByY(BufferedImage image) {
		//save(image, new File("C:\\Users\\tst\\Desktop\\test\\test.png"));
		LinkedList<Integer> insSub = ImageUtils.indexFlag(image, 'y');
		if (insSub.size() == 2) {
			return ImageUtils.split(image, insSub.get(0), insSub.get(1), 'y');
		}
		return null;
	}
	public void save(BufferedImage img, File file) {
		ImageUtils.copyFileImgshield(img,file);
	}
	public String googleRecognize(BufferedImage img,String picType,String picName, String laguageEng) throws ImageConverte{
		File dest = new File(SAVE_DIR + "prepare" + picName+"."+picType);
		try {
			ImageIO.write(img, picType, dest);
			String picContent = CallUtil.callTesseract(dest.getAbsolutePath(), laguageEng);
			picContent = picContent!=null?picContent.replaceAll("\\s", ""):null;
			return picContent;
		} catch (IOException e) {
			throw new ImageConverte(e);
		}
	}
	public BufferedImage clearAroundNull(BufferedImage img) {
		int left = bundaryLEFT(img);
		int right = bundaryRIGHT(img);
		int up = bundaryUP(img);
		int down = bundaryDOWN(img);
		img = img.getSubimage(left, up, right-left+1, down-up+1);
		return img;
	}
	public BufferedImage dispersed(BufferedImage img,Color color) {
		int width = img.getWidth();
		int height = img.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				int rgb = img.getRGB(w, h);
				// 周围一圈相同颜色像素点个数
				int count = 0;
				for (int x = w - 1; x <= w + 1; x++) {
					for (int y = h - 1; y <= h + 1; y++) {
						if (x < 0 || y < 0 || x > width - 1 || y > height - 1) {
							count++;
							continue;
						}
						if(img.getRGB(x, y) != rgb){
							count++;
						}
					}
				}
				if (count ==8){
					img.setRGB(w, h, color.getRGB());
				}
			}
		}
		return img;
	}
	public BufferedImage tiltCorrection(BufferedImage img){
		int width = img.getWidth();
		int height = img.getHeight();
		BufferedImage image = new BufferedImage(width + height/3, height, BufferedImage.TYPE_INT_RGB);
		for(int w = 0; w < image.getWidth(); w++){
			for(int h = 0; h < image.getHeight(); h++){
				image.setRGB( w , h, Color.WHITE.getRGB());
			}
		}
		for(int w = 0; w < width; w++){
			for(int h = 0; h < height; h++){
				image.setRGB( w + h/3, h, img.getRGB(w, h));
			}
		}
		return image;
	}
	public int bundaryRIGHT(BufferedImage img) {
		int h = img.getHeight();
		int w = img.getWidth();
		int end = -1;
		for(int x=w-1;x>0;x--){
			for(int y=0;y<h;y++){
				if(img.getRGB(x, y)==Color.BLACK.getRGB()){
					end = x;
					break;
				}
			}
			if(end!=-1)
				break;
		}
		return end;
	}
	public int bundaryLEFT(BufferedImage img) {
		int h = img.getHeight();
		int w = img.getWidth();
		int begin = -1;
		for(int x=0;x<w;x++){
			for(int y=0;y<h;y++){
				if(img.getRGB(x, y)==Color.BLACK.getRGB()){
					begin = x;
					break;
				}
			}
			if(begin!=-1)
				break;
		}
		return begin;
	}
	public int bundaryUP(BufferedImage img) {
		int w = img.getWidth();
		int h = img.getHeight();
		int begin = -1;
		for(int y=0;y<h;y++){
			for(int x=0;x<w;x++){
				if(img.getRGB(x, y)==Color.BLACK.getRGB()){
					begin = y;
					break;
				}
			}
			if(begin!=-1)
				break;
		}
		return begin;
	}
	public int bundaryDOWN(BufferedImage img) {
		int h = img.getHeight();
		int w = img.getWidth();
		int end = -1;
		for(int y=h-1;y>0;y--){
			for(int x=0;x<w;x++){
				if(img.getRGB(x, y)==Color.BLACK.getRGB()){
					end = y;
					break;
				}
			}
			if(end!=-1)
				break;
		}
		return end;
	}
	/**
	 * 像素点大于（rgb（255，255，255）的和的一半）
	 * @param rgb
	 * @return
	 */
	protected boolean isBg383gt(int rgb,int threshold) {
		if(threshold<383)
			throw new RuntimeException("设置的阀值大于383才能调用");
		Color color = new Color(rgb);
		if ((color.getRed() + color.getGreen() + color.getBlue()) >= threshold) 
			return true;
		return false;
	}
	/**
	 * 像素点小于于（rgb（255，255，255）的和的一半）
	 * @param rgb
	 * @return
	 */
	protected boolean isBg383lt(int rgb,int threshold) {
		if(threshold>=383)
			throw new RuntimeException("设置的阀值小于383才能调用");
		Color color = new Color(rgb);
		if ((color.getRed() + color.getGreen() + color.getBlue()) <= threshold) 
			return true;
		return false;
	}
	/**
	 * 将二值化图片的字符颜色转成黑色
	 * @param img
	 * @param bkColor 背景色
	 * @param chColor 字符色
	 */
	protected void othToBlackForChar(BufferedImage img,int bkColor,int chColor){
		int w = img.getWidth();
		int h = img.getHeight();
		for(int y=0;y<h;y++){
			for(int x=0;x<w;x++){
				int rgb = img.getRGB(x, y);
				if(rgb==chColor){
					img.setRGB(x, y, Color.BLACK.getRGB());
				}else if(rgb==bkColor){
					img.setRGB(x, y, Color.WHITE.getRGB());
				}
				
			}
		}
	}
}
