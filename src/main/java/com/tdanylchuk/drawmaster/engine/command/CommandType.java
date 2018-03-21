package com.tdanylchuk.drawmaster.engine.command;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum CommandType {

    CREATE_CANVAS("C", 2),
    DRAW_LINE("L", 4),
    DRAW_RECTANGLE("R", 4),
    FILL_AREA("B", 3),
    ERASE("E", 0),
    QUIT("Q", 0);

    private final String representation;
    private final int requiredArgumentsCount;

    public static Optional<CommandType> lookup(String command) {
        return Arrays.stream(CommandType.values())
                .filter(commandType -> commandType.representation.equals(command))
                .findFirst();
    }

    public static Set<String> getRepresentations() {
        return Arrays.stream(CommandType.values())
                .map(CommandType::getRepresentation)
                .collect(Collectors.toSet());
    }

}
