package org.lexikos;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.lexikos.component.MystemPosDetector;
import org.lexikos.constant.PartOfSpeech;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.TestPropertySource;

import java.util.Collection;
import java.util.List;

@RunWith(value = Parameterized.class)
@ContextConfiguration(classes = MorphoApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class MystemAllPosDetectionTest {

   @Parameterized.Parameter
   public String givenPhrase;

   @Parameterized.Parameter(value = 1)
   public List<PartOfSpeech> expectedPartOfSpeech;

   @Autowired
   MystemPosDetector mystemPosDetector;

   private TestContextManager testContextManager;

   @Parameterized.Parameters(name = "Test part of speech for {0} is {1}")
   public static Collection<Object[]> data() {
      return asList(new Object[][] {
         { "пост", asList(PartOfSpeech.NOUN) },
         { "хороший", asList(PartOfSpeech.ADJECTIVE) },
         { "тестировать", asList(PartOfSpeech.VERB) },
         { "десять", asList(PartOfSpeech.NUMERAL) },
         { "мой", asList(PartOfSpeech.PRONOUN) },
         { "недавно", asList(PartOfSpeech.ADVERB) },
         { "на", asList(PartOfSpeech.PREPOSITION, PartOfSpeech.PARTICLE) },
         { "ой", asList(PartOfSpeech.INTERJECTION) },
         { "же", asList(PartOfSpeech.PARTICLE, PartOfSpeech.CONJUNCTION) },
         { "блаблаблабла", emptyList() }
      });
   }

   @Before
   public void setUpContext() throws Exception {
      testContextManager = new TestContextManager(getClass());
      testContextManager.prepareTestInstance(this);
   }

   @Test
   public void testPartOfSpeechIsDetectedCorrectly() {
      List<PartOfSpeech> actualPartOfSpeechSet = mystemPosDetector.detectPartOfSpeech(givenPhrase);
      Assert.assertEquals(expectedPartOfSpeech, actualPartOfSpeechSet);
   }

}
