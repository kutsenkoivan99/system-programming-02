public class Controller {
    public static void main(String[] args) {
        FiniteStateMachine machine = FSMReader.GetFSMFromFile();

        String json = "abd";
        try {

            for (int i = 0; i < json.length(); i++) {
                machine = machine.switchState(String.valueOf(json.charAt(i)));
            }
            System.out.println(machine.canStop());
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }
}
