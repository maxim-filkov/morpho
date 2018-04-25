package org.lexikos.constant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Language {

   ENGLISH("en"),
   RUSSIAN("ru");

   @JsonProperty("language")
   public final String value;

   @Override
   public String toString() {
      return value;
   }

}
