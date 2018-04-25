package org.lexikos;

import static java.util.Collections.emptyList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lexikos.component.MystemPosDetector;
import org.lexikos.constant.PartOfSpeech;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MorphoApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class MystemPosDetectionNegativeTest {

   @Autowired
   MystemPosDetector mystemPosDetector;

   @Test
   public void testMystemNotAvailable() {
      ReflectionTestUtils.setField(mystemPosDetector, "mystemFileName", "unknownFile");
      List<PartOfSpeech> actualPartOfSpeechSet = mystemPosDetector.detectPartOfSpeech("dontcare");
      Assert.assertEquals(emptyList(), actualPartOfSpeechSet);
   }

}
