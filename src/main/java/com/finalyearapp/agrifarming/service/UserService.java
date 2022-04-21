package com.finalyearapp.agrifarming.service;




import com.finalyearapp.agrifarming.helper.RegistrationRequest;
import com.finalyearapp.agrifarming.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService{
	User save(RegistrationRequest registrationDto);
	void updatePassword(User user);
	User findByEmail(String email);
	User getUserById(long id);
	List<User> findAllUsers();
}
