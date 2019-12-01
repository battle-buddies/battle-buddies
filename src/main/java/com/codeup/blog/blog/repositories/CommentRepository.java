package com.codeup.blog.blog.repositories;


import com.codeup.blog.blog.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
