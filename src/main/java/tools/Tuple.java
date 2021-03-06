package tools;

/**
 * Created by gvoiron on 19/05/19.
 * Time : 16:59
 */
public final class Tuple<T1, T2> {

    private final T1 first;
    private final T2 second;

    public Tuple(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Tuple && getFirst().equals(((Tuple) obj).getFirst()) && getSecond().equals(((Tuple) obj).getSecond());
    }

    @Override
    public String toString() {
        return "<" + getFirst() + ", " + getSecond() + ">";
    }

}
