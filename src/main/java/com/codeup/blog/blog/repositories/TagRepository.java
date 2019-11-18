package com.codeup.blog.blog.repositories;

import com.codeup.blog.blog.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface TagRepository extends JpaRepository<Tag, Long> {


}
