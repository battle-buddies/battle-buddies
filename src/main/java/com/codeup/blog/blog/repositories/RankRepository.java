package com.codeup.blog.blog.repositories;

import com.codeup.blog.blog.models.Hobby;
import com.codeup.blog.blog.models.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankRepository extends JpaRepository<Rank, Long> {

}
