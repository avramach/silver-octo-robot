package cisco.reactx.blogs.service;

import java.util.List;

import cisco.reactx.blogs.api.BlogException;
import cisco.reactx.blogs.api.Comment;
import cisco.reactx.blogs.api.Comments;
import cisco.reactx.blogs.api.DataNotFoundException;
import cisco.reactx.blogs.api.DuplicateDataException;
import cisco.reactx.blogs.api.InvalidDataException;
import cisco.reactx.blogs.data.CommentsDAO;
import cisco.reactx.blogs.data.CommentsDAOImpl;

public class CommentsService implements Comments{

	private CommentsDAO dao = new CommentsDAOImpl();
	
	private static CommentsService commentsService = null;

    private CommentsService() {
    }

    public static CommentsService getInstance() {
        if (commentsService == null) {
            commentsService = new CommentsService();
        }
        return commentsService;
    }
	

	public Comment read(long commentId) throws DataNotFoundException, BlogException {
		Comment comment = dao.read(commentId);
        if (comment == null)
            throw new DataNotFoundException();
        return comment;
	}

	public void create(Comment comment) throws InvalidDataException, DuplicateDataException, BlogException {
		if (comment == null)
            throw new InvalidDataException();
        dao.create(comment);
	}

	public Comment update(Comment updatedComment) throws DataNotFoundException, BlogException {
		if (updatedComment == null)
            throw new BlogException();

        try {
            dao.update(updatedComment);
        } catch (Exception e) {
            throw new DataNotFoundException();
        }
        return updatedComment;
	}

	
	public void delete(long id) throws BlogException {
		read(id);
        try {
            dao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BlogException();
        }
	}
	

	public List<Comment> readAllByBlogId(int offset ,int count ,long blogId) throws DataNotFoundException, BlogException {
		List<Comment> comments = dao.readAllByBlogId(offset, count ,blogId);
        if (comments == null)
            throw new DataNotFoundException();
        return comments;
	}

	public long readCountByBlogId(long blogId) throws DataNotFoundException, BlogException {
		long count = dao.readCountByBlogId(blogId);
        return count;
	}

}
