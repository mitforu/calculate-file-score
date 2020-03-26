package score;

import domain.Name;
import org.junit.Assert;
import org.junit.Test;

public class NameExtractorImplTest {
    @Test
    public void getName_Test(){
        NameExtractor<Name, char[]> nameNameExtractor = new NameExtractorImpl();
        Name mitesh = new Name("Mitesh");
        char[] nameCharArray = nameNameExtractor.extractName(mitesh);

        Assert.assertArrayEquals(nameCharArray, mitesh.getFirstName().toCharArray());

    }
}