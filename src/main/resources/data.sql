INSERT INTO post (id, title          , description             , content                                  , created_at       , updated_at       )
VALUES           (1 , "Database"     , "Learn MySQL"           , "Learn MySQL with mentor Khoa"           , CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                 (2 , "Java Core"    , "Learn Java Core"       , "Learn Java Core with mentor Khoa"       , CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                 (3 , "Front Basic"  , "Learn HTML, CSS, JS"   , "Learn HTML, CSS, JS with mentor Khoa"   , CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                 (4 , "Java Advanced", "Learn Spring Framework", "Learn Spring Framework with mentor Khoa", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                 (5 , "React JS"     , "Learn React JS"        , "Learn React JS with mentor Khoa"        , CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO comment (id, name              , email               , body                  , created_at       , updated_at       , post_id)
VALUES              (1 , "Nguyễn Văn Khoa" , "khoa.nv@gmail.com" , "Bài viết rất bổ ích.", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1      ),
                    (2 , "Nguyễn Thu Trang", "trang.nt@gmail.com", "Bài viết rất tệ."    , CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2      ),
                    (3 , "Lữ Văn Chiến"    , "chien.lv@gmail.com", "Giải thích rõ ràng." , CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4      ),
                    (4 , "Trần Cẩm Thu"    , "thu.tc@gmail.com"  , "Ngắn gọn, xúc tích." , CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4      ),
                    (5 , "Ngô Bá Khá"      , "kha.nb@gmail.com"  , "Rườm rà, khó hiểu."  , CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2      );