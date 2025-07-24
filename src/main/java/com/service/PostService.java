package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dto.PostDTO;
import com.model.Post;
import com.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public List<PostDTO> listPosts(){
        return postRepository.findAll().stream().map(PostDTO::new).toList();
    }

    public Optional<Post> findById(Long id){
        return postRepository.findById(id);
    }

    public PostDTO savePost(PostDTO postDTO){
        Post post = new Post();
        post.setAuthorUid(postDTO.authorUid());
        post.setImageUrl(postDTO.imageUrl());
        post.setCaption(postDTO.caption());
        post.setFavorited(postDTO.favorited());
        Post saved = postRepository.save(post);
        return new PostDTO(saved);
    }

    public PostDTO updatePost(Long id, PostDTO postDTO){
        Optional<Post> optionalPost = findById(id);
        if(optionalPost.isEmpty()){
            return null;
        }
        Post post = optionalPost.get();
        post.setImageUrl(postDTO.imageUrl());
        post.setCaption(postDTO.caption());
        post.setFavorited(postDTO.favorited());
        Post updated = postRepository.save(post);
        return new PostDTO(updated);
    }

    public boolean deletePost(Long id){
        if(postRepository.existsById(id)){
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
