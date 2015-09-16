package kg.jarkyn;

import java.io.*;

public class Cli {
    private OutputStream output;
    private BufferedReader inputReader;

    public Cli(OutputStream output, InputStream input) {
        this.inputReader = new BufferedReader(new InputStreamReader(input));
        this.output = output;
    }

    public void show(String message) {
        try {
            output.write((message + "\n").getBytes());
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
