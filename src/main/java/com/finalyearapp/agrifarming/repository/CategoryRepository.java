package com.finalyearapp.agrifarming.repository;

import com.finalyearapp.agrifarming.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{

}
