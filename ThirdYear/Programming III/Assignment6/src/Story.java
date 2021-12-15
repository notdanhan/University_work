import java.util.List;

/** Story.java
 * @author Daniel Hannon (19484286)
 */

public class Story {
	private List<String> images;
	private String caption;
	private String title;

	public Story(List<String> images, String caption, String title) {
		this.images = images;
		this.caption = caption;
		this.title = title;
	}

	public List<String> getImages() {
		return images;
	}

	public String getCaption() {
		return caption;
	}

	public String getTitle() {
		return this.title;
	}
}
