package score;

import domain.Name;
import java.util.logging.Logger;
import java.util.logging.Level;

public class CalculateNameWeightImpl implements CalculateNameWeight<Name, Integer> {
    private final static Logger LOGGER = Logger.getLogger(CalculateNameWeightImpl.class.getName());
    NameExtractor<Name, char[]> extractor;

    public CalculateNameWeightImpl(NameExtractor extractor){
        this.extractor = extractor;
    }

    /**
     *  Log and Ignore a name when there a exception processing a
     */
    @Override
    public Integer calculateWeight(Name name){
        char[] nameChar = extractor.extractName(name);
        int individualNameScore = 0;
        try{
            for (char c : nameChar){
                individualNameScore  = individualNameScore + calculateCharacterWeight(c);
            }
            return individualNameScore;
        }catch (Exception e){
            LOGGER.log(Level.WARNING, "Ignoring Name : "+ name, e);
        }
        return 0;
    }

    /**
     *  Ignoring case of the character and considering upper case always.
     */
    private int calculateCharacterWeight(char c){
        int numericValue = Character.getNumericValue(Character.toUpperCase(c));
        return numericValue - 9;
    }
}
