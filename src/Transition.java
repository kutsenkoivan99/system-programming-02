final class Transition {

    private String rule;
    private State next;

    Transition(String rule, State next) {
        this.rule = rule;
        this.next = next;
    }

    State state() {
        return this.next;
    }

    boolean isPossible(CharSequence c) {
        return this.rule.equalsIgnoreCase(String.valueOf(c));
    }

}