package cisco.reactx.blogs.data;

import java.util.List;

import cisco.reactx.blogs.api.User;

public interface UsersDAO {
    public User read(String userId);

    public void create(User user);
    
    public void update(User user);

    public void delete(String userId);
    
    public List<User> readAllUsers();
    
    public User readByUserIdAndPassword(String userId, String password);


}
