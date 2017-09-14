package cisco.reactx.blogs.api;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;


@Entity
public class Blog {

	@Id
	private long blogId;

	private String title;
	private String blogMessage;
	private String image;
	private String category;
	private long viewCount;
	private long likes;
	private String author;
	private Date createDate = new Date();
	@PrePersist void prePersist() { createDate = new Date(); }
	
	public Blog() {
	}

	public Blog(long id, String title, String blogMessage, Date date, String author, String image, String category, long likes, long viewCount) {
		super();
		this.blogId = id;
		this.title = title;
		this.setBlogMessage(blogMessage);
		this.setCreateDate(date);
		this.author = author;
		this.image = image;
		this.category = category;
		this.likes = likes;
		this.viewCount = viewCount;
	}

	public long getBlogId() {
		return blogId;
	}

	public void setBlogId(long blogId) {
		this.blogId = blogId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getViewCount() {
		return viewCount;
	}

	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
	}

	public long getLikes() {
		return likes;
	}

	public void setLikes(long likes) {
		this.likes = likes;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBlogMessage() {
		return blogMessage;
	}

	public void setBlogMessage(String blogMessage) {
		this.blogMessage = blogMessage;
	}
}
