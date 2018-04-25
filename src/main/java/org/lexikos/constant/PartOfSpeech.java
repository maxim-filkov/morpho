/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lexikos.constant;

import static java.util.Arrays.stream;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class contains all supported parts of speech.
 *
 * @author Maksim Filkov
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PartOfSpeech {

   /**
    * Noun.
    */
   NOUN("noun"),

   /**
    * Adjective.
    */
   ADJECTIVE("adjective"),

   /**
    * Verb.
    */
   VERB("verb"),

   /**
    * Participle.
    */
   PARTICIPLE("participle"),

   /**
    * Numeral.
    */
   NUMERAL("numeral"),

   /**
    * Pronoun.
    */
   PRONOUN("pronoun"),

   /**
    * Adverb.
    */
   ADVERB("adverb"),

   /**
    * Preposition.
    */
   PREPOSITION("preposition"),

   /**
    * Interjection.
    */
   INTERJECTION("interjection"),

   /**
    * Particle.
    */
   PARTICLE("particle"),

   /**
    * Conjunction.
    */
   CONJUNCTION("conjunction");

   @JsonProperty("pos")
   private final String value;

   public static PartOfSpeech fromString(String value) {
      return stream(PartOfSpeech.values()).filter(lang -> lang.value.equals(value)).findFirst().orElse(null);
   }

   @Override
   public String toString() {
      return value;
   }

}
