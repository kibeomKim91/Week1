package com.sparta.blog.service;

import com.sparta.blog.dto.BlogRequestDto;
import com.sparta.blog.dto.BlogResponseDto;
import com.sparta.blog.entity.Blog;
import com.sparta.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public BlogResponseDto createBlog(BlogRequestDto requestDto) {
        // RequestDto -> Entity
        Blog blog = new Blog(requestDto);

        // DB 저장
        Blog saveBlog = blogRepository.save(blog);

        // Entity -> ResponseDto
        BlogResponseDto blogResponseDto = new BlogResponseDto(saveBlog);

        return blogResponseDto;
    }

    public List<BlogResponseDto> getBlogs() {
        return blogRepository.findAllByOrderByModifiedAtDesc().stream().map(BlogResponseDto::new).toList();
    }

    public BlogResponseDto readBlog(Long id) {

        Blog blog = findBlog(id);

        BlogResponseDto blogResponseDto = new BlogResponseDto(blog);

        return blogResponseDto;
    }

    @Transactional
    public BlogResponseDto updateBlog(Long id, BlogRequestDto requestDto) {

        Blog blog = findBlog(id);

        if (blog.getPw().equals(requestDto.getPw())) {
            blog.update(requestDto);
            BlogResponseDto blogResponseDto = new BlogResponseDto(blog);
            return blogResponseDto;
        } else {
            throw new IllegalArgumentException("선택한 게시물은 존재하지 않습니다");
        }
    }

    public String deleteBlog(Long id, BlogRequestDto requestDto) {

        Blog blog = findBlog(id);

        if (blog.getPw().equals(requestDto.getPw())) {
            blogRepository.delete(blog);
            return "게시글이 성공적으로 삭제되었습니다.";
        } else {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다. 비밀번호를 다시 확인해 주세요.");
            return "비밀번호가 일치하지 않습니다. 비밀번호를 다시 확인해 주세요.";
        }
    }

    private Blog findBlog(Long id) {
        return blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시물은 존재하지 않습니다")
        );
    }
}