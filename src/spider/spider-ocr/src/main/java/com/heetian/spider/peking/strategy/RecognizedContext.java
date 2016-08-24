package com.heetian.spider.peking.strategy;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.ocr.exception.ImageConverte;

/**
 * 识别程序context类，单例
 * @author tst
 *
 */
public class RecognizedContext {
	private static Logger logger = LoggerFactory.getLogger(RecognizedContext.class);
	private static final Map<String,Recognized> recognizeds = new HashMap<String,Recognized>();
	/**
	 * 是否保存验证码图片,true是保存
	 */
	private static final boolean isSavePicture = false;
	private String total ="0";
	private String errorTotal = "0";
	private String successTotal = "0";
	private long timeStamp = System.currentTimeMillis();
	public static final String saveName = "RecognizedContext_Result";
	private static final RecognizedContext context = new RecognizedContext();
	/**
	 * 识别程序总数
	 */
	private int number;
	/**
	 * 当前使用的识别程序id,从1开始进行编号
	 */
	private int currentId;
	/**
	 * 总的错误数
	 */
	private int maxError;
	/**
	 * 当前识别程序，识别的错误数目
	 */
	private int currentError;
	/**
	 * 识别程序，从开始第一个到最后一个又到第一个的循环次数
	 */
	private long circleNumber = 0;
	/**
	 * 识别程序集合
	 */
	private ArrayList<Recognized> ss =  new ArrayList<Recognized>();
	/**
	 * 锁对象
	 */
	private static final Object lock = new Object();
	/**
	 * 构造器，初始化程序参数
	 */
	private RecognizedContext(){
		InputStream in = RecognizedContext.class.getClassLoader().getResourceAsStream("strategy.properties");
		BufferedReader buffRead = new BufferedReader(new InputStreamReader(in));
		String line = null;
		try {
			while((line=buffRead.readLine())!=null){
				line = line.replace(" ", "");
				if(!"".equals(line)){
					int index = line.indexOf("#");
					if(index==-1){
						evaluation(line);
					}else{
						//过滤掉#在开头的情况
						if(index!=0){
							//有#号，但不是在开头；截取到开头到第一个#的内容
							String content = line.split("#")[0];
							//过滤掉内容中的格式为key=value的形式
							evaluation(content);
						}
					}
				}
			}
			this.number = ss.size();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	private void evaluation(String content) throws InstantiationException,IllegalAccessException, ClassNotFoundException {
		if(content.contains("=")){
			String contents[] = content.split("=");
			if(contents[0].equals("currentId")){
				this.currentId = Integer.parseInt(contents[1]);
			}else if(contents[0].equals("maxError")){
				this.maxError = Integer.parseInt(contents[1]);
			}else if(contents[0].equals("currentError")){
				this.currentError = Integer.parseInt(contents[1]);
			}else if(contents[0].contains("recognized")){
				ss.add((Recognized) Class.forName(contents[1]).newInstance());
				logger.info("初始化识别程序："+contents[0]+"="+contents[1]+",完成");
			}
		}
	}

	public static RecognizedContext newInstance() {
		return context;
	}
	/**
	 * 用于验证码校验完毕后，更新状态
	 * @param isSuccess
	 * @param currentId
	 * @param circleNumber
	 */
	public void updateCurrentError(IsSucess isSuccess,int currentId,long circleNumber){
		if(currentId==-1&&circleNumber==-1){
			return;
		}
		synchronized(lock){
			if(this.currentId == currentId&&this.circleNumber==circleNumber){
				if(isSuccess==IsSucess.SUCCESS){//成功处理
					if(this.maxError<=this.currentError){//判断错误数是否达到最大，超过，则切换识别程序，令错误数为0
						updateVariable();
					}
					this.currentError = 0;
					codeListen((byte)1);
				}
				if(isSuccess==IsSucess.FAIL){//失败处理
					if(this.maxError<=this.currentError){
						updateVariable();
					}else{
						this.currentError++;
					}
					codeListen((byte)0);
				}
			}
		}
	 }
	/**
	 * 
	 * @param picName
	 * @param picType
	 * @return 字符数组：0为内容，（1为currentId，2为circleNumber：更新成功或者失败需要用的）
	 * @throws ImageConverte
	 */
	public String[] recognized(String recognizedName,BufferedImage image,String picType) throws ImageConverte{
		if(image==null){
			throw new ImageConverte("BufferedImage对象为null");
		}
		String picName = Thread.currentThread().getName()+System.currentTimeMillis() + "Code.jpeg";
		String contents[] = null;
		try {
			//IOUtils.copy(picInputStream, new FileOutputStream(file));
			// byte[] contentBytes = IOUtils.toByteArray(httpResponse.getEntity().getContent());
			if(isSavePicture){
				ImageIO.write(image, picType , new File(Recognized.SAVE_DIR+picName));
			}
			Recognized recognized = null;
			if(recognizedName!=null||!"".equals(recognizedName)){
				recognized = recognizeds.get(recognizedName);
				if(recognized==null){
					for(Recognized s:ss){
						if(s.getClass().getName().equals(recognizedName)){
							recognized = s;
							recognizeds.put(recognizedName, s);
							break;
						}
					}
				}
			}
			if(recognized!=null){
				contents = new String[]{recognized.recognition(image,picName, picType),"-1","-1"};
			}else{
				synchronized(lock){
					if(this.maxError<=this.currentError){
						updateVariable();
					}
					recognized = ss.get(this.currentId-1);
					//返回当前识别程序内部id号，当前识别程序循环次数
					contents = new String[]{recognized.recognition(image,picName, picType),String.valueOf(this.currentId),String.valueOf(this.circleNumber)};
				}
			}
			return contents;
		} catch (Exception e) {
			logger.warn("解析验证码出错,导致验证码为空",e);
			return contents;
		}finally{
			if(contents==null){
				if(recognizedName!=null||!"".equals(recognizedName)){
					contents =  new String[]{null,"-1","-1"};
				}else{
					contents =  new String[]{null,String.valueOf(this.currentId),String.valueOf(this.circleNumber)};
				}
			}
		}
	}


	private void updateVariable() {
		this.currentId++;
		if(this.currentId>this.number){
			this.currentId=1;
			this.circleNumber++;
		}
		logger.info("切换识别程序"+this.currentId);
		this.currentError = 0;
	}
	private String divide(String Dividend,String divisor){
		BigDecimal DividendBig = new BigDecimal(Dividend);
		BigDecimal divisorBig = new BigDecimal(divisor);
		return DividendBig.divide(divisorBig,2,BigDecimal.ROUND_HALF_UP).toString();
	}
	private String add(String number1,String number2){
		BigDecimal number1Big = new BigDecimal(number1);
		BigDecimal number2Big = new BigDecimal(number2);
		return number1Big.add(number2Big).toString();
	}
	private String percentage(String number1,String number2){
		BigDecimal number1Big = new BigDecimal(number1);
		BigDecimal number2Big = new BigDecimal(number2);
		return number1Big.multiply(number2Big).toString();
	}
	public void codeListen(byte flag) {
		total = add(total, String.valueOf(1));
		if(flag==1){
			successTotal = add(successTotal, String.valueOf(1));
		}else if(flag==0){
			errorTotal = add(errorTotal, String.valueOf(1));
		}
		if((System.currentTimeMillis()-timeStamp)>1000*60*60*1){
			timeStamp = System.currentTimeMillis();
			logger.info("当前验证码识别有关指标：成功率["+percentage(divide(successTotal, total),String.valueOf(100))
					+"%]失败率["+percentage(divide(errorTotal, total),String.valueOf(100))+"%]总数["+total+"]失败总数["+errorTotal+"]成功总数["+successTotal+"]");
		}
	}
}