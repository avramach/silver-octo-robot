package cisco.reactx.blogs.data;

import java.util.List;

import cisco.reactx.blogs.api.Blog;

public interface BlogsDAO {
	public void create(Blog blog);

	public Blog read(long id);

	public List<Blog> readByCategory(String category);

	public List<Blog> readAllBlogs(int offset, int count, String category);

	public List<Blog> readByUserId(String userId);

	public void update(Blog blog);

	public void delete(long id);

}
