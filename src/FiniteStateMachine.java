public interface FiniteStateMachine {
    FiniteStateMachine switchState(final CharSequence c);

    boolean canStop();
}