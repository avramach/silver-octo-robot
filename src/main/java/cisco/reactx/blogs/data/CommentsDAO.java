package cisco.reactx.blogs.data;

import java.util.List;

import cisco.reactx.blogs.api.Comment;

public interface CommentsDAO {
    public void create(Comment comment);

    public Comment read(long commentId);

    public List<Comment> readAllByBlogId(long blogId);

    public void update(Comment comment);

    public void delete(long id);

    public long readCountByBlogId(long blogId);
}
