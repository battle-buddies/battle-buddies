package com.codeup.blog.blog.repositories;

import com.codeup.blog.blog.models.Profile;

import com.codeup.blog.blog.models.Relationship;
import com.codeup.blog.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelationshipRepository extends JpaRepository<Relationship, Long > {
    List<Relationship> findAllByStatus(String status);

    List<Relationship> findByFriend(User friend);
}
