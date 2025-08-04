package com.durgasoft.Controller;

import com.durgasoft.Entity.Comments;
import com.durgasoft.Entity.Post;
import com.durgasoft.Repository.CommentsRepository;
import com.durgasoft.Repository.PostRepository;
import com.durgasoft.Service.Comm_Service;
import com.durgasoft.payload.CommentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vi/api/comment")
public class CommentController {

    private Comm_Service commService;
    private PostRepository postRepository;
    private CommentsRepository commentsRepository;

    public CommentController(Comm_Service commService, PostRepository postRepository, CommentsRepository commentsRepository) {
        this.commService = commService;
        this.postRepository = postRepository;
        this.commentsRepository = commentsRepository;
    }

    @PostMapping("/add")

    public ResponseEntity<?> createComment(@RequestBody CommentDto commentDto, long postId){
        return commService.createComment(commentDto, postId);

    }

//    @GetMapping("{id}")
//    public ResponseEntity<?> getComments(@PathVariable Long id, Post post ){
//       return commService.getComments(id,post);
//    }
}
