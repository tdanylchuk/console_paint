package com.tdanylchuk.drawmaster.engine.command.processor.strategy;

import com.tdanylchuk.drawmaster.engine.Canvas;
import com.tdanylchuk.drawmaster.engine.command.Command;
import com.tdanylchuk.drawmaster.engine.command.CommandType;
import com.tdanylchuk.drawmaster.exception.UserInputException;
import org.springframework.stereotype.Component;

import static com.tdanylchuk.drawmaster.engine.command.CommandType.DRAW_LINE;

@Component
public class DrawLineStrategy extends AbstractDrawableStrategy {

    public DrawLineStrategy(final Canvas canvas) {
        super(canvas);
    }

    @Override
    public void processDrawable(Command command) {
        int x1 = command.getIntParam(0) - 1;
        int y1 = command.getIntParam(1) - 1;
        int x2 = command.getIntParam(2) - 1;
        int y2 = command.getIntParam(3) - 1;
        validateCoordinates(x1, y1, x2, y2);
        drawLine(x1, y1, x2, y2);
    }

    private void validateCoordinates(int x1, int y1, int x2, int y2) {
        validateParams(canvas.getWidth(), x1, x2);
        validateParams(canvas.getHeight(), y1, y2);
        validateSupportedLines(x1, y1, x2, y2);
    }

    private void validateSupportedLines(final int x1, final int y1, final int x2, final int y2) {
        if (x1 != x2 && y1 != y2) {
            throw new UserInputException("Only horizontal and vertical lines are supported.");
        }
    }

    public CommandType type() {
        return DRAW_LINE;
    }
}
