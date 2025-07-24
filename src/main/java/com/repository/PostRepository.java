package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{}
