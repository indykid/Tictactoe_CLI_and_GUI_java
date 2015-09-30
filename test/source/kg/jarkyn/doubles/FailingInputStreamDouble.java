package kg.jarkyn.doubles;

import java.io.IOException;
import java.io.InputStream;

public class FailingInputStreamDouble extends InputStream {
    @Override
    public int read() throws IOException {
        throw new IOException();
    }
}
