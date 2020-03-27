package score;


import domain.Name;
import file.NameRetrievalInterface;
import domain.Source;

import java.util.List;

public class ReadFileAndScoreImpl implements ReadFileAndScore<Source<?>, Long> {

    NameRetrievalInterface<Source<?>, List<Name>> readFile;
    NamesParser<List<Name>, Long> namesParser;

    /**
     * NameRetrievalInterface is a interface based and implementation of that reads a file.
     * In future we can have different implementation based on business case
     */
    public ReadFileAndScoreImpl(NameRetrievalInterface<Source<?>, List<Name>> readFile, NamesParser namesParser){
        this.readFile = readFile;
        this.namesParser = namesParser;
    }

    @Override
    public Long scoreFile(Source<?> file) {
        List<Name> strings = readFile.retrieveNames(file);
        return namesParser.score(strings);
    }
}
