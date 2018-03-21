package com.tdanylchuk.drawmaster.engine;

import com.tdanylchuk.drawmaster.engine.output.OutputWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class Canvas {

    private final static char EMPTY_CHAR = ' ';

    private final OutputWriter outputWriter;

    private char[][] field = null;

    public boolean isInitialized() {
        return field != null;
    }

    public void setColor(int x, int y, char color) {
        field[y][x] = color;
    }

    public int getHeight() {
        return field.length;
    }

    public int getWidth() {
        return field[0].length;
    }

    public void initialize(int width, int height) {
        field = new char[height][width];
        for (char[] row : field) {
            Arrays.fill(row, EMPTY_CHAR);
        }
    }

    public void print() {
        printBoundaryLine();
        for (char[] row : field) {
            for (int j = 0; j < row.length; j++) {
                if (j == 0) {
                    outputWriter.print('|');
                }
                outputWriter.print(row[j]);
                if (j == row.length - 1) {
                    outputWriter.print('|');
                }
            }
            outputWriter.newLine();
        }
        printBoundaryLine();
        outputWriter.newLine();
    }

    private void printBoundaryLine() {
        IntStream.range(0, getWidth() + 2).forEach(o -> outputWriter.print('-'));
        outputWriter.newLine();
    }

    public char getColorAt(int x, int y) {
        return field[y][x];
    }
}
