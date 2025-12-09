package com.learn.karan.prod_ready_features.prod_ready_features.services;

import com.learn.karan.prod_ready_features.prod_ready_features.dto.PostDTO;
import com.learn.karan.prod_ready_features.prod_ready_features.entities.PostEntity;
import com.learn.karan.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import com.learn.karan.prod_ready_features.prod_ready_features.repositories.PostEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class PostServiceImplement implements PostService{

    private final PostEntityRepository postEntityRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {
        return postEntityRepository.findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        PostEntity postEntity =modelMapper.map(inputPost,PostEntity.class);
        return modelMapper.map(postEntityRepository.save(postEntity),PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity=postEntityRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post Not found with the id"+postId));
        return modelMapper.map(postEntity,PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO inputPost, Long id) {
        PostEntity olderPost=postEntityRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post Not found with the id"+id));
        inputPost.setId(id);
        modelMapper.map(inputPost,olderPost);
        PostEntity savedPostEntity=postEntityRepository.save(olderPost);
        return modelMapper.map(savedPostEntity,PostDTO.class);
    }
}
