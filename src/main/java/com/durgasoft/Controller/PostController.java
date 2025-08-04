package com.durgasoft.Controller;


import com.durgasoft.Repository.CommentsRepository;
import com.durgasoft.Repository.PostRepository;
import com.durgasoft.Service.Comm_Service;
import com.durgasoft.payload.PostDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vi/api/posts")
public class PostController {

    @Autowired
    private ModelMapper mapper;

   private Comm_Service commService;
   private PostRepository postRepository;
    private CommentsRepository commentsRepository;

    public PostController(Comm_Service commService, PostRepository postRepository, CommentsRepository commentsRepository) {
        this.commService = commService;
        this.postRepository = postRepository;
        this.commentsRepository = commentsRepository;
    }
//    private PostRepository postRepository;
//    private CommentsRepository commentsRepository;
//    public PostController(PostRepository postRepository, CommentsRepository commentsRepository) {
//        this.postRepository = postRepository;
//        this.commentsRepository = commentsRepository;
//    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostDto postDto){
        return commService.createPost(postDto);
    }

@DeleteMapping("{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id){
        return commService.deletePost(id);
}
// when you give post id it will get the comments associated with this post id

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentsbyPostid(@PathVariable ("id") Long id){
        return commService.getCommentsByPostId(id);
    }

}
