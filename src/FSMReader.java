import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class FSMReader {
    private static File _chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
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

    public static FiniteStateMachine GetFSMFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(_getFilePath()))) {
            br.readLine();

            State[] states = new State[Integer.parseInt(br.readLine().trim())];

            int firstIndex = Integer.parseInt(br.readLine().trim());

            String[] finalIndexes = br.readLine().substring(2).split("\\s+");
            for (int i = 0; i < states.length; i++) {
                if (Arrays.asList(finalIndexes).contains(Integer.toString(i))) {
                    states[i] = new RtState(true);
                } else {
                    states[i] = new RtState();
                }
            }

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                states[Integer.parseInt(sCurrentLine.split("\\s+")[0])]
                        .with(new RtTransition(
                                sCurrentLine.split("\\s+")[1],
                                states[Integer.parseInt(sCurrentLine.split("\\s+")[2])]
                        ));
            }

            return new RtFiniteStateMachine(states[firstIndex]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}