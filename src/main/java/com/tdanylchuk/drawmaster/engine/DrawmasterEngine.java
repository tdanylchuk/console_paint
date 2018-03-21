package com.tdanylchuk.drawmaster.engine;

import com.tdanylchuk.drawmaster.engine.command.Command;
import com.tdanylchuk.drawmaster.engine.command.processor.CommandProcessor;
import com.tdanylchuk.drawmaster.engine.command.reader.CommandReader;
import com.tdanylchuk.drawmaster.engine.output.OutputWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DrawmasterEngine {

    private final CommandReader commandReader;
    private final CommandProcessor commandProcessor;
    private final OutputWriter outputWriter;

    public void start() {
        while (true) {
            try {
                outputWriter.print("enter command: ");
                Command command = commandReader.read();
                commandProcessor.process(command);
            } catch (Exception ex) {
                outputWriter.println(ex.getMessage());
            }

        }
    }

}
