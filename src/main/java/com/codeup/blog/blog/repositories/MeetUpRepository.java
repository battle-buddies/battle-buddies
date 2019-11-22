package com.codeup.blog.blog.repositories;

import com.codeup.blog.blog.models.MeetUp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetUpRepository extends JpaRepository<MeetUp, Long> {
}
