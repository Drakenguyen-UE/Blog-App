package com.vti.blogapp.repository;

import com.vti.blogapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostRepository extends
        JpaRepository<Post, Long>
        , JpaSpecificationExecutor<Post> {
    boolean existsByTitle(String title); // trả về đúng hoặc sai nên dùng boolean
}       // Interface JpaSpecificationExecutor cho phép mình tạo ra chức năng filter (lọc dữ liệu)
        // Với spring data jpa thì khi kế thừa đã có sẵn những phương thức như là findById, findAll, delete...