package com.heetian.spider.peking.strategy;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class Tmp {

    private BufferedImage image;
    private int width;
    private int height;

    /**
     * 变图像为黑白色 提示: 黑白化之前最好灰色化以便得到好的灰度平均值,利于获得好的黑白效果
     *
     * @return
     */
    public Tmp changeToBlackWhiteImage() {
        int avgGrayValue = getAvgValue();
        int whitePoint = getWhitePoint(), blackPoint = getBlackPoint();

        Color point;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                point = new Color(image.getRGB(j, i));
                image.setRGB(j, i, (point.getRed() < avgGrayValue ? blackPoint : whitePoint));
            }
        }
        return this;
    }

    /**
     *
     *
     * @param whiteAreaPercent 过滤之后白色区域面积占整个图片面积的最小百分比
     * @param removeLighter true:过滤比中值颜色轻的,false:过滤比中值颜色重的,一般都是true
     * @return
     */
    public Tmp midddleValueFilter(int whiteAreaMinPercent, boolean removeLighter) {
        int modify = 0;
        int avg = getAvgValue();
        Color point;
        while (getWhitePercent() < whiteAreaMinPercent) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    point = new Color(image.getRGB(j, i));
                    if (removeLighter) {
                        if (((point.getRed() + point.getGreen() + point.getBlue()) / 3) > avg - modify) {
//                         System.out.println(((point.getRed() + point.getGreen() + point.getBlue()) / 3)+"--"+(avg - modify));
                            image.setRGB(j, i, getWhitePoint());
                        }
                    } else {
                        if (((point.getRed() + point.getGreen() + point.getBlue()) / 3) < avg + modify) {
//                         System.out.println(((point.getRed() + point.getGreen() + point.getBlue()) / 3)+"--"+(avg - modify));
                            image.setRGB(j, i, getWhitePoint());
                        }
                    }

                }
            }
            modify++;
        }
