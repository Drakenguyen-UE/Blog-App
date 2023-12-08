INSERT INTO post (id, title          , description             , content                                  , created_at       , updated_at       )
VALUES           (1 , "Java Advanced", "Learn Spring Framework", "Learn Spring Framework with mentor Khoa", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO comment (id, name             , email              , body                  , created_at       , updated_at       , post_id)
VALUES              (1 , "Nguyễn Văn Khoa", "khoa.nv@gmail.com", "Bài viết rất bổ ích.", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1      );