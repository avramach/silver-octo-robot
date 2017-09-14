package cisco.reactx.blogs.service;

import java.util.ArrayList;
import java.util.List;

import cisco.reactx.blogs.api.Blog;
import cisco.reactx.blogs.api.BlogException;
import cisco.reactx.blogs.api.Blogs;
import cisco.reactx.blogs.api.DataNotFoundException;
import cisco.reactx.blogs.api.DuplicateDataException;
import cisco.reactx.blogs.api.InvalidDataException;
import cisco.reactx.blogs.data.BlogsDAO;
import cisco.reactx.blogs.data.BlogsDAOImpl;

public class BlogsService implements Blogs {

	private BlogsDAO dao = new BlogsDAOImpl();
	private static BlogsService blogsService = null;

	 private BlogsService() {
	    }
	 
	public static BlogsService getInstance() {
		if (blogsService == null) {
			blogsService = new BlogsService();
		}
		return blogsService;
	}

	
	public void create(Blog blog) throws InvalidDataException, DuplicateDataException, BlogException {
		if (blog == null)
            throw new InvalidDataException();
        /*if (dao.read(blog.getBlogId()) != null)
            throw new DuplicateDataException();*/
        dao.create(blog);
	}

	
	public Blog update(Blog updatedBlog) throws InvalidDataException, BlogException {
		 if (updatedBlog == null)
	            throw new InvalidDataException();
	        try {
	            dao.update(updatedBlog);
	        } catch (Exception e) {
	            throw new BlogException();
	        }
	        return updatedBlog;
	}

		
	public void delete(long blogId) throws DataNotFoundException, BlogException {
		read(blogId);
        try {
            dao.delete(blogId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BlogException();
        }
	}

	
	public List<Blog> readByCategory(String category) throws DataNotFoundException, BlogException {
		List<Blog> blogs = new ArrayList<Blog>();
        try {
            blogs = dao.readByCategory(category);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BlogException();
        }

        if (blogs == null || blogs.isEmpty())
            throw new DataNotFoundException();
        return blogs;
	}

	
	public List<Blog> readAllBlogs(int offset, int count, String category) throws DataNotFoundException, BlogException {
		 List<Blog> blogs = new ArrayList<Blog>();
	        try {
	            blogs = dao.readAllBlogs(offset, count, category);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new BlogException();
	        }

	        if (blogs == null || blogs.isEmpty())
	            throw new DataNotFoundException();
	        return blogs;
	}

	
	public Blog read(long blogId) throws DataNotFoundException, BlogException {
		Blog blog = null;
		try {
	            blog = dao.read(blogId);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new BlogException();
	        }

	        if (blog == null)
	            throw new DataNotFoundException();

	        return blog;
	}

	
	public List<Blog> readByUserId(String userId) throws DataNotFoundException, BlogException {
		 List<Blog> blogs = new ArrayList<Blog>();
	        try {
	            blogs = dao.readByUserId(userId);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new BlogException();
	        }

	        if (blogs == null || blogs.isEmpty())
	            throw new DataNotFoundException();
	        return blogs;
	}
}
