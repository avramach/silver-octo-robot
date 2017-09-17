package cisco.reactx.blogs.data;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import com.mongodb.MongoClient;


import cisco.reactx.blogs.api.Comment;

public class CommentsDAOImpl extends BasicDAO<Comment, Long> implements CommentsDAO {

	//public static MongoClient mongoClient = new MongoClient("ec2-34-209-76-193.us-west-2.compute.amazonaws.com:27017");
	//public static MongoClient mongoClient = new MongoClient("172.31.39.116:27017");
	public static MongoClient mongoClient = new MongoClient("localhost:27017");
	public static Morphia morphia = new Morphia();
	public static Datastore datastore = morphia.createDatastore(mongoClient, "cmad_blog");
	private static final AtomicInteger index = new AtomicInteger(0);

    public CommentsDAOImpl() {
        this(Comment.class, datastore);
        Comment comment = findOne(createQuery().order("-commentId"));
        if (comment != null) {
            index.set((int)comment.getCommentId());
        }
    }

	public CommentsDAOImpl(Class<Comment> entityClass, Datastore ds) {
		super(entityClass, ds);
	}


	
	public Comment read(long commentId) {
        Comment comment = findOne("_id", commentId);
        return comment;
	}

	public void create(Comment comment) {
		try {
			comment.setCommentId(index.incrementAndGet());
	        save(comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Comment updatedComment) {
        Comment temp = read(updatedComment.getCommentId());
		temp.setCommentText(updatedComment.getCommentText());
		temp.setLikes(updatedComment.getLikes());
		save(temp);
	}


	public void delete(long commentId) {
        Comment comment = read(commentId);
        delete(comment);
	}


	public List<Comment> readAllByBlogId(int offset ,int count ,long blogId) {
        List<Comment> comments = createQuery().filter("blogId", blogId).order("-createDate").asList();
        return comments;
	}

	public long readCountByBlogId(long blogId) {
		List<Comment> comments = createQuery().filter("blogId", blogId).asList();
		return comments.size();
	}
}
