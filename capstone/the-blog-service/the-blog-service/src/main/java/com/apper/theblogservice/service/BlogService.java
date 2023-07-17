package com.apper.theblogservice.service;

import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.repository.BlogRepository;
import com.apper.theblogservice.repository.BloggerRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Blog createBlog(String bloggerId, String title, String body) {
        Blog blog = new Blog();
        blog.setBloggerId(bloggerId);
        blog.setTitle(title);
        blog.setBody(body);

        return blogRepository.save(blog);
    }

    public Blog getBlog(String id) {
        Optional<Blog> blogResult = blogRepository.findById(id);
        return blogResult.get();
    }

    public Blog updateBlog(String id, String title, String body) {
        Blog blog = getBlog(id);
        blog.setTitle(title);
        blog.setBody(body);

        return blogRepository.save(blog);
    }

    public Iterable<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public List<Blog> getAllBlogsOfUser(String bloggerId) {
        return blogRepository.findAllByBloggerId(bloggerId);
    }

    public void deleteBlog(String id) {blogRepository.deleteById(id);}

    public void deleteAllBlogs() {blogRepository.deleteAll();}

    public void deleteAllBlogsOfUser(String bloggerId) {
        List <Blog> blogsOfUser = blogRepository.findAllByBloggerId(bloggerId);
        for (Blog blog: blogsOfUser) {
            blogRepository.delete(blog);
        }
    }
}