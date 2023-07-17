package com.apper.theblogservice.repository;

import com.apper.theblogservice.model.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends CrudRepository<Blog, String> {
    List<Blog> findAllByBloggerId(String bloggerId);
}
