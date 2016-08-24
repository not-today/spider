package com.heetian.spider.ocr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.tools.ReadConfig;

/**
 * <p>
 * Title: OCR.java
 * </p>
 * <p>
 * Description: 识别图片
 * </p>
 * 
 * @author gao_jun
 * @version 1.0
 * @created 2015年3月6日 下午5:16:13
 */
public class OCR {

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(OCR.class);

	private final static String LANG_OPTION = "-l"; // 英文字母小写l，并非数字1
	private final static String EOL = System.getProperty("line.separator");
	private static String tessPath ;
	static{
		try {
			tessPath = ReadConfig.getCfgPath("Tesseract-OCR" + File.separator + "tesseract.exe");
		} catch (FileNotFoundException e) {
			logger.error("读取Tesseract-OCR命令出错",e);
		}
	}
	// private String tessPath = new File("tesseract").getAbsolutePath();

	/**
	 * 封装调用tesseract的过程
	 * 
	 * @param imageFile
	 *            要识别的图片
	 * @param imageFormat
	 *            图片格式
	 * @return 识别结果
	 * @throws Exception
	 *             执行异常
	 */
	public static String recognizeText(File imageFile, String imageFormat)throws Exception {
		// File tempImage = ImageIOHelper.createImage(imageFile,imageFormat);
		File outputFile = new File(imageFile.getParentFile(),imageFile.getName());
		StringBuffer strB = new StringBuffer();
		try {
			List<String> cmd = new ArrayList<String>();
			// if(OS.isWindowsXP()){
			// cmd.add(tessPath+"//tesseract");
			// }else if(OS.isLinux()){
			// cmd.add("tesseract");
			// }else{
			// cmd.add(tessPath+"//tesseract");
			// }
			cmd.add(tessPath);
			cmd.add("");
			cmd.add(outputFile.getName());
			cmd.add(LANG_OPTION);
			// cmd.add("chi_sim");
			cmd.add("num");

			ProcessBuilder pb = new ProcessBuilder();
			pb.directory(imageFile.getParentFile());

			cmd.set(1, imageFile.getName());
			pb.command(cmd);
			pb.redirectErrorStream(true);// 合并错误与输出流
			logger.info("执行命令：{}", cmd);
			Process process = pb.start();
			// tesseract.exe 1.jpg 1 -l chi_sim
			int w = process.waitFor();

			// 删除临时正在工作文件
			// tempImage.delete();

			if (w == 0) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						new FileInputStream(outputFile.getAbsolutePath()
								+ ".txt"), "UTF-8"));

				String str;
				while ((str = in.readLine()) != null) {
					strB.append(str).append(EOL);
				}
				in.close();
			} else {
				String msg;
				switch (w) {
				case 1:
					msg = "Errors accessing files.There may be spaces in your image's filename.";
					break;
				case 29:
					msg = "Cannot recongnize the image or its selected region.";
					break;
				case 31:
					msg = "Unsupported image format.";
					break;
				default:
					msg = "Errors occurred.";

					logger.info("执行异常：{}", msg);
				}

				// tempImage.delete();
				// throw new RuntimeException(msg);
			}
		} finally {//最后一定删除临时文 件

			new File(outputFile.getAbsolutePath() + ".txt").delete();
			
			
//			imageFile.delete();
		}
		logger.info("识别结果为：{}", strB.toString());
		return strB.toString();
	}

	/**
	 * 要识别的验证码名称
	 * 
	 * @return
	 */
	public static String recVerificationCode(String picName) {
		String result = null;
		try {
			// ProcessImg.removeBackgroud(picName,0,picName+".jpg");
			result = OCR.recognizeText(new File(picName), "jpg");
//			new File(picName).delete();
			return CorrectVerificationCode(result);
		} catch (Exception e) {
			logger.error("{}", e);
		}
		return result;
	}

	/**
	 * 对验证码进行程序校正，并进行计算，一旦失败，返回null
	 * 
	 * @param valCode
	 * @return
	 */
	public static String CorrectVerificationCode(String valCode) {
		// 构造map
		Map<String,String> map = new HashMap<String,String>();
		map.put("0", "0");
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		map.put("4", "4");
		map.put("5", "5");
		map.put("6", "6");
		map.put("7", "7");
		map.put("8", "8");
		map.put("9", "9");
		map.put("一", "1");
		map.put("二", "2");
		map.put("三", "3");
		map.put("四", "4");
		map.put("五", "5");
		map.put("六", "6");
		map.put("七", "7");
		map.put("八", "8");
		map.put("九", "9");
		map.put("o", "0");
		map.put("壹", "1");
		map.put("贰", "2");
		map.put("叁", "3");
		map.put("肆", "4");
		map.put("伍", "5");
		map.put("陆", "6");
		map.put("柒", "7");
		map.put("捌", "8");
		map.put("玖", "9");
		map.put("零", "0");
		map.put("〇", "0");
		map.put("-", "1");
		map.put("-", "1");
		map.put("—", "1");
		map.put("一", "1");

		Map<String,String> map1 = new HashMap<String,String>();
		map1.put("加", "+");
		map1.put("减", "-");
		map1.put("x", "*");
		map1.put("乘", "*");
		map1.put("十", "+");
		map1.put("+", "+");
		map1.put("-", "-");
		map1.put("—", "-");
		map1.put("一", "-");

		String str = valCode.toLowerCase().replaceAll(" ", "");
		Integer result = null;
		if (str.length() > 0) {// 识别长度大于1
			String one = str.substring(0, 1);
			String two = str.substring(1, 2);
			String three = str.substring(2, 3);
			String threeResult = (String) map.get(three);
			if (threeResult == null || "".equals(threeResult)) {
				three = str.substring(3, 4);
			}

			String oneResult = (String) map.get(one);
			if (oneResult == null)
				return null;
			String twoResult = (String) map1.get(two);
			if (twoResult == null)
				return null;
			threeResult = (String) map.get(three);
			if (threeResult == null)
				return null;

			if ("+".equals(twoResult)) {
				result = Integer.parseInt(oneResult)
						+ Integer.parseInt(threeResult);
			} else if ("-".equals(twoResult)) {

				result = Integer.parseInt(oneResult)
						- Integer.parseInt(threeResult);
			} else if ("*".equals(twoResult)) {

				result = Integer.parseInt(oneResult)
						* Integer.parseInt(threeResult);
			}
		}
		// if(num1.indexOf(str.charAt(0))!=-1&&)
		return String.valueOf(result);
	}
}
