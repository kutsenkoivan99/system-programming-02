public interface State {
    State with(final Transition tr);

    State transit(final CharSequence c);

    boolean isFinal();
}