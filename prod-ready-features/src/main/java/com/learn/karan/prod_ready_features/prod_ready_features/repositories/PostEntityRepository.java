package com.learn.karan.prod_ready_features.prod_ready_features.repositories;

import com.learn.karan.prod_ready_features.prod_ready_features.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostEntityRepository extends JpaRepository<PostEntity, Long> {
}