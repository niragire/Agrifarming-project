package com.finalyearapp.agrifarming.repository;

import com.finalyearapp.agrifarming.model.Expense;
import com.finalyearapp.agrifarming.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long>{
    @Query(value="SELECT * FROM Expense e where e.user_id = :user_id",nativeQuery=true)
    List<Expense> findAllById(@Param(value="user_id") Long user_id);
}
