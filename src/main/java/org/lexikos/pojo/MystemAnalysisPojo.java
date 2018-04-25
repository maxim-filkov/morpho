package org.lexikos.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MystemAnalysisPojo {

   @JsonIgnore
   private final Map<String, Object> additionalProperties = new HashMap<>();

   @JsonProperty("lex")
   private String lex;

   @JsonProperty("gr")
   private String gr;

}