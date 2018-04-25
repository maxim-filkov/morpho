package org.lexikos.service;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import org.lexikos.component.MystemPosDetector;
import org.lexikos.component.PhpMorphyPosDetector;
import org.lexikos.constant.Language;
import org.lexikos.constant.PartOfSpeech;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PosDetectorService {

   @Value("${service.mystem}")
   private String mystemFileName;

   @Resource
   private PhpMorphyPosDetector phpMorphyPosDetector;

   @Resource
   private MystemPosDetector mystemPosDetector;

   public Map<PartOfSpeech, Long> detectPartOfSpeech(String phrase1, String phrase2,
      Language phrase1Language, Language phrase2Language) {

      List<PartOfSpeech> posList = new ArrayList<>();

      posList.addAll(phpMorphyPosDetector.detectPartOfSpeech(phrase1, phrase1Language));
      posList.addAll(phpMorphyPosDetector.detectPartOfSpeech(phrase2, phrase2Language));

      if (phrase1Language.equals(Language.RUSSIAN)) {
         posList.addAll(mystemPosDetector.detectPartOfSpeech(phrase1));
      } else if (phrase2Language.equals(Language.RUSSIAN)) {
         posList.addAll(mystemPosDetector.detectPartOfSpeech(phrase2));
      }

      return posList.stream()
         .collect(groupingBy(identity(), counting()));
   }

}
