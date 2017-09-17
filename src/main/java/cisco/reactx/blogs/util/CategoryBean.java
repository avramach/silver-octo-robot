package cisco.reactx.blogs.util;

public class CategoryBean {
    private String category ;
    private String count ;
    public CategoryBean(String categoryName, String categoryCount) {
    	setCategory(categoryName);
    	setCount(categoryCount); 
    }
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
}
