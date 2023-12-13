package com.vti.blogapp.specification;

import com.vti.blogapp.entity.Comment;
import com.vti.blogapp.form.CommentFilterForm;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class CommentSpecification { // class để chuyển đổi FilterForm thành Specification vì đầu vào là FilterForm nhưng thư viện cần Specification
    public static Specification<Comment> buildSpec(CommentFilterForm form) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            var postId = form.getPostId();
            if (postId != null) {
                var predicate = builder.equal(root.get("post").get("id"), postId); // từ bảng root(comment) truyền vào post, từ post truyền vào id sẽ lấy dc id của bảng Post sau đó so sánh equal với giá trị postId lấy từ cái form ra
                           // = (FROM comment) WHERE post_id = "1"
                predicates.add(predicate);
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
