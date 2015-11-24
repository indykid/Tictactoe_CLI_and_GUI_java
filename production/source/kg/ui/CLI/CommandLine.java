package kg.ui.CLI;

import java.io.*;

public class CommandLine {
    private OutputStream output;
    private BufferedReader inputReader;

    public CommandLine(InputStream input, OutputStream output) {
        this.inputReader = new BufferedReader(new InputStreamReader(input));
        this.output = output;
    }

    public void show(String message) {
        try {
            output.write((message).getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getInput() {
        try {
            return inputReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
