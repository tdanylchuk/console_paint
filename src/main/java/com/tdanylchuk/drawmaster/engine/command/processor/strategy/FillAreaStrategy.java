package com.tdanylchuk.drawmaster.engine.command.processor.strategy;

import com.tdanylchuk.drawmaster.engine.Canvas;
import com.tdanylchuk.drawmaster.engine.command.Command;
import com.tdanylchuk.drawmaster.engine.command.CommandType;
import org.springframework.stereotype.Component;

import static com.tdanylchuk.drawmaster.engine.command.CommandType.FILL_AREA;

@Component
public class FillAreaStrategy extends AbstractDrawableStrategy {

    public FillAreaStrategy(final Canvas canvas) {
        super(canvas);
    }

    @Override
    protected void processDrawable(final Command command) {
        int x = command.getIntParam(0) - 1;
        int y = command.getIntParam(1) - 1;
        char colorToFill = command.getCharParam(2);

        validateParam(x, canvas.getWidth());
        validateParam(y, canvas.getHeight());

        char colorToReplace = canvas.getColorAt(x, y);
        tryReplace(x, y, colorToReplace, colorToFill);
    }

    private void tryReplace(int x, int y, char colorToReplace, char colorToFill) {
        if (x >= 0 && y >= 0 && x < canvas.getWidth() && y < canvas.getHeight()) {
            fillCellWithMates(x, y, colorToReplace, colorToFill);
        }
    }

    private void fillCellWithMates(final int x, final int y, final char colorToReplace, final char colorToFill) {
        char currentColor = canvas.getColorAt(x, y);
        if (currentColor == colorToReplace) {
            canvas.setColor(x, y, colorToFill);
            tryReplace(x + 1, y, colorToReplace, colorToFill);
            tryReplace(x - 1, y, colorToReplace, colorToFill);
            tryReplace(x, y + 1, colorToReplace, colorToFill);
            tryReplace(x, y - 1, colorToReplace, colorToFill);
        }
    }

    public CommandType type() {
        return FILL_AREA;
    }
}
