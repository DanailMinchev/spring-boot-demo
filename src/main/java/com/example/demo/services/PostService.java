package com.example.demo.services;

import com.example.demo.clients.jsonplaceholder.JSONPlaceHolderClient;
import com.example.demo.clients.jsonplaceholder.dtos.PostCommentResponseDto;
import com.example.demo.domain.jpa.PostCommentEntity;
import com.example.demo.domain.jpa.PostEntity;
import com.example.demo.mappers.PostMapper;
import com.example.demo.models.PostModel;
import com.example.demo.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final JSONPlaceHolderClient jsonPlaceHolderClient;
    private final PostMapper postMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void loadPosts() {
        log.info("com.example.demo.services.PostService.loadPosts called");

        log.info("Deleting all posts ...");
        postRepository.deleteAll();

        log.info("Querying posts ...");
        jsonPlaceHolderClient.getAllPosts().forEach(postResponseDto -> {
            log.info("postResponseDto: {}", postResponseDto);

            log.info("Mapping postResponseDto ...");
            PostEntity postEntity = postMapper.postResponseDtoToPostEntity(postResponseDto);
            log.info("postEntity: {}", postEntity);

            populatePostComments(postEntity, postResponseDto.getId());

            log.info("Saving postEntity ...");
            PostEntity savedPostEntity = postRepository.save(postEntity);
            log.info("savedPostEntity: {}", savedPostEntity);
        });
    }

    public boolean existsById(Long id) {
        return postRepository.existsById(id);
    }

    public List<PostModel> findAll(Pageable pageable) {
        Pageable pageRequest = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.DESC, "id"))
        );

        Page<PostEntity> postModelPage = postRepository.findAll(pageRequest);

        return postModelPage
                .getContent()
                .stream()
                .map(postMapper::postEntityToPostModel)
                .toList();
    }

    public Optional<PostModel> findById(Long id) {
        Optional<PostEntity> postEntityOptional = postRepository.findById(id);
        if (postEntityOptional.isPresent()) {
            PostEntity postEntity = postEntityOptional.get();
            return Optional.of(postMapper.postEntityToPostModel(postEntity));
        } else {
            return Optional.empty();
        }
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    private void populatePostComments(PostEntity postEntity, Long postId) {
        List<PostCommentResponseDto> postCommentResponseDtos =
                jsonPlaceHolderClient.getAllPostCommentsByPostId(postId);
        log.info("postCommentResponseDtos: {}", postCommentResponseDtos);

        log.info("Mapping postCommentResponseDtos ...");
        if (Objects.isNull(postEntity.getPostComments())) {
            postEntity.setPostComments(new ArrayList<>());
        }
        postCommentResponseDtos.forEach(postCommentResponseDto -> {
            PostCommentEntity postCommentEntity =
                    postMapper.postCommentResponseDtoToPostCommentEntity(postCommentResponseDto);
            postCommentEntity.setPost(postEntity);
            log.info("postCommentEntity: {}", postCommentEntity);
            postEntity.getPostComments().add(postCommentEntity);
        });
    }

}
