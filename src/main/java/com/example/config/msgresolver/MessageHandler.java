package com.example.config.msgresolver;

import com.example.exception.util.ErrorEnumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageHandler {

   private static ReloadableResourceBundleMessageSource messageSource;

   @Autowired
   MessageHandler(ReloadableResourceBundleMessageSource messageSource) {
      MessageHandler.messageSource = messageSource;
   }

   public static String toLocale(ErrorEnumeration message) {
      Locale locale = LocaleContextHolder.getLocale();
      return messageSource.getMessage(message.getMessageKey(), null, locale);
   }
}