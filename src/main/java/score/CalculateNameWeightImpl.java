package score;

import domain.Name;

public class CalculateNameWeightImpl implements CalculateNameWeight<Name, Integer> {

    NameExtractor<Name, char[]> extractor;

    public CalculateNameWeightImpl(NameExtractor extractor){
        this.extractor = extractor;
    }

    @Override
    public Integer calculateWeight(Name name){
        char[] nameChar = extractor.extractName(name);
        int individualNameScore = 0;
        for (char c : nameChar){
            individualNameScore  = individualNameScore + calculateCharacterWeight(c);
        }
        return individualNameScore;
    }

    /**
     *  Ignoring case of the character and considering upper case always.
     */
    private int calculateCharacterWeight(char c){
        int numericValue = Character.getNumericValue(Character.toUpperCase(c));
        return numericValue - 9;
    }
}
