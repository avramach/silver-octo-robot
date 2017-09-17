package cisco.reactx.blogs.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cisco.reactx.blogs.api.Blog;

public class SearchResults {

	private  long messageMatchCount = 0L;
	private  long titleMatchCount = 0L;
	private  long categoryMatchCount = 0L;
	List<Blog> titleMatchList = new ArrayList<Blog>();
	List<Blog> messageMatchList=  new ArrayList<Blog>(); 
	List<Blog> categoryMatchList = new ArrayList<Blog>();
	private Set<Blog> reducedSet = new HashSet<Blog>();

	
	public List<Blog> getTitleMatchList() {
		return titleMatchList;
	}
	public void setTitleMatchList(List<Blog> titleMatchList) {
		this.titleMatchList = titleMatchList;
	}
	public List<Blog> getMessageMatchList() {
		return messageMatchList;
	}
	public void setMessageMatchList(List<Blog> messageMatchList) {
		this.messageMatchList = messageMatchList;
	}
	public List<Blog> getCategoryMatchList() {
		return categoryMatchList;
	}
	public void setCategoryMatchList(List<Blog> categoryMatchList) {
		this.categoryMatchList = categoryMatchList;
	}
	public long getMessageMatchCount() {
		return messageMatchCount;
	}
	public void setMessageMatchCount(long messageMatchCount) {
		this.messageMatchCount = messageMatchCount;
	}
	public long getTitleMatchCount() {
		return titleMatchCount;
	}
	public void setTitleMatchCount(long titleMatchCount) {
		this.titleMatchCount = titleMatchCount;
	}
	public long getCategoryMatchCount() {
		return categoryMatchCount;
	}
	public void setCategoryMatchCount(long categoryMatchCount) {
		this.categoryMatchCount = categoryMatchCount;
	}
    public void addTitleMatch(Blog blog) {
    	this.titleMatchCount++;
    	this.titleMatchList.add(blog);
    }
    public void addCategoryMatch(Blog blog) {
    	this.categoryMatchCount++;
    	this.categoryMatchList.add(blog);
    }
    public void addMessageMatch(Blog blog) {
    	this.messageMatchCount++;
    	this.messageMatchList.add(blog);
    }
    public Set<Blog> getReducedSet() {
		return reducedSet;
	}
	public void setReducedSet(Set<Blog> reducedSet) {
		this.reducedSet = reducedSet;
	}
	
    public void reduce() {
    	this.titleMatchList.forEach(item -> {getReducedSet().add(item);});
		this.messageMatchList.forEach(item -> {getReducedSet().add(item);});
		this.categoryMatchList.forEach(item -> {getReducedSet().add(item);});
        System.out.println("BEFORE REDUCE COUNTS: " + " TITLE "+ this.getTitleMatchCount() + " MESSAGE " + this.getMessageMatchCount() + " CATEGORY " + this.getCategoryMatchCount());
		this.setCategoryMatchCount(categoryMatchList.size());
        this.setMessageMatchCount(messageMatchList.size());
        this.setTitleMatchCount(titleMatchList.size());
        System.out.println("AFTER REDUCE COUNTS: " + " TITLE "+ this.getTitleMatchCount() + " MESSAGE " + this.getMessageMatchCount() + " CATEGORY " + this.getCategoryMatchCount());
    }
    
    public long getTotalMatches() {
    	long totalMatches = this.getCategoryMatchCount() + this.getMessageMatchCount() + this.getTitleMatchCount();
        return totalMatches;
    }
    
    public void report() {
    	System.out.println("SEARCH RESULT STAT: " + "TITLE "+ this.getTitleMatchCount() + " MESSAGE " + this.getMessageMatchCount() + " CATEGORY " + this.getCategoryMatchCount());
    }
    
}
