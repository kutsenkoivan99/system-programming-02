import java.util.ArrayList;
import java.util.Scanner;


public class Controller {
    public static void main(String[] args) {
        FiniteStateMachine machine = FSMReader.GetFSMFromFile();

        Scanner in = new Scanner(System.in);

        String json = in.nextLine();
        boolean result = false;
        try {
            ArrayList<State> reachable = new ArrayList<>();
            int previousCount = 0;
            reachable.add(machine.GetCurrentState());
            result = isResult(machine, json, result);

            while (previousCount != reachable.size()) {
                int num = previousCount;
                previousCount = reachable.size();
                for (int i = num; i < previousCount; i++) {
                    if (reachable.get(i).GetTransitions().size() != 0) {
                        for (int j = 0; j < reachable.get(i).GetTransitions().size(); j++) {
                            State state = reachable.get(i).GetTransitions().get(j).state();
                            if (!reachable.contains(state)) {
                                reachable.add(state);
                                machine = new FiniteStateMachine(state);
                                result = isResult(machine, json, result);
                            }
                        }
                    }
                }
            }

            System.out.println(result);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    private static boolean isResult(FiniteStateMachine machine, String json, boolean result) {
        try {
            for (int k = 0; k < json.length(); k++) {
                machine = machine.switchState(String.valueOf(json.charAt(k)));
            }
            if (machine.canStop()) {
                result = true;
            }
        } catch (IllegalArgumentException ignored) {
        }
        return result;
    }
}
