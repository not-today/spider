package com.heetian.spider.peking.strategy;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 * @author Zolotaya
 */
public class Hough1 {
	int[][] accu;
	static int maxRho;

	/** Constructeur */
	public Hough1() {
		JFileChooser choix = new JFileChooser();
		if (choix.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			String filename = choix.getSelectedFile().getAbsolutePath();
			System.out.println(filename);
			try {
				BufferedImage bi = loadImage(filename);
				Applyhough(bi);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/** Fonction d'application de la transform閑 de Hough */
	public void Applyhough(BufferedImage bi) {
		int compt = 0;
		int maxRho = (int) (Math.sqrt((bi.getWidth() * bi.getWidth()) + (bi.getHeight() * bi.getHeight())) + 0.5);
		Hough1.maxRho = maxRho;
		// cr閍tion du "conteneur" de donn閑s
		accu = new int[360][2 * maxRho];
		for (int i = 0; i < accu.length; i++) {
			for (int j = 0; j < accu[i].length; j++) {
				accu[i][j] = 0;
			}
		}
		BufferedImage dst = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < bi.getWidth(); x++) {
			for (int y = 0; y < bi.getHeight(); y++) {
				int rgb = bi.getRGB(x, y);
				rgb = rgb & 0xFF;
				if (rgb > 0) {
					for (int angle = 0; angle < 360; angle++) {
						double theta = Math.toRadians(angle);
						double rho = x * Math.cos(theta) + y * Math.sin(theta);
						int indexAngle = (int) (angle);
						int indexRho = (int) (rho + maxRho + 0.5);
						accu[indexAngle][indexRho]++;
					}
					dst.setRGB(x, y, rgb);
					compt++;
				}
			}
		}
		try {
			SaveImage(dst, new FileOutputStream("img"+File.separator+"PixelRead.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int[] droite = maxi();
		try {
			dst = tracerDroite(droite, dst);
			SaveImage(dst, new FileOutputStream("img"+File.separator+"droite.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Fonction de recherche des maximums */
	public int[] maxi() {
		int maxi = 0;
		int[] droite = new int[3];
		// On parcours tout l'accumulateur pour trouver le maximum
		for (int i = 0; i < accu.length; i++) {
			for (int j = 0; j < accu[i].length; j++) {
				if (maxi < accu[i][j]) {
					maxi = accu[i][j];
					droite[0] = i; // angle
					droite[1] = j; // distance
					droite[2] = maxi; // peut servir pour la recherche des
										// maximas suivant
				}
			}
		}
		return droite;
	}

	/** Fonction de tracage des droites + calcul de leur 閝uation */
	private static BufferedImage tracerDroite(int[] droite, BufferedImage src) {

		int angle = droite[0];
		int rho = droite[1] - maxRho;

		// droite de la forme ax +by + c = 0 -->
		// si b != 0 y = (-ax + c)/b
		// sinon droite verticale
		double a = Math.cos(Math.toRadians(angle));
		double b = Math.sin(Math.toRadians(angle));
		double c = rho;

		// On fixe 2 nombre :
		int x0 = -src.getWidth();
		int x1 = src.getWidth();

		double y0, y1;
		// On calcul les deux droites :
		// SI la droite n'est pas verticale
		if (b != 0) {
			y0 = (-a * x0 + c) / b;
			y1 = (-a * x1 + c) / b;
			// On affiche l'閝uation de la droite
			System.out.println("droite : y = " + a + "*x + " + b);
			// On trace les droites trouv閑s
			Graphics g = src.getGraphics();
			g.setColor(Color.red);
			g.drawLine(x0, (int) (y0 + 0.5), x1, (int) (y1 + 0.5));
		} else {
			x0 = rho;
			y0 = 0;
			x1 = rho;
			y1 = src.getHeight();
			// On affiche l'閝uation de la droite
			System.out.println("droite : y = " + a + "*x + " + b);
			// On trace les droites trouv閑s
			Graphics g = src.getGraphics();
			g.setColor(Color.red);
			g.drawLine(x0, (int) (y0 + 0.5), x1, (int) (y1 + 0.5));
		}
		// On retourne l'image source
		return src;
	}

	/** Fonction de sauvegarde d'image */
	public static void SaveImage(BufferedImage imBW, OutputStream os)
			throws Exception {
		Graphics g = imBW.createGraphics();
		g.drawImage(imBW, 0, 0, null);
		g.dispose();
		ImageIO.write(imBW, "png", os);
		os.close();
		imBW = null;
		os = null;
	}

	// Fonction d'ouverture des images (.jpeg / .gif / .png)
	public BufferedImage loadImage(String f) throws Exception {
		Image im2 = null;
		java.awt.MediaTracker mt2 = null;

		java.io.FileInputStream in = null;
		byte[] b = null;
		int size = 0;

		in = new java.io.FileInputStream(f);
		if (in != null) {
			size = in.available();
			b = new byte[size];
			in.read(b);
			im2 = java.awt.Toolkit.getDefaultToolkit().createImage(b);
			in.close();
		}

		mt2 = new java.awt.MediaTracker(new Canvas());
		if (im2 != null) {
			if (mt2 != null) {
				mt2.addImage(im2, 0);
				mt2.waitForID(0);
			}
		}
		BufferedImage input = new BufferedImage(im2.getWidth(null), im2.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics g = input.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, im2.getWidth(null), im2.getHeight(null));
		g.drawImage(im2, 0, 0, null);
		g.dispose();
		g = null;
		return input;
	}

	public static void main(String[] args) {
		new Hough1();
	}

}