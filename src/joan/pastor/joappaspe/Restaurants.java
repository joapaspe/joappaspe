package joan.pastor.joappaspe;

import java.util.HashMap;



public class Restaurants {

	public static HashMap<RESTAURANTS, Restaurants> restaurants_resources;
	
	public enum RESTAURANTS {
		NONE, VELLA, CONSERVA, BURGER_KING, KEBAB
	};
	

    static {
        restaurants_resources = new HashMap<RESTAURANTS, Restaurants>();
        restaurants_resources.put(RESTAURANTS.VELLA, new Restaurants(R.string.vella,R.drawable.vella));
        restaurants_resources.put(RESTAURANTS.CONSERVA, new Restaurants(R.string.conserva));
        restaurants_resources.put(RESTAURANTS.BURGER_KING, new Restaurants(R.string.burger));
        restaurants_resources.put(RESTAURANTS.KEBAB, new Restaurants(R.string.kebab,R.drawable.kebab));
        
    }
	public int idName;
	public int idImg;
	/**
	 * @param args
	 */
	public Restaurants(int idName) {
		this.idName = idName;
	}
	public Restaurants(int idName, int idImg) {
		this.idName = idName;
		this.idImg  = idImg;
	}
}
