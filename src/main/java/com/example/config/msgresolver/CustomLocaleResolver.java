package com.example.config.msgresolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Configuration
public class CustomLocaleResolver extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {
   private static final Logger logger = LoggerFactory.getLogger(CustomLocaleResolver.class);

   List<Locale> LOCALES = Arrays.asList(
         new Locale("en"),
         new Locale("fr"));

   @Override
   public Locale resolveLocale(HttpServletRequest request) {
      String headerLang = request.getHeader("Accept-Language");
      return headerLang == null || headerLang.isEmpty()
            ? Locale.getDefault()
            : Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES);
   }

   @Bean
   public ReloadableResourceBundleMessageSource messageSource() {
      ReloadableResourceBundleMessageSource messageSourceBundle = new ReloadableResourceBundleMessageSource();
      messageSourceBundle.setBasename("classpath:messages");
      messageSourceBundle.setDefaultEncoding("UTF-8");
      messageSourceBundle.setUseCodeAsDefaultMessage(true);
      return messageSourceBundle;
   }

}