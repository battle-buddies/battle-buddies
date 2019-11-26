package com.codeup.blog.blog.repositories;

import com.codeup.blog.blog.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository  extends JpaRepository<Photo,Long> {
}
