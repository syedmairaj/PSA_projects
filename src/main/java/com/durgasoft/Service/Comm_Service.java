package com.durgasoft.Service;

import com.durgasoft.Entity.Comments;
import com.durgasoft.Entity.Post;
import com.durgasoft.Exception.TestNotFound;
import com.durgasoft.Repository.CommentsRepository;
import com.durgasoft.Repository.Ent_empRepository;
import com.durgasoft.Repository.PostRepository;
import com.durgasoft.payload.CommentDto;
import com.durgasoft.payload.PostDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.html.HTMLTableCellElement;

import java.util.List;
import java.util.Optional;

@Service
public class Comm_Service {



    @Autowired
    private ModelMapper mapper;

    private PostRepository postRepository;
    private CommentsRepository commentsRepository;

    public Comm_Service(PostRepository postRepository, CommentsRepository commentsRepository) {
        this.postRepository = postRepository;
        this.commentsRepository = commentsRepository;
    }




    //deleting a post



//creating comments
    public ResponseEntity<?> createComment(CommentDto commentDto, long postId) {
        Comments commentconv = DtoToEntity_C(commentDto);
        Post post =   postRepository.findById(postId).get();
             commentconv.setPost(post);
             Comments commsaved = commentsRepository.save(commentconv);
             CommentDto dtoconv = EntityToDTO_C(commsaved);
             return new ResponseEntity<>(dtoconv,HttpStatus.CREATED);


    }

//create post
    public  ResponseEntity<?> createPost(PostDto postDto) {
        Post post = DtoToEntity_P(postDto);
        Post savepost = postRepository.save(post);
        PostDto dtopost = EntityToDTO_P(savepost);
        return new ResponseEntity<>(dtopost, HttpStatus.OK);
    }


    //EntityToDto --posts
    public PostDto EntityToDTO_P(Post post){
        return mapper.map(post, PostDto.class);
    }

    public Post DtoToEntity_P(PostDto postDto){
        return mapper.map(postDto,Post.class);
    }

    //DtoToEntity --comments
    public CommentDto EntityToDTO_C(Comments comments){
        return mapper.map(comments, CommentDto.class);
    }

    public Comments DtoToEntity_C(CommentDto commentdto){
        return mapper.map(commentdto,Comments.class);
    }


    public ResponseEntity<?> deletePost(Long id) {
        Optional<Post> postfind = postRepository.findById(id);
        if(postfind.isPresent()){
              postRepository.deleteById(id);
        } else {
            throw new TestNotFound("ID:"+id+ "is not found");
        }

        return new ResponseEntity<>(id+"ID is deleted",HttpStatus.OK);
    }


//    public ResponseEntity<?> getComments(Long id, Post post) {
//        List<Comments> postid = post.getComments();
//
//        Optional<Comments> findcomm = commentsRepository.findById(post.getId());
//        if(findcomm.isPresent()){
//            commentsRepository.getById(id);
//    }else {
//            throw new TestNotFound("ID:" + id +"is not found");
//        }
//        return new ResponseEntity<>(findcomm,HttpStatus.OK);
//}

    public ResponseEntity<?> getCommentsByPostId(Long id) {
    Optional<Post> findPost = postRepository.findById(id);
    if(findPost.isPresent()){
        Post post = findPost.get();
        List<Comments> commentsList = post.getComments();
        return new ResponseEntity<>(commentsList,HttpStatus.OK);
    }else {
        throw new TestNotFound("ID:" +id+ " not found");
    }

    }

}
