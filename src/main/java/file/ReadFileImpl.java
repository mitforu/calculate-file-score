package file;


import domain.Name;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFileImpl implements ReadFile<File, List<Name>> {
    Logger logger = Logger.getLogger(ReadFileImpl.class);
    @Override
    public List<Name> readFileAsNames(File file) {
        String names = "";
        try {
            Scanner myReader = new Scanner(file);
            if(myReader.hasNextLine()) {
                names =  myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            logger.error("Can not find file.");
            e.printStackTrace();
        }

        return getNames(names);
    }

    public List<Name> getNames(String names){
        return Stream.of(names.split(","))
                .map(it -> it.replace("\"", ""))
                .filter(it -> it.length() > 0)
                .map(Name::new)
                .collect(Collectors.toList());
    }
}
