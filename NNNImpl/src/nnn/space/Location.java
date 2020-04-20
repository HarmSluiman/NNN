package nnn.space;

/**
 * Location is a point in 3 dimensional space.
 * <p>
 * Primary usage is to physically locate the Terminal and Dendrite ends of a nerve.
 * <p>
 * 
 * 
 */
public class Location {

	private int x_axis = 0;
	private int y_axis = 0;
	private int z_axis = 0;
	
	public int getX_axis() {
		return x_axis;
	}
	public void setX_axis(int x_axis) {
		this.x_axis = x_axis;
	}
	public int getY_axis() {
		return y_axis;
	}
	public void setY_axis(int y_axis) {
		this.y_axis = y_axis;
	}
	public int getZ_axis() {
		return z_axis;
	}
	public void setZ_axis(int z_axis) {
		this.z_axis = z_axis;
	}
	
	public Location(int x, int y, int z ) {
		setX_axis(x);
		setY_axis(y);
		setZ_axis(z);
	}
	public String toString() {
		return " "+getX_axis()+" "+getY_axis()+" "+getY_axis();
		
	}

}
