package ru.mirea;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public abstract class AbstractTest {
    protected String trimOutputStream(ByteArrayOutputStream outputStream, int actualInputStart) {
        String fullOutput = outputStream.toString();
        String[] lines = fullOutput.split(System.lineSeparator());
        return String.join(System.lineSeparator(), java.util.Arrays.copyOfRange(lines, actualInputStart, lines.length)).trim();
    }

    protected void emulateInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    protected ByteArrayOutputStream captureOutput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        return outputStream;
    }
}
