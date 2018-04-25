package org.lexikos;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lexikos.component.PhpMorphyPosDetector;
import org.lexikos.constant.Language;
import org.lexikos.constant.PartOfSpeech;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = MorphoApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class PhpMorphyPosDetectionTest {

   @Mock
   private RestTemplate phpmorphyMock;

   @InjectMocks
   private PhpMorphyPosDetector phpMorphyPosDetector;

   @Before
   public void initMocks() {
      MockitoAnnotations.initMocks(this);
      when(phpmorphyMock.getForObject(anyString(), any(Class.class)))
         .thenReturn(asList("noun", "verb"));

      ReflectionTestUtils.setField(phpMorphyPosDetector, "morphoUrl", "");
   }

   @Test
   public void testPartOfSpeechIsDetectedCorrectly() {
      List<PartOfSpeech> parts = phpMorphyPosDetector.detectPartOfSpeech("test", Language.ENGLISH);
      assertThat(parts, containsInAnyOrder(PartOfSpeech.NOUN, PartOfSpeech.VERB));
   }

   //   @Test
   //   public void testUnknownPartOfSpeechIsDetectedCorrectly() {
   //      List<PartOfSpeech> parts = phpMorphyPosDetector.detectPartOfSpeech("unknown", Language.ENGLISH);
   //      assertTrue(parts.isEmpty());
   //   }
   //
   //   @Test
   //   public void testBrokenUrlToPhpMorphy() {
   //      ReflectionTestUtils.setField(phpMorphyPosDetector, "morphoUrl", "phrase={0}&language={1}");
   //      List<PartOfSpeech> actualPartOfSpeechSet = phpMorphyPosDetector.detectPartOfSpeech("kjkkkoekroewrkowekoekd", Language.ENGLISH);
   //      Assert.assertEquals(emptySet(), actualPartOfSpeechSet);
   //   }

}
