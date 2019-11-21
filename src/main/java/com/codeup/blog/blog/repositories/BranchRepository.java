package com.codeup.blog.blog.repositories;

import com.codeup.blog.blog.models.Branch;
import com.codeup.blog.blog.models.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {

}
