package cisco.reactx.blogs.data;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import com.mongodb.MongoClient;

import cisco.reactx.blogs.api.User;
import cisco.reactx.blogs.util.Constants;


public class UsersDAOImpl extends BasicDAO<User, String> implements UsersDAO {

	public static MongoClient mongoClient = new MongoClient(Constants.MONGO_IP);
	public static Morphia morphia = new Morphia();
	public static Datastore datastore = morphia.createDatastore(mongoClient, "cmad_blog");

    public UsersDAOImpl() {
        this(User.class, datastore);
    }

	public UsersDAOImpl(Class<User> entityClass, Datastore ds) {
		super(entityClass, ds);
	}

	public User read(String userName) {
        User user = findOne("_id", userName);
        return user;
	}
	
	public void create(User user) {
		try {
	        save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(User updatedUser) {
        User temp = read(updatedUser.getUserName());
		temp.setFirstName(updatedUser.getFirstName());
		temp.setLastName(updatedUser.getLastName());
		temp.setEmailId(updatedUser.getEmailId());
		temp.setPassword(updatedUser.getPassword());
		temp.setImage(updatedUser.getImage());
        save(temp);
	}

	
	public void delete(String userName) {
        User user = read(userName);
        delete(user);
	}

	
	public User readByUserIdAndPassword(String userName, String password) {
		User user = createQuery().field("_id").contains(userName).field("password").contains(password).get();
		return user;
	}

	
	public List<User> readAllUsers() {
        List<User> users = createQuery().asList();
        return users;
	}

	
}
