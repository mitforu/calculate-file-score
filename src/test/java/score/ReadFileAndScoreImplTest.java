package score;

import domain.Name;
import file.ReadFile;
import file.ReadFileImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ReadFileAndScoreImplTest {
    ReadFile<File, List<Name>> readFile;
    NamesParser<List<Name>, Long> namesParser;
    ReadFileAndScore<File, Long> readFileAndScore;

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
        readFileAndScore.scoreFile(file);
        verify(readFile).readFileAsNames(file);
    }

    @Test
    public void should_call_score_with_return_value_of_readFile(){
        List<Name> names = Arrays.asList(new Name("Mitesh"));
        when(readFile.readFileAsNames(any())).thenReturn(names);
        File file = new File("c://test.text");
        readFileAndScore.scoreFile(file);
        verify(namesParser).score(names);
    }

    @Test
    public void should_return_score_from_the_name_parser(){
        List<Name> names = Arrays.asList(new Name("Mitesh"));
        when(readFile.readFileAsNames(any())).thenReturn(names);
        when(namesParser.score(any())).thenReturn(10L);

        File file = new File("c://test.text");
        Long aLong = readFileAndScore.scoreFile(file);
        Assert.assertEquals(
                Optional.of(aLong), Optional.of(10L)
        );
    }
}