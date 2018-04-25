package org.lexikos.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lexikos.pojo.MystemJsonPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

@Component
public class Mystem {

   @Value("${service.mystem}")
   private String mystemFileName;

   public MystemJsonPojo[] getForObject(String stdin) {
      try {
         Runtime runtime = Runtime.getRuntime();
         File file = new File(getClass().getResource(mystemFileName).getFile());
         Process mystem = runtime.exec(file.getAbsolutePath() + " --eng-gr -wgil --format json");
         OutputStream stdinStream = mystem.getOutputStream();
         InputStream stdoutStream = mystem.getInputStream();
         BufferedReader reader = new BufferedReader(new InputStreamReader(stdoutStream));
         BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdinStream));

         writer.write(stdin);
         writer.close();
         String json = reader.readLine();
         reader.close();

         return new ObjectMapper().readValue(json, MystemJsonPojo[].class);
      } catch (Exception e) {
         return new MystemJsonPojo[] {};
      }
   }

}
