public class Controller {
    public static void main(String[] args) {
        State first = new RtState();
        State second = new RtState();
        State third = new RtState();
        State fourth = new RtState();
        State fifth = new RtState();
        State sixth = new RtState();
        State seventh = new RtState();
        State eighth = new RtState(true);

        first.with(new RtTransition("{", second));
        second.with(new RtTransition("\"", third));
        //Add transitions with chars 0-9 and a-z
        for (int i = 0; i < 26; i++) {
            if (i < 10) {
                third = third.with(new RtTransition(String.valueOf(i), third));
                sixth = sixth.with(new RtTransition(String.valueOf(i), sixth));
            }
            third = third.with(new RtTransition(String.valueOf((char) ('a' + i)), third));
            sixth = sixth.with(new RtTransition(String.valueOf((char) ('a' + i)), sixth));
        }
        third.with(new RtTransition("\"", fourth));
        fourth.with(new RtTransition(":", fifth));
        fifth.with(new RtTransition("\"", sixth));
        sixth.with(new RtTransition("\"", seventh));
        seventh.with(new RtTransition(",", second));
        seventh.with(new RtTransition("}", eighth));

        String json = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
        FiniteStateMachine machine = new RtFiniteStateMachine(first);
        for (int i = 0; i < json.length(); i++) {
            machine = machine.switchState(String.valueOf(json.charAt(i)));
        }

        System.out.println(machine.canStop());
    }
}