//        System.out.println(getWhitePercent());
        return this;
    }

    private int getWhitePercent() {
        Color point;
        int white = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                point = new Color(image.getRGB(j, i));
                if (((point.getRed() + point.getGreen() + point.getBlue()) / 3) == 255) {
                    white++;
                }
            }
        }
        return (int) Math.ceil(((float) white * 100 / (width * height)));
    }

    /**
     * @param 变图像为灰色 取像素点的rgb三色平均值作为灰度值
     *
     * @return
     */
    public Tmp changeToGrayImage() {
        int gray;
        Color point;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                point = new Color(image.getRGB(j, i));
                gray = (point.getRed() + point.getGreen() + point.getBlue()) / 3;
                image.setRGB(j, i, new Color(gray, gray, gray).getRGB());
            }
        }
        return this;
    }

    /**
     *
     * 去除噪点和单点组成的干扰线 注意: 去除噪点之前应该对图像黑白化
     *
     * @param neighborhoodMinCount 每个点最少的邻居数
     * @return
     */
    public Tmp removeBadBlock(int blockWidth, int blockHeight, int neighborhoodMinCount) {
        int val;
        int whitePoint = getWhitePoint();
        int counter, topLeftXIndex, topLeftYIndex;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //初始化邻居数为0
                counter = 0;
                topLeftXIndex = x - 1;
                topLeftYIndex = y - 1;
                //x1 y1是以x,y左上角点为顶点的矩形,该矩形包围在传入的矩形的外围,计算传入的矩形的有效邻居数目
                if (isBlackBlock(x, y, blockWidth, blockHeight)) {//只有当块是全黑色才计算
                    for (int x1 = topLeftXIndex; x1 <= topLeftXIndex + blockWidth + 1; x1++) {
                        for (int y1 = topLeftYIndex; y1 <= topLeftYIndex + blockHeight + 1; y1++) {
                            //判断这个点是否存在
                            if (x1 < width && x1 >= 0 && y1 < height && y1 >= 0) {
                                //判断这个点是否是传入矩形的外围点
                                if (x1 == topLeftXIndex || x1 == topLeftXIndex + blockWidth + 1
                                        || y1 == topLeftYIndex || y1 == topLeftYIndex + blockHeight + 1) {
                                    //这里假定图像已经被黑白化,取Red值认为不是0就是255 
                                    val = new Color(image.getRGB(x1, y1)).getRed();
//                                System.out.println(val + "--" + (centerVal));
                                    //如果这个邻居是黑色,就把中心点的有效邻居数目加一
                                    if (val == 0) {
                                        counter++;
                                    }
                                }
                            }
                        }
                    }
//                    System.out.println("-------------------");
//                System.out.println(x+"-"+y+"-"+counter);
                    if (counter < neighborhoodMinCount) {
                        image.setRGB(x, y, whitePoint);
                    }
                }
            }
        }
        return this;
    }

    /**
     * 如果点周围的黑点数达到补偿值就把这个点变为黑色
     *
     * @param addFlag 补偿阀值,通过观察处理过的图像确定,一般为2即可
     * @return
     */
    public Tmp modifyBlank(int addFlag) {
        int val, counter = 0, topLeftXIndex, topLeftYIndex, blackPoint = getBlackPoint();
        Color point;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //初始化邻居数为0
                counter = 0;
                topLeftXIndex = x - 1;
                topLeftYIndex = y - 1;
                point = new Color(image.getRGB(x, y));
                //这里假定图像已经被黑白化,取Red值认为不是0就是255 
                val = point.getRed();
                //只有白点才进行补偿
                if (val == 255) {
                    for (int x1 = topLeftXIndex; x1 <= topLeftXIndex + 2; x1++) {
                        for (int y1 = topLeftYIndex; y1 <= topLeftYIndex + 2; y1++) {
                            //判断这个点是否存在
                            if (x1 < width && x1 >= 0 && y1 < height && y1 >= 0) {
                                //判断这个点是否是传入点的外围点
                                if (x1 == topLeftXIndex || x1 == topLeftXIndex + 2
                                        || y1 == topLeftYIndex || y1 == topLeftYIndex + 2) {
                                    //这里假定图像已经被黑白化,取Red值认为不是0就是255 
                                    val = new Color(image.getRGB(x1, y1)).getRed();
//                                System.out.println(val + "--" + (centerVal));
                                    //如果这个邻居是黑色,就把中心点的补偿数目加一
                                    if (val == 0) {
                                        counter++;
                                    }
                                }
                            }
                        }
                    }
                    //如果这个点周围的黑点数达到补偿值就把这个点变为黑色
                    if (counter >= addFlag) {
                        image.setRGB(x, y, blackPoint);
                    }
                }
            }
        }
        return this;
    }

    public BufferedImage getBufferedImage(String filename) {
        File file = new File(filename);
        try {
            return ImageIO.read(file);
        } catch (IOException ex) {
            Logger.getLogger(Tmp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private boolean isBlackBlock(int startX, int startY, int blockWidth, int blockHeight) {
        int counter = 0;//统计黑色像素点的个数
        int total = 0;//统计有效像素点的个数
        int val;
        for (int x1 = startX; x1 <= startX + blockWidth - 1; x1++) {
            for (int y1 = startY; y1 <= startY + blockHeight - 1; y1++) {
                //判断这个点是否存在
                if (x1 < width && x1 >= 0 && y1 < height && y1 >= 0) {
                    total++;//有效像素点的个数
                    //这里假定图像已经被黑白化,取Red值认为不是0就是255 
                    val = new Color(image.getRGB(x1, y1)).getRed();
                    //如果这个点是黑色,就把黑色像素点的数目加一
                    if (val == 0) {
                        counter++;
                    }
                }
            }
        }
//        System.out.println(startX + "--" + startY + "" + (counter == total&&total!=0));
        return counter == total && total != 0;
    }

    private int getWhitePoint() {
        return (new Color(255, 255, 255).getRGB() & 0xffffffff);
    }

    private int getBlackPoint() {
        return (new Color(0, 0, 0).getRGB() & 0xffffffff);
    }

    private int getAvgValue() {
        Color point;
        int total = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                point = new Color(image.getRGB(j, i));
                total += (point.getRed() + point.getGreen() + point.getBlue()) / 3;
            }
        }
        return total / (width * height);
    }

    public void saveToFile(String filePath) {
        try {
            String ext = filePath.substring(filePath.lastIndexOf(".") + 1);
            File newFile = new File(filePath);
            ImageIO.write(image, ext, newFile);
        } catch (IOException ex) {
            Logger.getLogger(Tmp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
    }
}
