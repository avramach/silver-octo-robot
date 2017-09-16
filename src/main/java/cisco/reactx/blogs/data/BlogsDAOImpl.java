package cisco.reactx.blogs.data;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.FindOptions;

import com.mongodb.MongoClient;

import cisco.reactx.blogs.api.Blog;

public class BlogsDAOImpl extends BasicDAO<Blog, Long> implements BlogsDAO {

	//public static MongoClient mongoClient = new MongoClient("ec2-34-209-76-193.us-west-2.compute.amazonaws.com:27017");
	//public static MongoClient mongoClient = new MongoClient("172.31.39.116:27017");
	public static MongoClient mongoClient = new MongoClient("localhost:27017");
	public static Morphia morphia = new Morphia();
	public static Datastore datastore = morphia.createDatastore(mongoClient, "cmad_blog");
	private static final AtomicInteger index = new AtomicInteger(0);

    public BlogsDAOImpl() {
        this(Blog.class, datastore);
        Blog blog = findOne(createQuery().order("-blogId"));
        if (blog != null) {
            index.set((int)blog.getBlogId());
        }
    }

	public BlogsDAOImpl(Class<Blog> entityClass, Datastore ds) {
		super(entityClass, ds);
	}

	public void create(Blog blog) {
		try {
			blog.setBlogId(index.incrementAndGet());
	        save(blog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Blog read(long blogId) {
        Blog blog = findOne("_id", blogId);
        return blog;
	}

	public List<Blog> readByCategory(String category) {
		List<Blog> blogs = null;
		if (category==null) {
			blogs = createQuery().asList();
			return blogs;
		}
		else {
			Pattern regexp = Pattern.compile(category, Pattern.CASE_INSENSITIVE);
			blogs = createQuery().filter("category",regexp).order("-createDate").asList();
			return blogs;
		}
	}

	public List<Blog> readAllBlogs(int offset, int count, String category) {
		List<Blog> blogs;
		if (category != null) {
			if (count != 0) {
				FindOptions options = new FindOptions();
				options.skip(offset * count).limit(count);
				blogs = createQuery().filter("category", category).order("-createDate").asList(options);
			}
			else {
				blogs = createQuery().filter("category", category).order("-createDate").asList();
			}
		}
		else {
			if (count != 0) {
				FindOptions options = new FindOptions();
				options.skip(offset * count).limit(count);
				blogs = createQuery().order("-createDate").asList(options);
			}
			else {
				blogs = createQuery().order("-createDate").asList();
			}
		}
        return blogs;
	}

	public List<Blog> readByUserId(String userId) {
        List<Blog> blogs = createQuery().filter("author", userId).order("-createDate").asList();
        return blogs;
	}

	public void update(Blog blog) {
        Blog temp = read(blog.getBlogId());
		temp.setBlogMessage(blog.getBlogMessage());
		temp.setTitle(blog.getTitle());
		temp.setCategory(blog.getCategory());
		temp.setImage(blog.getImage());
        save(temp);
    }

	public void delete(long blogId) {
        Blog blog = read(blogId);
        delete(blog);
	}
}
