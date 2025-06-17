package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Need;

@Repository
public interface NeedRepository extends JpaRepository<Need, Long>{}
