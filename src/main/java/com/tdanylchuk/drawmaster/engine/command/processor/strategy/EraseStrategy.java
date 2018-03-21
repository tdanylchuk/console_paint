package com.tdanylchuk.drawmaster.engine.command.processor.strategy;

import com.tdanylchuk.drawmaster.engine.Canvas;
import com.tdanylchuk.drawmaster.engine.command.Command;
import com.tdanylchuk.drawmaster.engine.command.CommandType;
import org.springframework.stereotype.Component;

import static com.tdanylchuk.drawmaster.engine.command.CommandType.ERASE;

@Component
public class EraseStrategy extends AbstractDrawableStrategy {

    public EraseStrategy(final Canvas canvas) {
        super(canvas);
    }

    @Override
    protected void processDrawable(final Command command) {
        canvas.initialize(canvas.getWidth(), canvas.getHeight());
    }

    public CommandType type() {
        return ERASE;
    }
}
