/**
 * 
 */
package nnn.space;

import java.util.HashMap;


/**
 * @author harmsluiman
 * <p>
 * The Space class holds the 3 dimensional physical area where 
 * nerves exist, with X Y and Z axis.
 * <p>
 * Each 3 dimensional point is at a Location, and a location can only be
 * occupied by one entity. Locations are unique keys in a HashMap, and the
 * and the actual location in the space is the Object associated with the 
 * Location coordinates.
 * <p>
 * By default a Location can have 26 neighbors in the 3 dimensional space 
 * and 8 in a 2 dimensional space. 
 * <p>
 * The 3D space centers at 0 0 0, so the location axis can be positive or negative 
 * from this location.
 * <p>
 * A sensory space would typically be an area on a 2D plane, and likely  
 * on the outer surface of the space, and typically statically defined in
 * order to simulate something like a retina.
 *
 */
public class Space {
	
	private static HashMap<Location, Object> space = new HashMap<Location, Object>();
	
	public static void addLocation(Location location, Object object) {
		space.put(location, object);
	}

}
