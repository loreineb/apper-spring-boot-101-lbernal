package com.apper.theblogservice;

import com.apper.theblogservice.exceptions.DoesNotExist;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.payload.*;
import com.apper.theblogservice.service.BlogService;
import com.apper.theblogservice.service.BloggerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("blog")
public class BlogApi {

    private final BlogService blogService;
    private final BloggerService bloggerService;

    public BlogApi(BlogService blogService, BloggerService bloggerService) {
        this.blogService = blogService;
        this.bloggerService = bloggerService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBlogResponse createBlog(@RequestBody @Valid CreateBlogRequest request) {
        System.out.println(request);

        boolean idExists = checkBloggerIdPresence(request.getBloggerId());

        if (!idExists) {
            System.out.println("Account is not yet registered and cannot be found. Blog post cannot be made.");
            throw new DoesNotExist();
        }

        Blog createdBlog = blogService.createBlog(request.getBloggerId(), request.getTitle(), request.getBody());

        CreateBlogResponse response = new CreateBlogResponse();
        response.setId(createdBlog.getBlogId());
        response.setBloggerId(createdBlog.getBloggerId());
        response.setCreatedAt(createdBlog.getCreatedAt());
        response.setLastUpdate(createdBlog.getLastUpdate());

        return response;
    }

    @PutMapping("{id}") //this id is the blog id
    public CreateBlogResponse updateBlog(@RequestBody @Valid UpdateBlogRequest request, @PathVariable String id) {

        System.out.println(request);

        boolean idExists = checkBlogIdPresence(id);

        if (!idExists) {
            System.out.println("The blog you are attempting to update does not exist.");
            throw new DoesNotExist();
        }

        Blog updatedBlog = blogService.updateBlog(id, request.getTitle(), request.getBody());

        CreateBlogResponse response = new CreateBlogResponse();

        response.setId(updatedBlog.getBlogId());
        response.setBloggerId(updatedBlog.getBloggerId());
        response.setCreatedAt(updatedBlog.getCreatedAt());
        response.setLastUpdate(updatedBlog.getLastUpdate());

        return(response);
    }


    @GetMapping("{id}") //this id is the blog id
    public BlogDetails getBlogger(@PathVariable String id) {
        boolean idExists = checkBlogIdPresence(id);

        if (!idExists) {
            System.out.println("The blog you are attempting to get does not exist.");
            throw new DoesNotExist();
        }

        Blog blog = blogService.getBlog(id);

        BlogDetails blogDetails = new BlogDetails();
        blogDetails.setBloggerId(blog.getBloggerId());
        blogDetails.setTitle(blog.getTitle());
        blogDetails.setBody(blog.getBody());
        blogDetails.setCreatedAt(blog.getCreatedAt());
        blogDetails.setLastUpdate(blog.getLastUpdate());

        return blogDetails;
    }

    @GetMapping
    public List<BlogDetails> getAllBlogsResponse() {
        Iterable<Blog> blogs = blogService.getAllBlogs();

        boolean isEmpty = !blogs.iterator().hasNext();

        if (isEmpty) {
            System.out.println("No blogs to get.");
            throw new DoesNotExist();
        }

        List<BlogDetails> allBlogs = new ArrayList<>();
        for (Blog blog : blogs) {
            BlogDetails blogDetails = new BlogDetails();
            blogDetails.setBloggerId(blog.getBloggerId());
            blogDetails.setTitle(blog.getTitle());
            blogDetails.setBody(blog.getBody());
            blogDetails.setCreatedAt(blog.getCreatedAt());
            blogDetails.setLastUpdate(blog.getLastUpdate());

            allBlogs.add(blogDetails);
        }
        return allBlogs;
    }

    @GetMapping("blogger/{id}") //this id is the BLOGGER id
    public List<GetBlogsOfUserResponse> getAllBlogsOfUser(@PathVariable String id) {

        List<Blog> blogs = blogService.getAllBlogsOfUser(id);
        boolean isEmpty = !blogs.iterator().hasNext();

        boolean idExists = checkBloggerIdPresence(id);
        if (!idExists || isEmpty) {
            System.out.println("Account and/or blogs do not exist. Blogs cannot be retrieved.");
            throw new DoesNotExist();
        }

        List<GetBlogsOfUserResponse> allBlogs = new ArrayList<>();
        for (Blog blog : blogs) {
            GetBlogsOfUserResponse gBOUR = new GetBlogsOfUserResponse();
            gBOUR.setTitle(blog.getTitle());
            gBOUR.setBody(blog.getBody());
            gBOUR.setCreatedAt(blog.getCreatedAt());
            gBOUR.setLastUpdate(blog.getLastUpdate());

            allBlogs.add(gBOUR);
        }
        return allBlogs;
    }

    @DeleteMapping("{id}") //this id is the blog id
    public void deleteBlogResponse(@PathVariable String id) {
        boolean idExists = false;

        Iterable<Blog> blogs = blogService.getAllBlogs();
        for (Blog blog : blogs) {
            if (blog.getBlogId().equals(id)) {
                idExists = true;
            }
        }

        if (!idExists) {
            System.out.println("Account is not yet registered and cannot be found. Blog post cannot be deleted.");
            throw new DoesNotExist();
        }
        blogService.deleteBlog(id);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllBlogsResponse() {
        Iterable<Blog> blogs = blogService.getAllBlogs();

        boolean isEmpty = !blogs.iterator().hasNext();

        if (isEmpty) {
            System.out.println("No blogs to get.");
            throw new DoesNotExist();
        }
        blogService.deleteAllBlogs();
    }

    @DeleteMapping("blogger/{id}") //this id is the BLOGGER id
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllBlogsOfUser(@PathVariable String id) {

        List<Blog> blogs = blogService.getAllBlogsOfUser(id);
        boolean isEmpty = !blogs.iterator().hasNext();

        boolean idExists = checkBloggerIdPresence(id);
        if (!idExists || isEmpty) {
            System.out.println("Account and/or blogs do not exist. Blogs cannot be deleted.");
            throw new DoesNotExist();
        }
        blogService.deleteAllBlogsOfUser(id);

    }

    public boolean checkBlogIdPresence (String id) {

        Iterable<Blog> blogs = blogService.getAllBlogs();
        for (Blog blog : blogs) {
            if (blog.getBlogId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBloggerIdPresence (String id) {

        Iterable<Blogger> bloggers = bloggerService.getAllBloggers();
        for (Blogger blogger : bloggers) {
            if (blogger.getBloggerId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
