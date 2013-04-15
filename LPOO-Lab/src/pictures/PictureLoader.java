package pictures;
/**
 * A class used to load pictures from the file System.
 * 
 * 
 *
 */
public class PictureLoader {
	
	public static String getImagePath(String name){
		
		return PictureLoader.class.getResource(name).getPath();
	}

}
