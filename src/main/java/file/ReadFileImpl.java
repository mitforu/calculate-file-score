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

    /**
     * Filtering the name which are empty
     * Also assumption is taken that names in the file will be c
     * omma separated and will have alphabet only
     */
    public List<Name> getNames(String names){
        return Stream.of(names.split(","))
                .map(it -> it.replace("\"", ""))
                .filter(it -> it.length() > 0)
                .map(it -> Name.builder().setFirstName(it).build())
                .collect(Collectors.toList());
    }
}
