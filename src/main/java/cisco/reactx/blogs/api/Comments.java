package cisco.reactx.blogs.api;

import java.util.List;

public interface Comments {
	public void create(Comment comment) throws InvalidDataException, DuplicateDataException, BlogException;

	public Comment read(long commentId) throws DataNotFoundException, BlogException;

	public List<Comment> readAllByBlogId(long blogId) throws DataNotFoundException, BlogException;

	public Comment update(Comment comment) throws DataNotFoundException, BlogException;

	public void delete(long id) throws BlogException;

	public long readCountByBlogId(long blogId) throws DataNotFoundException, BlogException;
	
	public long getUpVote(long commentId) throws DataNotFoundException, BlogException;
	
	public long getDownVote(long commentId) throws DataNotFoundException, BlogException;
}
