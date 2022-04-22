package com.finalyearapp.agrifarming.service;




import com.finalyearapp.agrifarming.helper.RegistrationRequest;
import com.finalyearapp.agrifarming.model.User;
import java.util.List;


public interface UserService{
	User save(RegistrationRequest registrationDto);
	void updatePassword(User user);
	User findByEmail(String email);
	User getUserById(long id);
	List<User> findAllUsers();
}
