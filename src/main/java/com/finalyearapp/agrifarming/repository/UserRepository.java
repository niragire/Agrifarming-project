package com.finalyearapp.agrifarming.repository;


import com.finalyearapp.agrifarming.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
   User findByEmail(String email);
}
