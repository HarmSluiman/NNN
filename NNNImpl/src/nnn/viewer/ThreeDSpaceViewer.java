package nnn.viewer;

import javax.swing.*;
import java.math.*;

import java.awt.Graphics;
import java.util.*;
import nnn.space.*;
import nnn.nerve.*;

import nnn.space.Space;
import nnn.utility.Logger;

public class ThreeDSpaceViewer {

	private JPanel jPanels[] = { null, null, null };
	public static final int XYIndex = 0;
	public static final int XZIndex = 1;
	public static final int ZYIndex = 2;

	public JPanel[] getjPanels() {
		return jPanels;
	}

	public ThreeDSpaceViewer(Space space) {

		// TwoDSpaceView xyImage
		getjPanels()[XYIndex] = new TwoDSpaceView(space.getXRangeSize(), space.getYRangeSize(), Colours.GreyMatter);
		// TwoDSpaceView xzImage
		getjPanels()[XZIndex] = new TwoDSpaceView(space.getXRangeSize(), space.getZRangeSize(), Colours.GreyMatter);
		// TwoDSpaceView zyImage
		getjPanels()[ZYIndex] = new TwoDSpaceView(space.getZRangeSize(), space.getYRangeSize(), Colours.GreyMatter);

		renderLocations(space);
	}

	private void renderLocations(Space space) {

		for (String l : space.getLocationHash().keySet()) {
			Logger.log("     Location: " + l + "  Object " + space.getLocationHash().get(l));
			int rgb = 0, x = 0, y = 0, z = 0;
			if (space.getLocationHash().get(l) instanceof Dendrite) {
				rgb = Colours.UsedDendrite;
				Dendrite d = (Dendrite) space.getLocationHash().get(l);
				x = d.getDendriteRootLocation().getX_axis();
				if (x <=0){
					x =   Math.abs(space.getMinX()) +x;
				}else {
					x = x- space.getMinX();
				}
				x = TwoDSpaceView.DimensionPointFactor * x;
				
				y = d.getDendriteRootLocation().getY_axis();
				if (y <=0){
					y =   Math.abs(space.getMinY()) +y;
				}else {
					y = y- space.getMinY();
				}
				y = TwoDSpaceView.DimensionPointFactor * y;
				
				z = d.getDendriteRootLocation().getZ_axis();
				if (z <=0){
					z =   Math.abs(space.getMinZ()) +z;
				}else {
					z = z- space.getMinZ();
				}
				z = TwoDSpaceView.DimensionPointFactor * z;
				
				
				
			}
			if (space.getLocationHash().get(l) instanceof Terminal) {
				rgb = Colours.UsedTerminal;
				Terminal t = (Terminal) space.getLocationHash().get(l);
				x = t.getMyLocation().getX_axis();
				if (x <=0){
					x =   Math.abs(space.getMinX()) +x;
				}else {
					x = x- space.getMinX();
				}
				x = TwoDSpaceView.DimensionPointFactor * x;
				
				y = t.getMyLocation().getY_axis();
				if (y <=0){
					y =   Math.abs(space.getMinY()) +y;
				}else {
					y = y- space.getMinY();
				}
				y = TwoDSpaceView.DimensionPointFactor * y;
				
				z = t.getMyLocation().getZ_axis();
				if (z <=0){
					z =   Math.abs(space.getMinZ()) +z;
				}else {
					z = z- space.getMinZ();
				}
				z = TwoDSpaceView.DimensionPointFactor * z;
			}

			drawLocation((TwoDSpaceView) getjPanels()[XYIndex], x, y, rgb);
			drawLocation((TwoDSpaceView) getjPanels()[XZIndex], x, z, rgb);
			drawLocation((TwoDSpaceView) getjPanels()[ZYIndex], z, y, rgb);

		}
		
		getjPanels()[XYIndex].repaint();
		getjPanels()[XZIndex].repaint();
		getjPanels()[ZYIndex].repaint();
	}

	private void drawLocation(TwoDSpaceView v, int x, int y, int rgb) {		
		for (int xi =0; xi< TwoDSpaceView.DimensionPointFactor; xi++) {
			for (int yi = 0; yi < TwoDSpaceView.DimensionPointFactor; yi++) {
				v.getBufferedImage().setRGB(x+xi, y+yi, rgb);				
			}						
		}
		
		

	}

}
