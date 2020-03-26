package score;

import domain.Name;
import org.junit.Assert;
import org.junit.Test;

public class NameExtractorImplTest {
    @Test
    public void getName_Test(){
        NameExtractor<Name, char[]> nameNameExtractor = new NameExtractorImpl();
        Name mitesh = Name.builder().setFirstName("Mitesh").build();
        char[] nameCharArray = nameNameExtractor.extractName(mitesh);

        Assert.assertArrayEquals(nameCharArray, mitesh.getFirstName().toCharArray());
    }

    @Test
    public void shoule_return_empty_array_if_name_is_null(){
        NameExtractor<Name, char[]> nameNameExtractor = new NameExtractorImpl();
        char[] nameCharArray = nameNameExtractor.extractName(null);
        Assert.assertArrayEquals(nameCharArray, new char[]{});
    }
}