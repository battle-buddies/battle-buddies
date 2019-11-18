package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.models.Post;
import com.codeup.blog.blog.models.Tag;
import com.codeup.blog.blog.models.User;
import com.codeup.blog.blog.repositories.PostRepository;
import com.codeup.blog.blog.repositories.TagRepository;
import com.codeup.blog.blog.repositories.UserRepository;
import com.codeup.blog.blog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

        private PostRepository postDao;
        private UserRepository userDao;
        private TagRepository tagDao;
        private EmailService emailService;


    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService, TagRepository tagDao ){
    this.postDao = postDao;
    this.userDao = userDao;
    this.emailService = emailService;
    this.tagDao = tagDao;
    }

//    VIEWING POSTS

    @GetMapping("/posts")
    public String showIndex(Model vModel){

        vModel.addAttribute("posts", postDao.findAll());

        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String showIndividual(@PathVariable long id, Model vModel){

        vModel.addAttribute("post", postDao.getOne(id));


        return "/posts/show";
    }


//    DELETE POSTS

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){

        postDao.deleteById(id);

        return "redirect:/posts";
    }


//    EDIT POSTS

    @GetMapping("/posts/{id}/edit")
    public String beginEdit(@PathVariable long id, Model vModel, @ModelAttribute String missing){

        vModel.addAttribute("post", postDao.getOne(id));

        return "/posts/edit" ;
    }

    @PostMapping ("/posts/{id}/edit")
    public String editPost(@PathVariable long id, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body, Model vModel){

        if (title.isEmpty() || body.isEmpty()) {
            return "redirect:/posts/" +id + "/edit";
        }else {
            postDao.editPost(title, body, id);
            return "redirect:/posts/" + id;
        }
    }


//    CREATE POSTS

    @GetMapping("/posts/create")
    public String view(Model vModel, @ModelAttribute String missing, @ModelAttribute ArrayList<Tag> tagsErr) {
        vModel.addAttribute("post", new Post());

        if (missing != null){
            vModel.addAttribute("missing", missing);
            vModel.addAttribute("tagsErr", tagsErr);
        }

        vModel.addAttribute("tags", tagDao.findAll());

        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String create(@ModelAttribute Post post, Model vModel, @RequestParam(value = "ids", required = false) ArrayList<Long> ids){


        if (post.getTitle().isEmpty() || post.getBody().isEmpty() || ids == null){
            vModel.addAttribute("tagsErr", tagDao.findAll());
            vModel.addAttribute("missing", "Please fill out all forms");
            return "/posts/create";
        }else{
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            ArrayList<Tag> tagsToAdd = new ArrayList<>();
            List<Tag> allTags = tagDao.findAll();
            for (Long id : ids) {
                for (Tag tag: allTags) {
                    if(tag.getId() == id)
                        tagsToAdd.add(tag);
                }

            }

            post.setUser(userDao.getOne(user.getId()));

            if (tagsToAdd != null){
                post.setTags(new ArrayList<>());
                for (Tag tag: tagsToAdd) {
                    post.getTags().add(tag);
                }
            }

            Post savedPost = postDao.save(post);
            emailService.prepareAndSend(savedPost, "New Post", "Congrats, your post has been created.");
            return "redirect:/posts/" +  savedPost.getId();
        }
    }


//    TAGS

    @GetMapping("/posts/tags/{id}")
    public String searchTag(@PathVariable long id, Model vModel){

        List<Post> posts = new ArrayList<>();

        for (Tag tag: tagDao.findAll() ) {
            if (tag.getId() == id){
                vModel.addAttribute("tag", tag);
                posts.addAll(tag.getPosts());
            }
        }
        vModel.addAttribute("posts", posts);
        return "/posts/tag";
    }

}

