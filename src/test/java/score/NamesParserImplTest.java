package score;

import domain.Name;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class NamesParserImplTest {

    NamesParser<List<Name>, Long> namesParser;
    CalculateNameWeight calculateNameWeight;
    @Before
    public void setUp(){
        calculateNameWeight = mock(CalculateNameWeightImpl.class);
        namesParser = new NamesParserImpl(calculateNameWeight);
    }

    @Test
    public void test_score(){
        Name mary =  Name.builder().setFirstName("MARY").build();
        Name patricia =  Name.builder().setFirstName("PATRICIA").build();
        Name linda =  Name.builder().setFirstName("LINDA").build();

        when(calculateNameWeight.calculateWeight(mary)).thenReturn(5);
        when(calculateNameWeight.calculateWeight(patricia)).thenReturn(10);
        when(calculateNameWeight.calculateWeight(linda)).thenReturn(15);

        List<Name> names = Arrays.asList(
                mary,
                patricia,
                linda
        );

        Long score = namesParser.score(names);

        InOrder inOrder = inOrder(calculateNameWeight);
        inOrder.verify(calculateNameWeight).calculateWeight(linda);
        inOrder.verify(calculateNameWeight).calculateWeight(mary);
        inOrder.verify(calculateNameWeight).calculateWeight(patricia);

        Assert.assertEquals(Optional.of(score), Optional.of(55L));
    }
}