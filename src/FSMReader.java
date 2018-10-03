import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

class FSMReader {
    private static File _chooseFile() {
        JFileChooser fileChooser =
                new JFileChooser("D:\\Users\\kutsenkoivan99\\Documents\\Education\\5 семестр\\Системне програмування\\Lab2");
        int dialogResult = fileChooser.showDialog(null, "Open File");
        File file;
        if (dialogResult == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        } else {
            file = null;
        }
        return file;
    }

    private static String _getFilePath() {
        return _chooseFile().getPath();
    }

    static FiniteStateMachine GetFSMFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(_getFilePath()))) {
            br.readLine();

            State[] states = new State[Integer.parseInt(br.readLine().trim())];

            int firstIndex = Integer.parseInt(br.readLine().trim());

            String[] finalIndexes = br.readLine().substring(2).split("\\s+");
            for (int i = 0; i < states.length; i++) {
                if (Arrays.asList(finalIndexes).contains(Integer.toString(i))) {
                    states[i] = new State(true);
                } else {
                    states[i] = new State();
                }
            }

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                states[Integer.parseInt(sCurrentLine.split("\\s+")[0])]
                        .with(new Transition(
                                sCurrentLine.split("\\s+")[1],
                                states[Integer.parseInt(sCurrentLine.split("\\s+")[2])]
                        ));
            }

            return new FiniteStateMachine(states[firstIndex]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}