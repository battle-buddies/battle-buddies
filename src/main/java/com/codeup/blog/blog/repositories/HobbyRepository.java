package com.codeup.blog.blog.repositories;

import com.codeup.blog.blog.models.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {

}
