package com.tdanylchuk.drawmaster.engine.command.processor.strategy;

import com.tdanylchuk.drawmaster.engine.Canvas;
import com.tdanylchuk.drawmaster.engine.command.Command;
import com.tdanylchuk.drawmaster.engine.command.CommandType;
import com.tdanylchuk.drawmaster.exception.UserInputException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.tdanylchuk.drawmaster.engine.command.CommandType.CREATE_CANVAS;
import static java.lang.String.format;

@Component
@AllArgsConstructor
public class CreateCanvasStrategy implements CommandStrategy {

    private final Canvas canvas;

    @Override
    public void process(Command command) {
        int width = command.getIntParam(0);
        int height = command.getIntParam(1);
        validateParams(height, width);

        canvas.initialize(width, height);
        canvas.print();
    }

    private void validateParams(final int height, final int width) {
        if (height <= 0 || width <= 0) {
            throw new UserInputException(format("Height[%d] and width[%d] should be > 0.", height, width));
        }
    }

    public CommandType type() {
        return CREATE_CANVAS;
    }
}
