package org.lexikos.rest;

import org.hibernate.validator.constraints.NotEmpty;
import org.lexikos.constant.Language;
import org.lexikos.constant.PartOfSpeech;
import org.lexikos.service.PosDetectorService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Size;
import java.util.Map;

@RestController
public class PosDetectorResource {

   @Resource
   private PosDetectorService posDetectorService;

   @RequestMapping(path = "/v0/pos/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public Map<PartOfSpeech, Long> detect(
      @RequestParam @NotEmpty @Size(max = 255) String phrase1,
      @RequestParam @NotEmpty @Size(max = 255) String phrase2,
      @RequestParam @NotEmpty Language phrase1Language,
      @RequestParam @NotEmpty Language phrase2Language
   ) {
      return posDetectorService.detectPartOfSpeech(phrase1, phrase2, phrase1Language, phrase2Language);
   }

}
