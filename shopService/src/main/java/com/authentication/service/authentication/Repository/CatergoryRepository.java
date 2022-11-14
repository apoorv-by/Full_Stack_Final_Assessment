package com.authentication.service.authentication.Repository;

import com.authentication.service.authentication.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatergoryRepository extends JpaRepository<Category,Integer> {

}
