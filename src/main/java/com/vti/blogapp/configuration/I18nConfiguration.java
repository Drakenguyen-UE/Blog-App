package com.vti.blogapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.Locale;

@Configuration // @ này đánh dấu 1 file cấu hình
public class I18nConfiguration {
    @Bean
    public LocaleResolver localeResolver() {
        var resolver = new AcceptHeaderLocaleResolver();
        var locales = Arrays.asList(Locale.ENGLISH, new Locale("vi")); // do tiếng Việt ko có sẵn nên phải tự nhập vào new Locale
        resolver.setSupportedLocales(locales); // SupportedLocales là khu vực được hỗ trợ
        resolver.setDefaultLocale(Locale.ENGLISH); // Nếu ko truyền gì thì ngôn ngữ mặc định là tiếng Anh
        return resolver;
    }
}
