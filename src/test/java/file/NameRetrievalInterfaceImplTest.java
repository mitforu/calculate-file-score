package file;

import domain.Name;
import org.junit.Assert;
import org.junit.Test;
import domain.Source;

import java.io.File;
import java.util.List;

public class NameRetrievalInterfaceImplTest {

    @Test
    public void test_readFile(){
        String path = "src/test/resources/file.txt";
        File file = new File(path);
        ReadFileImpl test = new ReadFileImpl();

        Source<File> source = new Source<>(file);

        List<Name> names = test.retrieveNames(source);

        Assert.assertEquals(names.get(0).getFirstName(),"MARY");
        Assert.assertEquals(names.get(1).getFirstName(), "PATRICIA");
        Assert.assertEquals(names.get(2).getFirstName(), "LINDA");

    }
}