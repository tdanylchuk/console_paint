package com.tdanylchuk.drawmaster.engine.command.processor;

import com.tdanylchuk.drawmaster.engine.command.Command;

public interface CommandProcessor {

    void process(Command command);
}
