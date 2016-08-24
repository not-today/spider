package com.heetian.spider.peking.strategy;

/*  
 **************************************************************************  
 *  
 *    Hough Transform  
 *  
 *    1. Hough array: hough[nrho][271]  
 *    2  Take a sharpened image  
 *    3. From rho = x*cos(theta) + y*sin(theta);  
 *       - Vary the theta form -90 to 180 degrees to find a solution to rho;  
 *       - If (rho>=0 && rho<nrho) then the line exists.  
 *  
 **************************************************************************  
 */   
   
public class Hough {   
  public static void main(String[] args) {   
    int  in_img[][], out_img[][], hough_img[][];   
    int  nrows, ncols, nrho, ntheta, x, y;   
    double  hough[][], thresh, theta, rho, rad, max, alpha;   
   
    if (args.length != 5) {   
      System.out.println(   
        "Usage: Hough <nrows> <ncols> <thresh> <in_img> <out_img>");   
      System.exit(0);   
    }   
    nrows = Integer.parseInt(args[0]);   
    ncols = Integer.parseInt(args[1]);   
    nrho = (int)Math.sqrt(nrows*nrows+ncols*ncols) + 1;   
    ntheta = 271;    // -90 ~ 180   
    thresh = Double.parseDouble(args[2]);   
    rad = Math.PI/180;   
    max = 0;   
   
    in_img = new int[nrows][ncols];   
    ArrayIO.readByteArray(args[3], in_img, nrows, ncols);   
    hough = new double[nrho][ntheta];   
    hough_img = new int[nrho][ntheta];   
    out_img = new int[nrows][ncols];   
   
    // Initialize Hough array   
    for (x=0; x<nrho; x++)   
      for (y=0; y<ntheta; y++)   
        hough[x][y] = 0;   
   
    for (x=0; x<nrows; x++)   
      for (y=0; y<ncols; y++)   
        if (in_img[x][y] == 255)   
          for (theta= -90; theta<=180; theta++) {   
        rho = x*Math.cos(theta*rad) + y*Math.sin(theta*rad);   
        if (rho>0 && rho<nrho) {   // Solution of rho found.   
              hough[(int)rho][(int)(theta+90)]++;   
          if (max < hough[(int)rho][(int)(theta+90)])   
                max = hough[(int)rho][(int)(theta+90)];   
            }   
      }   
   
    // Normalize to the range of [0, 255]   
    alpha = 255/max;   
    for (x=0; x<nrho; x++)   
      for (y=0; y<ntheta; y++)   
        hough_img[x][y] = (int)(alpha*hough[x][y]);   
    ArrayIO.writeByteArray("Hough.raw", hough_img, nrho, ntheta);   
    System.out.println("'Hough.raw' (" + nrho + " x 271) created");   
   
    for (x=0; x<nrows; x++)   
      for (y=0; y<ncols; y++) {   
        out_img[x][y] = 0;   
        // The following 'if' statement can be commented to see straight lines   
        // if (in_img[x][y] != 255)   
          // continue;   
        for (theta=-90; theta<180; theta++) {   
      rho = x*Math.cos(theta*rad) + y*Math.sin(theta*rad);   
      if (rho>0 && rho<nrho && hough[(int)rho][(int)(theta+90)]>thresh)   
            out_img[x][y] = 255;   
        }   
      }   
   
    ArrayIO.writeByteArray(args[4], out_img, nrows, ncols);   
  }   
   
}   