package score;

import domain.Name;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NamesParserImpl implements NamesParser<List<Name>, Long> {

    CalculateNameWeight<Name, Integer> calculateNameWeightInterface;

    public NamesParserImpl(
            CalculateNameWeight calculateNameWeightInterface
    ){
        this.calculateNameWeightInterface = calculateNameWeightInterface;
    }

    @Override
    public Long score(List<Name> names) {
        Collections.sort(names, Comparator.comparing(Name::getFirstName));
        Long score = 0L;

        for (int i = 0 ; i < names.size(); i++){
            Integer weight = calculateNameWeightInterface.calculateWeight(names.get(i));
            score = score + weight * (i + 1);
        }

        return score;
    }
}
