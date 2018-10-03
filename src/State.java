import java.util.ArrayList;
import java.util.List;

final class State {

    private List<Transition> transitions;
    private boolean isFinal;

    List<Transition> GetTransitions() {
        return this.transitions;
    }

    State() {
        this(false);
    }

    State(final boolean isFinal) {
        this.transitions = new ArrayList<>();
        this.isFinal = isFinal;
    }

    State transit(final CharSequence c) {
        return transitions
                .stream()
                .filter(t -> t.isPossible(c))
                .map(Transition::state)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Input not accepted: " + c));
    }

    boolean isFinal() {
        return this.isFinal;
    }

    State with(Transition tr) {
        this.transitions.add(tr);
        return this;
    }
}