package score;

public interface NameExtractor<T, R> {
    R extractName(T name);
}
