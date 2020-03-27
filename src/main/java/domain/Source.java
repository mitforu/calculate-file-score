package domain;

public class Source<T> {
    private T t;

    public Source(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) { this.t = t; }
}
