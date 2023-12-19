package com.vti.blogapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.Locale;

@Configuration // @ này đánh dấu 1 file cấu hình
public class I18nConfiguration {
    @Bean // để public cho sping thấy dc là mình đang có 1 thằng xử lý khu vực custom của riêng mình
    public LocaleResolver localeResolver() { // logic này dùng để chỉnh ngộn ngữ ở phần headers trong postman (dùng accept-language)
        var resolver = new AcceptHeaderLocaleResolver(); // AcceptHeader để chấp nhận language trên headers
        var locales = Arrays.asList(Locale.ENGLISH, new Locale("vi")); // do tiếng Việt ko có sẵn nên phải tự nhập vào new Locale
        resolver.setSupportedLocales(locales); // SupportedLocales là khu vực được hỗ trợ
        resolver.setDefaultLocale(Locale.ENGLISH); // Nếu ko truyền gì thì ngôn ngữ mặc định là tiếng Anh
        return resolver;
    }
} // chuột phải new -> resource bundle -> đặt đúng tên -> add locale -> thêm en,vi -> ok
// chọn Setting -> editor -> file encodings -> chọn UTF-8 ở default encoding for properties files -> ok
