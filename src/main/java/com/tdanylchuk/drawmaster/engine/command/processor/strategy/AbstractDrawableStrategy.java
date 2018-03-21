package com.tdanylchuk.drawmaster.engine.command.processor.strategy;

import com.tdanylchuk.drawmaster.engine.Canvas;
import com.tdanylchuk.drawmaster.engine.command.Command;
import com.tdanylchuk.drawmaster.exception.UserInputException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static java.lang.String.format;

@Component
@AllArgsConstructor
public abstract class AbstractDrawableStrategy implements CommandStrategy {

    private static final char LINE_CHARACTER = 'x';

    protected final Canvas canvas;

    @Override
    public void process(Command command) {
        checkIfInitialized();
        processDrawable(command);
        canvas.print();
    }

    private void checkIfInitialized() {
        if (!canvas.isInitialized()) {
            throw new UserInputException("Canvas should be initialized first.");
        }
    }

    void drawLine(int x1, int y1, int x2, int y2) {
        int start;
        int finish;
        if (x1 == x2) {
            if (y1 > y2) {
                start = y2;
                finish = y1;
            } else {
                start = y1;
                finish = y2;
            }
            for (int i = start; i <= finish; i++) {
                canvas.setColor(x1, i, LINE_CHARACTER);
            }
        } else {
            if (x1 > x2) {
                start = x2;
                finish = x1;
            } else {
                start = x1;
                finish = x2;
            }
            for (int i = start; i <= finish; i++) {
                canvas.setColor(i, y1, LINE_CHARACTER);
            }
        }
    }

    void validateParams(int bound, int... params) {
        Arrays.stream(params).forEach(param -> validateParam(param, bound));
    }

    void validateParam(int param, int bound) {
        if (param < 0 || param >= bound) {
            throw new UserInputException(format("Param[%d] should be in range of [%d] - [%d].", param + 1, 1, bound));
        }
    }

    protected abstract void processDrawable(Command command);

}
