package com.example.demo.mappers;

import com.example.demo.clients.jsonplaceholder.dtos.PostCommentResponseDto;
import com.example.demo.clients.jsonplaceholder.dtos.PostResponseDto;
import com.example.demo.domain.jpa.PostCommentEntity;
import com.example.demo.domain.jpa.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "postComments", ignore = true)
    PostEntity postResponseDtoToPostEntity(PostResponseDto source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "post", ignore = true)
    PostCommentEntity postCommentResponseDtoToPostCommentEntity(PostCommentResponseDto source);

}
