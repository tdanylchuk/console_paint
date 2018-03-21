package com.tdanylchuk.drawmaster.engine.command.reader;

import com.tdanylchuk.drawmaster.engine.command.Command;
import com.tdanylchuk.drawmaster.engine.command.CommandType;
import com.tdanylchuk.drawmaster.exception.UserInputException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.util.Arrays;

import static java.lang.String.format;
import static org.springframework.util.StringUtils.hasText;

@Component
@AllArgsConstructor
public class DefaultCommandReader implements CommandReader {

    private final static String COMMAND_SPLITERATOR = " ";

    private final BufferedReader reader;

    @SneakyThrows
    @Override
    public Command read() {
        String input = reader.readLine();
        return convertToCommand(input);
    }

    private Command convertToCommand(String input) {
        if (!hasText(input)) {
            throw new UserInputException("User input should contain at least one argument.");
        }
        input = input.trim();
        String[] inputs = input.split(COMMAND_SPLITERATOR);

        CommandType commandType = CommandType.lookup(inputs[0]).orElseThrow(() ->
                new UserInputException("First argument should be command. One from following - " + CommandType.getRepresentations()));

        String[] arguments = Arrays.copyOfRange(inputs, 1, inputs.length);
        validateArgumentLength(commandType, arguments.length);

        return new Command(commandType, arguments);
    }

    private void validateArgumentLength(CommandType commandType, int argumentsCount) {
        int requiredArgumentsCount = commandType.getRequiredArgumentsCount();
        if (requiredArgumentsCount != argumentsCount) {
            throw new UserInputException(
                    format("Incorrect count of command arguments - %d. But should be - %d.", argumentsCount, requiredArgumentsCount));
        }
    }


}
