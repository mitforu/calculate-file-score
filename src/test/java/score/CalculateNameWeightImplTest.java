package score;

import domain.Name;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class CalculateNameWeightImplTest {
    CalculateNameWeight<Name, Integer> calculateNameWeightImpl;
    NameExtractor<Name, char[]> extractor;

    @Before
    public void setUp(){
        extractor = mock(NameExtractorImpl.class);
        calculateNameWeightImpl = new CalculateNameWeightImpl(extractor);
    }

    @Test
    public void testCalculateWeight(){
        Name name = Name.builder().setFirstName("MARRY").build();
        when(extractor.extractName(any())).thenReturn(name.getFirstName().toCharArray());
        Integer weight = calculateNameWeightImpl.calculateWeight(name);
        verify(extractor, times(1)).extractName(name);
        assertEquals(Optional.of(weight), Optional.of(75));
    }

    @Test
    public void should_ignore_invalid_characters(){
        Name name = Name.builder().setFirstName("MARRY").build();
        when(extractor.extractName(any())).thenReturn(null);
        Integer weight = calculateNameWeightImpl.calculateWeight(name);
        assertEquals(Optional.of(weight), Optional.of(0));
    }
}