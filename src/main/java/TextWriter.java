import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextWriter {

    void createFile(String id) {
        try {
            File file = new File("data", "id_" + id + "_notes.txt");
            file.createNewFile();

        } catch (IOException io) { }
    }

    void writeText(JTextArea textArea, String id) {

        try {
            // Creates a filename using the character's ID
            File notes = new File("data", "id_" + id + "_notes.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(notes));

            bufferedWriter.write(textArea.getText());
            bufferedWriter.close();

        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Error Writing Notes Text: " + io);
        }
    }

    void readText(JTextArea textArea, String id) {
        // Reads the appropriate file using the character's ID
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("data/id_" + id + "_notes.txt"));
            String line = bufferedReader.readLine();
            while (line != null) {
                textArea.append(line + "\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Error Reading Notes Text: " + io);
        }

    }

    void deleteFile(String id) {
        // Deletes the appropriate file using the character's ID
        try {
            Path path = Paths.get("data/id_" + id + "_notes.txt");
            Files.delete(path);
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Error Deleting Text File For This Character");
        }
    }
}
