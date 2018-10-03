final class FiniteStateMachine {

    private State current;

    FiniteStateMachine(final State initial) {
        this.current = initial;
    }

    FiniteStateMachine switchState(final CharSequence c) {
        return new FiniteStateMachine(this.current.transit(c));
    }

    boolean canStop() {
        return this.current.isFinal();
    }

    State GetCurrentState() {
        return this.current;
    }
}