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
import cisco.reactx.blogs.api.BlogException;
import cisco.reactx.blogs.api.DataNotFoundException;
import cisco.reactx.blogs.util.SearchResults;

public class BlogsDAOImpl extends BasicDAO<Blog, Long> implements BlogsDAO {

	public static MongoClient mongoClient = new MongoClient("localhost:27017");
	public static Morphia morphia = new Morphia();
	public static Datastore datastore = morphia.createDatastore(mongoClient, "cmad_blog");
	private static final AtomicInteger index = new AtomicInteger(0);

	public BlogsDAOImpl() {
		this(Blog.class, datastore);
		Blog blog = findOne(createQuery().order("-blogId"));
		if (blog != null) {
			index.set((int) blog.getBlogId());
		}
	}

	public BlogsDAOImpl(Class<Blog> entityClass, Datastore ds) {
		super(entityClass, ds);
	}

	public Blog read(long blogId) {
		Blog blog = findOne("_id", blogId);
		blog.setViewCount(blog.getViewCount()+1);
		save(blog);
		return blog;
	}

	public void create(Blog blog) {
		try {
			blog.setBlogId(index.incrementAndGet());
			save(blog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Blog blog) {
		Blog temp = read(blog.getBlogId());
		temp.setBlogMessage(blog.getBlogMessage());
		temp.setTitle(blog.getTitle());
		temp.setCategory(blog.getCategory());
		temp.setImage(blog.getImage());
		temp.setLikes(blog.getLikes());
		save(temp);
	}

	public void delete(long blogId) {
		Blog blog = read(blogId);
		delete(blog);
	}

	public List<Blog> readAllBlogs(int offset, int count, String category) {
		List<Blog> blogs;
		if (category != null) {
			if (count != 0) {
				FindOptions options = new FindOptions();
				options.skip(offset * count).limit(count);
				blogs = createQuery().filter("category", category).order("-createDate").asList(options);
			} else {
				blogs = createQuery().filter("category", category).order("-createDate").asList();
			}
		} else {
			if (count != 0) {
				FindOptions options = new FindOptions();
				options.skip(offset * count).limit(count);
				blogs = createQuery().order("-createDate").asList(options);
			} else {
				blogs = createQuery().order("-createDate").asList();
			}
		}
		return blogs;
	}

	public List<Blog> readByCategory(int offset, int count, String category) {
		List<Blog> blogs = null;
		
			FindOptions options = new FindOptions();
			options.skip(offset * count).limit(count);
			if (category == null) {
				blogs = createQuery().asList();

			} else {
				Pattern regexp = Pattern.compile(category, Pattern.CASE_INSENSITIVE);
				blogs = createQuery().filter("category", regexp).order("-createDate").asList(options);

			}
		return blogs;
	}

	public List<Blog> readByUserId(int offset, int count, String userId) {
		List<Blog> blogs = null;
		FindOptions options = new FindOptions();
		
		options.skip(offset * count).limit(count);
		blogs = createQuery().filter("author", userId).order("-createDate").asList(options);
		
		return blogs;
	}

	public SearchResults searchBlogs(int offset, int count, String searchString) {
		SearchResults results = new SearchResults();
		FindOptions options = new FindOptions();
		options.skip(offset * count).limit(count);
		
		Pattern regexp = Pattern.compile(".*"+searchString+".*", Pattern.CASE_INSENSITIVE);
		List<Blog> titleMatchList = createQuery().filter("title", regexp).order("-createDate").asList(options);
		List<Blog> messageMatchList = createQuery().filter("blogMessage", regexp).order("-createDate")
				.asList(options);
		List<Blog> categoryMatchList = createQuery().filter("category", regexp).order("-createDate")
				.asList(options);

		titleMatchList.forEach(item -> {
			results.addTitleMatch(item);
		});
		messageMatchList.forEach(item -> {
			results.addMessageMatch(item);
		});
		categoryMatchList.forEach(item -> {
			results.addCategoryMatch(item);
		});
		System.out.println("SEARCHING FOR " +searchString);
		results.report();
		return results;
	}

	public List<Blog> readSortedByLikes(int offset, int count) throws DataNotFoundException, BlogException {
		List<Blog> blogs = null;
		FindOptions options = new FindOptions();
		
		options.skip(offset * count).limit(count);
		blogs = createQuery().order("-viewCount").asList(options);
		
		return blogs;
	}

}
