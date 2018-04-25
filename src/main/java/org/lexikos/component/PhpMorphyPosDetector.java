package org.lexikos.component;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import org.lexikos.constant.Language;
import org.lexikos.constant.PartOfSpeech;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

@Component
public class PhpMorphyPosDetector {

   @Value("${service.phpmorphy.url}")
   private String morphoUrl;

   @Resource
   private RestTemplate phpMorphy;

   public List<PartOfSpeech> detectPartOfSpeech(String phrase, Language language) {
      String url = MessageFormat.format(morphoUrl, phrase, toPhpMorphyLanguage(language.value));
      try {
         return phpmorphyToPos(phpMorphy.getForObject(url, List.class));
      } catch (Exception e) {
         return emptyList();
      }
   }

   private List<PartOfSpeech> phpmorphyToPos(List<String> phpMorphyPosList) {
      return phpMorphyPosList.stream()
         .map(PartOfSpeech::fromString)
         .filter(Objects::nonNull)
         .collect(toList());
   }

   private String toPhpMorphyLanguage(String language) {
      return language + "_" + language.toUpperCase();
   }

}
