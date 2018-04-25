package org.lexikos.component;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.split;

import org.lexikos.constant.PartOfSpeech;
import org.lexikos.pojo.MystemJsonPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
public class MystemPosDetector {

   @Resource
   private Mystem mystem;

   @Value("${service.mystem}")
   private String mystemFileName;

   public List<PartOfSpeech> detectPartOfSpeech(String phrase) {
      return mystemJsonToPos(phrase, mystem.getForObject(phrase));
   }

   private List<PartOfSpeech> mystemJsonToPos(String phrase, MystemJsonPojo[] mystemJson) {
      return Stream.of(mystemJson)
         .findFirst()
         .map(MystemJsonPojo::getAnalysis)
         .orElse(emptyList())
         .stream()
         .filter(a -> a.getLex().equals(phrase))
         .map(a -> extractPos(a.getGr()))
         .map(this::mystemToPos)
         .filter(Objects::nonNull)
         .collect(toList());
   }

   private PartOfSpeech mystemToPos(String string) {
      switch (string) {
      case "A":
         return PartOfSpeech.ADJECTIVE;
      case "ADV":
      case "ADVPRO":
         return PartOfSpeech.ADVERB;
      case "ANUM":
      case "NUM":
         return PartOfSpeech.NUMERAL;
      case "APRO":
         return PartOfSpeech.PRONOUN;
      case "CONJ":
         return PartOfSpeech.CONJUNCTION;
      case "INTJ":
         return PartOfSpeech.INTERJECTION;
      case "PART":
         return PartOfSpeech.PARTICLE;
      case "PR":
         return PartOfSpeech.PREPOSITION;
      case "S":
      case "SPRO":
         return PartOfSpeech.NOUN;
      case "V":
         return PartOfSpeech.VERB;
      default:
         return null;
      }
   }

   private String extractPos(String string) {
      return Stream.of(split(string, "="))
         .findFirst()
         .map(s -> Stream.of(split(s, ",")))
         .orElse(Stream.empty())
         .findFirst()
         .orElse(null);
   }

}
