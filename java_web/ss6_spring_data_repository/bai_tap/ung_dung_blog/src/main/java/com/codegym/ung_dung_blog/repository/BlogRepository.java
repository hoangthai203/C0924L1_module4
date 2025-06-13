package com.codegym.ung_dung_blog.repository;

import com.codegym.ung_dung_blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    // Tìm blog theo category
    Page<Blog> findByCategoryId(Long categoryId, Pageable pageable);

    // Hoặc bạn có thể sử dụng @Query cho linh hoạt hơn
    @Query("SELECT b FROM Blog b WHERE " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(b.content) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Blog> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}