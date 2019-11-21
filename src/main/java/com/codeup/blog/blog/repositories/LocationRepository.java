package com.codeup.blog.blog.repositories;

import com.codeup.blog.blog.models.Location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{


}


