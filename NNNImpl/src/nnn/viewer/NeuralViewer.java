package nnn.viewer;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.io.*;
import java.util.TreeSet;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;

import nnn.nerve.Nerve;
import nnn.space.Location;
import nnn.space.Space;

public class NeuralViewer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String descs[] = { "Zoom in 2X", "Zoom out 2X", "Zoom out 10X", "Original" };

	int opIndex;
	// private BufferedImage viewBI;
	// private BufferedImage adjustedBufferedImage;
	int w, h, pixel;

	BufferedImage viewBI;

	public NeuralViewer(String name) {

		try {
			viewBI = ImageIO.read(new File("./src/nnn/viewer/" + name));
			w = viewBI.getWidth(null);
			h = viewBI.getHeight(null);
			if (viewBI.getType() != BufferedImage.TYPE_INT_RGB) {
				BufferedImage bi2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

				Graphics big = bi2.getGraphics();

				big.drawImage(viewBI, 0, 0, null);
				viewBI = bi2; // adjustedBufferedImage =
				viewBI = bi2;

			}
		} catch (IOException e) {
			System.out.println("Image could not be read");
			System.exit(1);
		}

	}

	public Dimension getPreferredSize() {
		return new Dimension(w, h);
	}

	String[] getDescriptions() {
		return descs;
	}

	void setOpIndex(int i) {
		opIndex = i;
	}

	public void paint(Graphics g) {
		// adjustImage();
		super.paint(g);

		g.drawImage(this.viewBI, 0, 0, null);
	}

	/*
	 * public void adjustImage() { BufferedImageOp op = null;
	 * 
	 * 
	 * "Zoom in 2X", "Zoom out 2X", "Zoom out 10X", "Original"
	 * 
	 * int rgb; switch (opIndex) {
	 * 
	 * case 0: Zoom in 2X adjustedBufferedImage = new BufferedImage(2*w, 2*h,
	 * BufferedImage.TYPE_INT_RGB); //setSize(2*w, 2*h); for(int w2x = 0;w2x < w;
	 * w2x++) { for (int h2x = 0; h2x < h; h2x++) { rgb = viewBI.getRGB(w2x, h2x) ;
	 * //System.out.println("Width : "+w2x+" Height: "+ h2x);
	 * adjustedBufferedImage.setRGB(w2x*2, h2x*2, rgb);
	 * adjustedBufferedImage.setRGB(w2x*2 +1, h2x*2, rgb);
	 * adjustedBufferedImage.setRGB(w2x*2, h2x*2 +1, rgb);
	 * adjustedBufferedImage.setRGB(w2x*2 +1, h2x*2 +1, rgb); } } break; case 1:
	 * Zoom out 2X case 2: Zoom out 10X pixel = 0xffc0c0c0; for(int a= 0;a <100;a++)
	 * { for(int b =0;b<100;b++) { adjustedBufferedImage.setRGB(a,b, pixel); } }
	 * break;
	 * 
	 * case 3 : Original adjustedBufferedImage = viewBI; original break; } }
	 * 
	 * Return the formats sorted alphabetically and in lower case public String[]
	 * getFormats() { String[] formats = ImageIO.getWriterFormatNames();
	 * TreeSet<String> formatSet = new TreeSet<String>(); for (String s : formats) {
	 * formatSet.add(s.toLowerCase()); } return formatSet.toArray(new String[0]); }
	 * 
	 * public void actionPerformed(ActionEvent e) { System.out.println("action");
	 * JComboBox cb = (JComboBox)e.getSource(); if
	 * (cb.getActionCommand().equals("SetFilter")) {
	 * setOpIndex(cb.getSelectedIndex()); repaint(); } else if
	 * (cb.getActionCommand().equals("Formats")) { Save the filtered image in the
	 * selected format. The selected item will be the name of the format to use
	 * 
	 * String format = (String)cb.getSelectedItem(); Use the format name to
	 * initialise the file suffix. Format names typically correspond to suffixes
	 * 
	 * File saveFile = new File("savedimage."+format); JFileChooser chooser = new
	 * JFileChooser(); chooser.setSelectedFile(saveFile); int rval =
	 * chooser.showSaveDialog(cb); if (rval == JFileChooser.APPROVE_OPTION) {
	 * saveFile = chooser.getSelectedFile(); Write the filtered image in the
	 * selected format, to the file chosen by the user.
	 * 
	 * try { ImageIO.write(adjustedBufferedImage, format, saveFile); } catch
	 * (IOException ex) { } } } };
	 * 
	 */ public static void main(String s[]) {

		Space dSpace = new Space();
		Space tSpace = dSpace;

		// fake a 200 by 200 by 200 nerve space for test viewing
		@SuppressWarnings("unused")
		Nerve nerve1 = new Nerve(new Location(100, 100, 100), new Location(-100, 100, 100), dSpace, tSpace);
		@SuppressWarnings("unused")
		Nerve nerve2 = new Nerve(new Location(100, 100, -100), new Location(-100, 100, -100), dSpace, tSpace);
		@SuppressWarnings("unused")
		Nerve nerve3 = new Nerve(new Location(100, -100, -100), new Location(-100, -100, -100), dSpace, tSpace);
		@SuppressWarnings("unused")
		Nerve nerve4 = new Nerve(new Location(100, -100, 100), new Location(-100, -100, 100), dSpace, tSpace);
		
		
		
	
		
		Nerve nerve01 = new Nerve (new Location(10,10,30), new Location(10,10,25), dSpace, tSpace);
		Nerve nerve02 = new Nerve (new Location(10,10,24), new Location(10,10,20), dSpace, tSpace);
		Nerve nerve03 = new Nerve (new Location(10,10,19), new Location(10,10,15), dSpace, tSpace);
		Nerve nerve04 = new Nerve (new Location(10,10,14), new Location(10,10,10), dSpace ,tSpace);
		Nerve nerve05 = new Nerve (new Location(10,10,9), new Location(10,50,10), dSpace, tSpace);
		Nerve nerve06 = new Nerve (new Location(10,50,11), new Location(10,30,15), dSpace, tSpace);
		Nerve nerve07 = new Nerve (new Location(10,30,14), new Location(10,-10,10), dSpace, tSpace);
		Nerve nerve08 = new Nerve (new Location(10,-10,9), new Location(10,-20,10), dSpace, tSpace);
		
		System.out.println("first stimulation 1.0");
		nerve01.getDendrites().get(0).stimulate(1);

		try {
			Thread.sleep(10);
			System.out.println("sleeping 10");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
		}
		System.out.println("second stimulation 0.9");
		nerve01.getDendrites().get(0).stimulate(0.9);
	
		try {
			Thread.sleep(5);
			System.out.println("sleeping 5");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
		}
		System.out.println("third stimulation 1.0");
		nerve01.getDendrites().get(0).stimulate(1);
		
		try {
			Thread.sleep(5);
			System.out.println("sleeping 5");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
		}
		System.out.println("fourth stimulation 1.0");
		nerve01.getDendrites().get(0).stimulate(1);
		
		try {
			Thread.sleep(5);
			System.out.println("sleeping 5");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
		}
		System.out.println("fifth stimulation 1.0");
		nerve01.getDendrites().get(0).stimulate(1);
	
		try {
			Thread.sleep(15);
			System.out.println("sleeping 15");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
		}
		System.out.println("sixth stimulation 3.0");
		nerve01.getDendrites().get(0).stimulate(3);
	
		try {
			Thread.sleep(15);
			System.out.println("sleeping 15");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
		}
		
		
		
		
		
		

		JFrame frame = new JFrame("Views of Neural spaces");
		
		JPanel viewPanel = new JPanel(false);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		NeuralViewer viewImage = new NeuralViewer("index.jpg");
		TwoDSpaceView eyeImage = new TwoDSpaceView("index4.jpg");

		viewPanel.add(viewImage, BorderLayout.CENTER);

		JTabbedPane tabbed = new JTabbedPane();

	
		tabbed.addTab("View", viewPanel);
		tabbed.addTab("Eye", eyeImage);
		ThreeDSpaceViewer brainView = new ThreeDSpaceViewer(dSpace);

		tabbed.addTab(" Grey X by Y", brainView.getjPanels()[ThreeDSpaceViewer.XYIndex]);
		tabbed.addTab(" Grey X by Z", brainView.getjPanels()[ThreeDSpaceViewer.XZIndex]);
		tabbed.addTab(" Grey Z by Y", brainView.getjPanels()[ThreeDSpaceViewer.ZYIndex]);
		
		/*
		 * JComboBox choices = new JComboBox(viewImage.getDescriptions());
		 * choices.setActionCommand("SetFilter"); choices.addActionListener(viewImage);
		 * JComboBox formats = new JComboBox(viewImage.getFormats());
		 * formats.setActionCommand("Formats"); formats.addActionListener(viewImage);
		 * JPanel panel = new JPanel(); panel.add(choices); panel.add(new
		 * JLabel("Save As")); panel.add(formats); tabbed.addTab("Options", panel);
		 */
		
		
		JScrollPane scroller = new JScrollPane(tabbed,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
		        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		frame.getContentPane().add(scroller);
		Dimension d = new Dimension(450,450);
		frame.setPreferredSize(d);
		frame.validate();

		frame.pack();

		frame.setVisible(true);
	}
}
