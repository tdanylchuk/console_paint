package com.tdanylchuk.drawmaster.engine.command.processor.strategy;

import com.tdanylchuk.drawmaster.engine.ShutDownHook;
import com.tdanylchuk.drawmaster.engine.command.Command;
import com.tdanylchuk.drawmaster.engine.command.CommandType;
import com.tdanylchuk.drawmaster.engine.output.OutputWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.tdanylchuk.drawmaster.engine.command.CommandType.QUIT;

@Component
@AllArgsConstructor
public class QuitStrategy implements CommandStrategy {

    private final OutputWriter outputWriter;
    private final ShutDownHook shutDownHook;

    public void process(Command command) {
        outputWriter.println("Thank you for trying out!");
        shutDownHook.quit();
    }

    public CommandType type() {
        return QUIT;
    }
}
