package net.myinterns.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.andree.MyInterns.common.dto.StudentDTO;
import net.andree.MyInterns.common.dto.UserDTO;
import net.myinterns.business.StudentManager;
import net.myinterns.persistance.dao.StudentDao;

@Repository
public class StudentManagerImpl implements StudentManager {

	@Autowired
	StudentDao studentDao;

	@Override
	public List<StudentDTO> getAll() {
		
		return studentDao.getAll();
	}

	@Override
	public StudentDTO getById(long id) {
		
		return studentDao.getById(id);
	}

	@Override
	public void saveOrUpdate(String name, String surname, String description, String email, UserDTO userDTO) {

		studentDao.saveOrUpdate(name, surname, description, email, userDTO);
	}

	@Override
	public void saveOrUpdate(StudentDTO studentDTO) {

		studentDao.saveOrUpdate(studentDTO);
	}

	@Override
	public void delete(long id) {

		studentDao.delete(id);
	}
}