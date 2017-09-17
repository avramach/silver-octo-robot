package cisco.reactx.blogs.data;

import java.util.List;

import cisco.reactx.blogs.api.Blog;
import cisco.reactx.blogs.api.BlogException;
import cisco.reactx.blogs.api.DataNotFoundException;
import cisco.reactx.blogs.util.SearchResults;

public interface BlogsDAO {
	
    public Blog read(long id);

	public void create(Blog blog);

	public void update(Blog blog);

	public void delete(long id);
	
	public List<Blog> readAllBlogs(int offset, int count, String category);
	
	public List<Blog> readByCategory(int offset, int count, String category);
	
	public List<Blog> readByUserId(int offset, int count, String userId);
	
	public List<Blog> readSortedByLikes(int offset, int count) throws DataNotFoundException, BlogException;

	public SearchResults searchBlogs(int offset, int count, String searchString);

}
