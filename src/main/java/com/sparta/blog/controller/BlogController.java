package com.sparta.blog.controller;

import com.sparta.blog.dto.BlogRequestDto;
import com.sparta.blog.dto.BlogResponseDto;
import com.sparta.blog.service.BlogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/blogs")
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto requestDto) {
        return blogService.createBlog(requestDto);
    }

    @GetMapping("/blogs")
    public List<BlogResponseDto> getBlogs() {
        return blogService.getBlogs();
    }

    @GetMapping("/blogs/{id}")
    public BlogResponseDto readBlog(@PathVariable Long id) {
        return blogService.readBlog(id);
    }

    @PutMapping("/blogs/{id}")
    public BlogResponseDto updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        return blogService.updateBlog(id, requestDto);
    }

    @DeleteMapping("/blogs/{id}")
    public String deleteBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        return blogService.deleteBlog(id, requestDto);
    }
}