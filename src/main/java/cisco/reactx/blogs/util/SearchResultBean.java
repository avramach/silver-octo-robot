package cisco.reactx.blogs.util;

import java.util.ArrayList;
import java.util.List;

import cisco.reactx.blogs.api.Blog;

public class SearchResultBean {

	private String matchType;
	private long  count;
	List<Blog> blogList = new ArrayList<Blog>();
	public String getMatchType() {
		return matchType;
	}
	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List<Blog> getBlogList() {
		return blogList;
	}
	public void setBlogList(List<Blog> blogList) {
		this.blogList = blogList;
	}
	public SearchResultBean(String matchType, long count, List<Blog> matchBlogList) {
		this.matchType = matchType;
		this.count = count;
		for (Blog blog : matchBlogList) {
			blogList.add(blog);
		}
	}
}
