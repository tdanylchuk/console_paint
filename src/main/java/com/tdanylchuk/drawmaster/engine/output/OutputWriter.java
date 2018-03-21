package com.tdanylchuk.drawmaster.engine.output;

public interface OutputWriter {

    void print(char character);

    void print(String string);

    void println(String string);

    void newLine();
}
