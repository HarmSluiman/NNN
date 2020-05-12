package nnn.viewer;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.awt.Dimension;
import javax.swing.*;
import javax.imageio.ImageIO;

import nnn.space.Space;

public class TwoDSpaceView extends JPanel {
	/**
	 * 
	 */
	public static final int DimensionPointFactor = 4;
	private static final long serialVersionUID = 1L;
	private BufferedImage bufferedImage;
	// private BufferedImage view;

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	public TwoDSpaceView(String name) {

		try {
			this.setBufferedImage(ImageIO.read(new File("./src/nnn/viewer/" + name)));
			int w = this.getBufferedImage().getWidth(null);
			int h = this.getBufferedImage().getHeight(null);
			if (this.getBufferedImage().getType() != BufferedImage.TYPE_INT_RGB) {
				// BufferedImage bi2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

				this.prepareImage(this.getBufferedImage(), null);
				System.out.println("height:" + h + " Width :" + w);
				this.setSize(w, h);
				System.out.println("file 2DSsize is :" + this.getSize());
				Dimension d = new Dimension();
				d.setSize(w, h);
				this.setMinimumSize(d);
				this.setPreferredSize(d);

				Graphics big = this.getBufferedImage().getGraphics();
				big.drawImage(this.getBufferedImage(), 0, 0, null);
				System.out.println(name + " size is :" + this.getSize());
			}
		} catch (IOException e) {
			System.out.println("Image could not be read");
			System.exit(1);
		}
	}

	public TwoDSpaceView(int w, int h, int rgb) {
		int newWidth = (w * DimensionPointFactor) + DimensionPointFactor+1;
		int newHeight = (h * DimensionPointFactor) + DimensionPointFactor+1;
		this.setBufferedImage(new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB));
		for (int a = 0; a < newHeight; a++) {
			for (int b = 0; b < newHeight; b++) {
				this.getBufferedImage().setRGB(a, b, rgb);
			}
		}
		
		
		this.prepareImage(this.getBufferedImage(), null);
		System.out.println("height:" + newHeight + " Width :" + newWidth);
		this.setSize(newWidth, newHeight);
		System.out.println("3D 2DSsize is :" + this.getSize());

		Dimension d = new Dimension();
		d.setSize(newWidth, newHeight);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		System.out.println("Orientation :");
		Graphics big = this.getBufferedImage().getGraphics();
		big.drawImage(getBufferedImage(), 0, 0, null);
	}

	public void paint(Graphics g) {
		int pixel, alpha, red, blue, green = 0;

		super.paint(g);

		pixel = getBufferedImage().getRGB(1,1);
		alpha = (pixel >> 24) & 0xff;
		red = (pixel >> 16) & 0xff;
		green = (pixel >> 8) & 0xff;
		blue = (pixel) & 0xff;
		System.out.println("pixel : " + alpha + ", " + red + ", " + green + ", " + blue);

		System.out.println("RGB " + getBufferedImage().getRGB(1, 1));

		g.drawImage(getBufferedImage(), 0, 0, null);

	}

}
