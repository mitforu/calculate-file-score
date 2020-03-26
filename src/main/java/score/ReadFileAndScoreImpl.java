package score;


import domain.Name;
import file.ReadFile;

import java.io.File;
import java.util.List;

public class ReadFileAndScoreImpl implements ReadFileAndScore<File, Long> {

    ReadFile<File, List<Name>> readFile;
    NamesParser<List<Name>, Long> namesParser;

    /**
     * ReadFile is a interface based and implementation of that reads a file.
     * In future we can have different implementation based on business case
     */
    public ReadFileAndScoreImpl(ReadFile readFile, NamesParser namesParser){
        this.readFile = readFile;
        this.namesParser = namesParser;
    }

    @Override
    public Long scoreFile(File file) {
        List<Name> strings = readFile.readFileAsNames(file);
        return namesParser.score(strings);
    }
}
