package score;


import domain.Name;

/**
 *  Bases on the requirement change different implementation of NameExtractor can be created
 *  to get first and last name both or bases on business scenario
 */
public class NameExtractorImpl implements NameExtractor<Name, char[]> {
    @Override
    public char[] extractName(Name name){
        return name.getFirstName().toCharArray();
    }
}
