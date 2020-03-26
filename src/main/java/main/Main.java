package main;

import domain.Name;
import file.ReadFile;
import file.ReadFileImpl;
import score.*;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String args[]) throws Exception {

        /**
         * Assuming first argument will be the path of the file/
         */
        File file;
        if(args.length > 0){
            file = new File(args[0]);
        }else {
            throw new Exception("Please specify the file path..");
        }

        NameExtractor<Name, char[]> nameExtractor = new NameExtractorImpl();
        CalculateNameWeight<Name, Integer> weight = new CalculateNameWeightImpl(nameExtractor);

        /**
         * Name Parse is the algorithm which glues together below.
         * 1. How to extract name, currently first name but can have different implementation
         * of interface NameExtractor to get first and last name.
         * 2. Formula to calculate weight of individual name.
         */
        NamesParser<List<Name>, Long> nameParser = new NamesParserImpl(weight);

        ReadFile<File, List<Name>> readFile = new ReadFileImpl();
        ReadFileAndScore<File, Long> readFileAndScore = new ReadFileAndScoreImpl(readFile, nameParser);
        Long score = readFileAndScore.scoreFile(file);

        System.out.println(score);
    }
}