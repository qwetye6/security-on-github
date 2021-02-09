
public class Location {
	private double latitude;
	private double longitude;
	private String address;
	private String name;
	private String type;
	private String comment;
	private int rating;
	
	public Location(String name, String address, String type, double latitude, double longitude, String comment, int rating)
	{
		this.name = name;
		this.address = address;
		this.type = type;
		this.latitude = latitude;
		this.longitude = longitude;
		this.comment = comment;
		this.rating = rating;
	}
	
	
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String toString()
	{	
		return name;
	}
}
