package com.tdanylchuk.drawmaster.engine.command.processor.strategy;

import com.tdanylchuk.drawmaster.engine.Canvas;
import com.tdanylchuk.drawmaster.engine.command.Command;
import com.tdanylchuk.drawmaster.engine.command.CommandType;
import org.springframework.stereotype.Component;

import static com.tdanylchuk.drawmaster.engine.command.CommandType.DRAW_RECTANGLE;

@Component
public class DrawRectangleStrategy extends AbstractDrawableStrategy {

    public DrawRectangleStrategy(final Canvas canvas) {
        super(canvas);
    }

    @Override
    protected void processDrawable(final Command command) {
        int x1 = command.getIntParam(0) - 1;
        int y1 = command.getIntParam(1) - 1;
        int x2 = command.getIntParam(2) - 1;
        int y2 = command.getIntParam(3) - 1;

        validateParams(canvas.getWidth(), x1, x2);
        validateParams(canvas.getHeight(), y1, y2);

        drawLine(x1, y1, x2, y1);
        drawLine(x1, y1, x1, y2);
        drawLine(x2, y1, x2, y2);
        drawLine(x1, y2, x2, y2);
    }

    public CommandType type() {
        return DRAW_RECTANGLE;
    }
}
