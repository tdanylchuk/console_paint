package com.tdanylchuk.drawmaster.engine.command.processor;

import com.tdanylchuk.drawmaster.engine.command.Command;
import com.tdanylchuk.drawmaster.engine.command.CommandType;
import com.tdanylchuk.drawmaster.engine.command.processor.strategy.CommandStrategy;
import com.tdanylchuk.drawmaster.exception.DrawmasterApplicationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static java.util.Optional.ofNullable;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Component
public class DefaultCommandProcessor implements CommandProcessor {

    private final Map<CommandType, CommandStrategy> strategyMap;

    public DefaultCommandProcessor(final List<CommandStrategy> strategyList) {
        this.strategyMap = strategyList.stream()
                .collect(toMap(CommandStrategy::type, identity()));
    }

    public void process(final Command command) {
        getStrategy(command).process(command);
    }

    private CommandStrategy getStrategy(final Command command) {
        CommandType commandType = command.getCommandType();
        return ofNullable(strategyMap.get(commandType))
                .orElseThrow(() -> new DrawmasterApplicationException("Cannot find strategy for CommandType - " + commandType));
    }
}
