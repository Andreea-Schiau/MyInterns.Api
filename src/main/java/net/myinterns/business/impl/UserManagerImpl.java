package net.myinterns.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mysql.cj.xdevapi.SessionFactory;

import net.andree.MyInterns.common.dto.UserDTO;
import net.myinterns.business.UserManager;
import net.myinterns.persistance.dao.UserDao;
import net.myinterns.persistance.entity.User;

@Repository
public class UserManagerImpl implements UserManager {

	final ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	UserDao userDao = appContext.getBean(UserDao.class);

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public SessionFactory sessionFactory;
	
	@Override
	public List<UserDTO> getAll() {

		return userDao.getAll();
	}

	@Override
	public UserDTO getById(long id) {

		User user = new User();
		user = userDao.getById(id);

		UserDTO userDTO = new UserDTO();

		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		userDTO.setIsMentor(user.getIsMentor());

		return userDTO;
	}

	@Override
	public UserDTO getByUsername(String username) {

		userDao.getByUsername(username);

		User user = new User();
		user = userDao.getByUsername(username);

		UserDTO userDTO = new UserDTO();

		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		userDTO.setIsMentor(user.getIsMentor());
		userDTO.setId(user.getId());

		return userDTO;
	}

	@Override
	public UserDTO saveOrUpdate(final String username, final String password, final boolean isMentor) {

		userDao.saveOrUpdate(username, password, isMentor);

		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);
		userDTO.setPassword(password);
		userDTO.setIsMentor(isMentor);

		return userDTO;
	}

	@Override
	public UserDTO saveOrUpdate(final String username, final String password) {

		userDao.saveOrUpdate(username, password);

		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);
		userDTO.setPassword(password);

		return userDTO;
	}

	@Override
	public UserDTO saveOrUpdate(final UserDTO userDTO) {

		String password = userDTO.getPassword();
		String username = userDTO.getUsername();
		boolean isMentor = userDTO.getIsMentor();

		saveOrUpdate(username, password, isMentor);

		return userDTO;
	}

	@Override
	public void delete(long id) {

		UserDTO userDTO = new UserDTO();
		userDTO.setId(id);
		userDao.delete(userDTO);
	}
	
	@Override
	public void deleteByUsername(String username) {

		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);
		userDao.deleteByUsername(userDTO);
		
//		userDao.deleteByUsername(username);
	}

	@Override
	public UserDTO login(String username, String password) {

		UserDTO userDTO = new UserDTO();

		try {
			User userObj = userDao.getUser(username, password);

			if (userObj != null && userObj.getUsername() != null) {

				userDTO.setUsername(userObj.getUsername());

				return userDTO;

			} else {
				System.out.println("The username or the password is incorrect!");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return userDTO;
		}
	}

	@Override
	public UserDTO update(UserDTO userDTO, long id) {

		User user = new User();
		user.setPassword(userDTO.getPassword());
		user.setUsername(userDTO.getUsername());
		user.setId(id);

		User userUpdated = new User();

		userUpdated = userDao.update(user, id);

		userDTO.setPassword(userUpdated.getPassword());
		userDTO.setUsername(userUpdated.getUsername());
		userDTO.setId(id);

		return userDTO;
	}

	@Override
	public UserDTO updateByUsername(UserDTO userDTO, String username) {

		User user = new User();
		user.setIsMentor(userDTO.getIsMentor());
		user.setPassword(userDTO.getPassword());
		user.setUsername(userDTO.getUsername());
		User userUpdated = new User();

		userUpdated = userDao.updateByUsername(user, username);

		userDTO.setIsMentor(userUpdated.getIsMentor());
		userDTO.setPassword(userUpdated.getPassword());
		userDTO.setUsername(userUpdated.getUsername());

		return userDTO;
	}
}