package pl.bookstore.robot.bookstorecontener;

public class BookStore {

	private String url;
	private String siteName;
	private boolean isactive;

	public BookStore(String url, String siteName, boolean isactive) {
		this.url = url;
		this.isactive = isactive;
		this.siteName = siteName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public boolean isActive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	

}
