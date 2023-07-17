package com.apper.theblogservice;

import com.apper.theblogservice.exceptions.DoesNotExist;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.payload.*;
import com.apper.theblogservice.service.BloggerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.apper.theblogservice.exceptions.EmailAlreadyRegistered;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("blogger")
public class BloggerApi {

    private final BloggerService bloggerService;

    public BloggerApi(BloggerService bloggerService) {
        this.bloggerService = bloggerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBloggerResponse createBlogger(@RequestBody @Valid CreateBloggerRequest request) throws EmailAlreadyRegistered{
        System.out.println(request);

        Iterable<Blogger> bloggers = bloggerService.getAllBloggers();
        for (Blogger blogger : bloggers) {
            if (blogger.getEmail().equals(request.getEmail())) {
                System.out.println("Email is already registered.");
                throw new EmailAlreadyRegistered();
            }
        }

        Blogger createdBlogger = bloggerService.createBlogger(request.getEmail(), request.getName(), request.getPassword());

        CreateBloggerResponse response = new CreateBloggerResponse();
        response.setId(createdBlogger.getBloggerId());
        response.setDateRegistration(createdBlogger.getCreatedAt());

        return response;
    }

    @GetMapping("{id}")
    public BloggerDetails getBlogger(@PathVariable String id) {

        boolean idExists = false;

        Iterable<Blogger> bloggers = bloggerService.getAllBloggers();
        for (Blogger blogger : bloggers) {
            if (blogger.getBloggerId().equals(id)) {
                //I initially did if (!blogger.getBloggerId().equals(id) PERO I didn't realize na that means na once di maging equal, exception na agad, without going through everything
                idExists = true;
                break;
            }
        }

        if (!idExists) {
            System.out.println("The blogger you're attempting to get is not yet registered and cannot be found.");
            throw new DoesNotExist();
        }

        Blogger blogger = bloggerService.getBlogger(id);

        BloggerDetails bloggerDetails = new BloggerDetails();
        bloggerDetails.setId(blogger.getBloggerId());
        bloggerDetails.setName(blogger.getName());
        bloggerDetails.setEmail(blogger.getEmail());
        bloggerDetails.setDateRegistration(blogger.getCreatedAt());

        return bloggerDetails;
    }

    @GetMapping
    public Iterable<Blogger> getAllBloggersResponse() {
        Iterable<Blogger> bloggers = bloggerService.getAllBloggers();
        boolean isEmpty = !bloggers.iterator().hasNext();

        if (isEmpty) {
            System.out.println("No bloggers to get.");
            throw new DoesNotExist();
        }

        return bloggerService.getAllBloggers();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBloggerResponse(@PathVariable String id) {
        boolean idExists = false;

        Iterable<Blogger> bloggers = bloggerService.getAllBloggers();
        for (Blogger blogger : bloggers) {
            if (blogger.getBloggerId().equals(id)) {
                idExists = true;
            }
        }

        if (!idExists) {
            System.out.println("Account is not yet registered and cannot be found. Blog post cannot be made.");
            throw new DoesNotExist();
        }
        bloggerService.deleteBlogger(id);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllBloggersResponse() {
        Iterable<Blogger> bloggers = bloggerService.getAllBloggers();
        boolean isEmpty = !bloggers.iterator().hasNext();

        if (isEmpty) {
            System.out.println("No bloggers to get.");
            throw new DoesNotExist();
        }
        bloggerService.deleteAllBloggers();
    }

}