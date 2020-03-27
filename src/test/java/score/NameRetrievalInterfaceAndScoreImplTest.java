package score;

import domain.Name;
import file.NameRetrievalInterface;
import file.ReadFileImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import domain.Source;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class NameRetrievalInterfaceAndScoreImplTest {
    NameRetrievalInterface<Source<?>, List<Name>> readFile;
    NamesParser<List<Name>, Long> namesParser;
    ReadFileAndScore<Source<?>, Long> readFileAndScore;

    @Before
    public void setUp(){

        readFile = mock(ReadFileImpl.class);
        namesParser = mock(NamesParserImpl.class);
        readFileAndScore = new ReadFileAndScoreImpl(
                readFile,
                namesParser
        );
    }

    @Test
    public void should_call_read_file(){
        File file = new File("c://test.text");
        Source<File> source = new Source<>(file);
        readFileAndScore.scoreFile(source);
        verify(readFile).retrieveNames(source);
    }

    @Test
    public void should_call_score_with_return_value_of_readFile(){
        List<Name> names = Arrays.asList(Name.builder().setFirstName("Mitesh").build());
        when(readFile.retrieveNames(any())).thenReturn(names);
        File file = new File("c://test.text");
        Source<File> source = new Source<>(file);
        readFileAndScore.scoreFile(source);
        verify(namesParser).score(names);
    }

    @Test
    public void should_return_score_from_the_name_parser(){
        List<Name> names = Arrays.asList(Name.builder().setFirstName("Mitesh").build());
        when(readFile.retrieveNames(any())).thenReturn(names);
        when(namesParser.score(any())).thenReturn(10L);

        File file = new File("c://test.text");
        Source<File> source = new Source<>(file);
        Long aLong = readFileAndScore.scoreFile(source);
        Assert.assertEquals(
                Optional.of(aLong), Optional.of(10L)
        );
    }
}