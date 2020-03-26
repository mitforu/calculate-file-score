package score;

public interface ReadFileAndScore<T, R> {
    R scoreFile(T file);
}
