package com.example.demo.repositories;

import com.example.demo.domain.jpa.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findByTitleLikeIgnoreCase(String title);

}
