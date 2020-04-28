/**
 * 
 */
package nnn.space;

import java.util.ArrayList;
import java.util.HashMap;

import nnn.utility.Logger;
import nnn.nerve.Dendrite;

/**
 * @author harmsluiman
 *         <p>
 *         The Space class holds the 3 dimensional physical area where nerves
 *         exist, with X Y and Z axis.
 *         <p>
 *         Each 3 dimensional point is at a Location, and a location can only be
 *         occupied by one entity. Locations are unique keys in a HashMap, and
 *         the and the actual location in the space is the Object associated
 *         with the Location coordinates.
 *         <p>
 *         By default a Location can have 26 neighbors in the 3 dimensional
 *         space and 8 in a 2 dimensional space.
 *         <p>
 *         The 3D space centers at 0 0 0, so the location axis can be positive
 *         or negative from this location.
 *         <p>
 *         A sensory space would typically be an area on a 2D plane, and likely
 *         on the outer surface of the space, and typically statically defined
 *         in order to simulate something like a retina.
 *
 */
public class Space {

	private static HashMap<String, Object> space = new HashMap<String, Object>();

	public static void addLocation(Location location) {
		space.put(location.toString(), location.getOccupant());
	}

	public static void dumpLocations() {

		for (String l : space.keySet()) {
			Logger.log("     Location: " + l + "  Object " + space.get(l));
		}

	}

	public static ArrayList<Dendrite> findNeighborDendrites(Location center) {
	    ArrayList<Dendrite> dendrites =  new ArrayList<Dendrite>() ;
		
		boolean looking = true;
		Location criteria = new Location(0, 0, 0);
		int[] layer1 = { -1, 0, 1 };

		while (looking) {
			// first look for side by side neighbors using layer1
			Logger.log("Criteria Center " + center);
			for (int z = 0; z < layer1.length; z++) {
				// criteria.setZ_axis(center.getZ_axis() + layer1[z]);
				for (int x = 0; x < layer1.length; x++) {
					// criteria.setX_axis(center.getX_axis() + layer1[x]);
					for (int y = 0; y < layer1.length; y++) {
						// criteria.setY_axis(center.getY_axis() + layer1[y]);
						// if not the center x=y=z=0
						if (!(layer1[y] == 0 && layer1[x] == 0 && layer1[z] == 0)) {
							// scan the space
							criteria = new Location(center.getX_axis() + layer1[x], center.getY_axis() + layer1[y],
									center.getZ_axis() + layer1[z]);					
							if (space.containsKey(criteria.toString())) {
								Logger.log("   testing " + criteria);
								Logger.log("      Scan result = " + space.get(criteria.toString()));
								if (space.get(criteria.toString()) instanceof Dendrite) {
									Logger.log("found neighbor Dendrite");
									dendrites.add((Dendrite)space.get(criteria.toString()));	
								}	
							}

						}
					}
				}
			}
			looking = false;
		} // end while
		return dendrites;
	}
}
