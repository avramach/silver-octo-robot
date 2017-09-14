package cisco.reactx.blogs.api;

import java.util.List;

public interface Users {
	public void create(User user) throws InvalidDataException, DuplicateDataException, BlogException;

	public User read(String userId) throws DataNotFoundException, BlogException;

	public List<User> readAllUsers() throws DataNotFoundException, BlogException;

	public User update(User user) throws InvalidDataException, DuplicateDataException, BlogException;

	public void delete(String userId) throws BlogException;
	
	public User authenticate(String userId, String password) throws InvalidDataException, BlogException;
}
