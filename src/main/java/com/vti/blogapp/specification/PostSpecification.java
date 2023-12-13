package com.vti.blogapp.specification;

import com.vti.blogapp.entity.Post;
import com.vti.blogapp.form.PostFilterForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class PostSpecification { // Nghĩa là đây là class triển khai việc lọc dữ liệu cho entity Post
    public static Specification<Post> buildSpec(PostFilterForm form) { // Phương thức build ra mệnh đề WHERE trong truy vấn
        return (root, query, builder) -> { // = 'new Specification<Post>()' trước khi dùng lambda
            var predicates = new ArrayList<Predicate>(); // Predicate có thể hiểu như 1 điều kiện
                // Trong mệnh đề WHERE có nhiều điều kiện, và mỗi điều kiện chính là phần tử trong ArrayList ở trên

            var search = form.getSearch(); // đây là giá trị 'search' người dùng truyền lên
            if (StringUtils.hasText(search)) { // dùng thư viện này sẽ kiếm tra biến 'search' xem có chữ hay ko, nếu người dùng truyền lên toàn dấu cách cũng sẽ trả về false, nếu trong đó có chữ mới hợp lệ, hàm này sẽ mạnh mẽ hơn vì có thể người dùng truyền lên ko có gì cả
                var pattern = "%" + search.trim() + "%"; // Toán tử like trong mySQL sẽ có LIKE '%java%'; ở VD này người dùng sẽ truyền vào giá trị java là biến 'search' ở trên, còn mình sẽ bọc % trước và sau giá trị cần trị kiếm
                var predicate = builder.like(root.get("title"), pattern);
                           // = (FROM post)    WHERE   title LIKE '%java%'(đây là pattern)
                predicates.add(predicate); // Thêm vào list điều kiện ở trên
            }

            var minCreatedDate = form.getMinCreatedDate();
            if (minCreatedDate != null) {
                // created_at >= '2020-09-10 00:00:00' vì kiểu dữ liệu của minCreatedDate là localdate != localdatetime nên phải convert cho giống
                var minCreatedAt = LocalDateTime.of(minCreatedDate, LocalTime.MIN); // truyền vào 2 kiểu dữ liệu Date và Time
                var predicate = builder.greaterThanOrEqualTo(root.get("createdAt"), minCreatedAt);
                           // = (FROM post)    WHERE                   created_at >= 'YYYY-MM-DD 00:00:00'
                predicates.add(predicate);
            }

            var maxCreatedDate = form.getMaxCreatedDate();
            if (maxCreatedDate != null) {
                var maxCreatedAt = LocalDateTime.of(maxCreatedDate, LocalTime.MAX);
                var predicate = builder.lessThanOrEqualTo(root.get("createdAt"), maxCreatedAt);
                           // = (FROM post)    WHERE                created_at <= 'YYYY-MM-DD 23:59:59.999999999'
                predicates.add(predicate);
            }

            return builder.and(predicates.toArray(new Predicate[0])); // chuyển hoá 1 list của java 8 collection về 1 kiểu array
                    // kết nối các điều kiện với nhau = mệnh đề 'and'. VD: tìm điện thoại màu đỏ 'và' giá trên 10tr 'và' là mẫu mới nhất
        };
    }
}
