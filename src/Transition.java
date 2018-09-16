public interface Transition {
    boolean isPossible(final CharSequence c);

    State state();
}