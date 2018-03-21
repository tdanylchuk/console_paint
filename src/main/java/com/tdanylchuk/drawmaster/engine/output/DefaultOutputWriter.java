package com.tdanylchuk.drawmaster.engine.output;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;

@Component
@AllArgsConstructor
public class DefaultOutputWriter implements OutputWriter {

    private final BufferedWriter bufferedWriter;

    @Override
    @SneakyThrows
    public void print(final String string) {
        bufferedWriter.write(string);
        bufferedWriter.flush();
    }

    @Override
    @SneakyThrows
    public void print(final char character) {
        bufferedWriter.write(character);
        bufferedWriter.flush();
    }

    @Override
    @SneakyThrows
    public void println(final String string) {
        print(string);
        newLine();
    }

    @Override
    @SneakyThrows
    public void newLine() {
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }
}
