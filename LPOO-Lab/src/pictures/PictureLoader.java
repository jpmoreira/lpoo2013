package pictures;

public class PictureLoader {
	
	public static String getImagePath(String name){
		
		return PictureLoader.class.getResource(name).getPath();
	}

}
