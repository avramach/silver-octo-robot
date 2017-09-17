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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (blogId ^ (blogId >>> 32));
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blog other = (Blog) obj;
		if (blogId != other.blogId)
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		return true;
	}
}
