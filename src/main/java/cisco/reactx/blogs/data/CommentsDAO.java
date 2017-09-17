package cisco.reactx.blogs.data;

import java.util.List;

import cisco.reactx.blogs.api.Comment;

public interface CommentsDAO {
	
    public Comment read(long commentId);

    public void create(Comment comment);
    
    public void update(Comment comment);

    public void delete(long id);
    
    public List<Comment> readAllByBlogId(int offset, int count ,long blogId);

    public long readCountByBlogId(long blogId);
}
