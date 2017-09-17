package cisco.reactx.blogs.api;

import java.util.List;

import cisco.reactx.blogs.util.SearchResults;



public interface Blogs {

	public Blog read(long blogId) throws DataNotFoundException, BlogException;
	
	public void create(Blog blog) throws InvalidDataException, DuplicateDataException, BlogException;

	public Blog update(Blog updatedBlog) throws InvalidDataException, BlogException;

	public void delete(long blogId) throws DataNotFoundException, BlogException;

	public List<Blog> readByCategory(int offset, int count, String category) throws DataNotFoundException, BlogException;

	public List<Blog> readAllBlogs(int offset, int count, String category) throws DataNotFoundException, BlogException;
	
	public SearchResults searchAllBlogs(int offset, int count, String searchString) throws DataNotFoundException, BlogException;

	public List<Blog> readByUserId(int offset, int count, String userId) throws DataNotFoundException, BlogException;
	
	public List<Blog> readSortedByLikes(int offset, int count) throws DataNotFoundException, BlogException;

}
