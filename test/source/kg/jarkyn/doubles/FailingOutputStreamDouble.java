package kg.jarkyn.doubles;

import java.io.IOException;
import java.io.OutputStream;

public class FailingOutputStreamDouble  extends OutputStream {
    @Override
    public void write(int b) throws IOException {
    }

    @Override
    public void write(byte[] b) throws IOException {
        throw new IOException();
    }
}
