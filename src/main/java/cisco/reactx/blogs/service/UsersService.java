package cisco.reactx.blogs.service;

import java.util.List;

import cisco.reactx.blogs.api.BlogException;
import cisco.reactx.blogs.api.DataNotFoundException;
import cisco.reactx.blogs.api.DuplicateDataException;
import cisco.reactx.blogs.api.InvalidDataException;
import cisco.reactx.blogs.api.User;
import cisco.reactx.blogs.api.Users;
import cisco.reactx.blogs.data.UsersDAO;
import cisco.reactx.blogs.data.UsersDAOImpl;

public class UsersService implements Users {

	private UsersDAO dao = new UsersDAOImpl();

	private static UsersService usersService = null;

	private UsersService() {
	}

	public static UsersService getInstance() {
		if (usersService == null) {
			usersService = new UsersService();
		}
		return usersService;
	}

	
	public void create(User user) throws InvalidDataException, DuplicateDataException, BlogException {
		if (user == null)
			throw new BlogException();
		User temp = dao.read(user.getUserName());
		if (temp == null) {
			dao.create(user);
		}
		else {
			throw new DuplicateDataException();
		}
	}

	
	public User read(String userName) throws DataNotFoundException, BlogException {
		User user = dao.read(userName);
		if (user == null)
			throw new DataNotFoundException();
		return user;
	}

	
	public List<User> readAllUsers() throws DataNotFoundException, BlogException {
		List<User> users = dao.readAllUsers();
		if (users == null)
			throw new DataNotFoundException();
		return users;
	}

	
	public User update(User updatedUser) throws InvalidDataException, DuplicateDataException, BlogException {
		if (updatedUser == null)
			throw new InvalidDataException();

		try {
			dao.update(updatedUser);
		} catch (Exception e) {
			throw new BlogException();
		}
		return updatedUser;
	}

	
	public void delete(String userName) throws BlogException {
		read(userName);
		try {
			dao.delete(userName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BlogException();
		}
	}

	
	public User authenticate(String userName, String password) throws InvalidDataException, BlogException {
		User user = dao.readByUserIdAndPassword(userName, password);
		if (user == null)
			throw new InvalidDataException("Invalid user/password");
		return user;
	}

}
