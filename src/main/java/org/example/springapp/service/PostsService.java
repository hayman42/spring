package org.example.springapp.service;

import lombok.RequiredArgsConstructor;
import org.example.springapp.domain.posts.Posts;
import org.example.springapp.domain.posts.PostsRepository;
import org.example.springapp.web.dto.PostsResponseDto;
import org.example.springapp.web.dto.PostsSaveRequestDto;
import org.example.springapp.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("No such posts. id=" + id));
        return posts.update(requestDto.getTitle(), requestDto.getContent());
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("No such posts. id=" + id));
        return new PostsResponseDto(entity);
    }
}
