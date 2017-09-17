package cisco.reactx.blogs.api;

import java.util.List;

public interface Comments {

	public Comment read(long commentId) throws DataNotFoundException, BlogException;
	
	public void create(Comment comment) throws InvalidDataException, DuplicateDataException, BlogException;

	public Comment update(Comment comment) throws DataNotFoundException, BlogException;

	public void delete(long id) throws BlogException;

	public List<Comment> readAllByBlogId(int offset, int count ,long blogId) throws DataNotFoundException, BlogException;

	public long readCountByBlogId(long blogId) throws DataNotFoundException, BlogException;
	
}
