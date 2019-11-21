package com.codeup.blog.blog.repositories;

import com.codeup.blog.blog.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long > {

}
