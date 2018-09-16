public final class RtFiniteStateMachine implements FiniteStateMachine {

    private State current;

    public RtFiniteStateMachine(final State initial) {
        this.current = initial;
    }

    public FiniteStateMachine switchState(final CharSequence c) {
        return new RtFiniteStateMachine(this.current.transit(c));
    }

    public boolean canStop() {
        return this.current.isFinal();
    }

}