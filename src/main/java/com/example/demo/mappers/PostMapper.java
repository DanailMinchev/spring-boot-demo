package com.example.demo.mappers;

import com.example.demo.domain.jpa.PostEntity;
import com.example.demo.dtos.PostDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDto postEntityToPostDto(PostEntity source);

    PostEntity postDtoToPostEntity(PostDto source);

}
