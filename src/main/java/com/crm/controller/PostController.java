package com.crm.controller;

import com.crm.entity.Post;
import com.crm.repository.CommentRepository;
import com.crm.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
// TODO: Implement endpoints for creating, reading, updating, and deleting posts.
public class PostController {
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public PostController(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @PostMapping
    public String createPost(
            @RequestBody Post post
    ){
        postRepository.save(post);
        return null;
    }

    @DeleteMapping
    public void deletePost(){
        postRepository.deleteById(1L);
    }


}
