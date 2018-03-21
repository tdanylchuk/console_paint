package com.tdanylchuk.drawmaster.engine.command;

import com.tdanylchuk.drawmaster.exception.DrawmasterApplicationException;
import com.tdanylchuk.drawmaster.exception.UserInputException;
import lombok.Value;

import java.util.Arrays;
import java.util.function.Function;

import static java.lang.String.format;

@Value
public class Command {

    CommandType commandType;
    String[] params;

    public char getCharParam(int index) {
        return convertSave(index, string -> string.charAt(0));
    }

    public int getIntParam(int index) {
        return convertSave(index, Integer::valueOf);
    }

    private String getParam(int index) {
        if (params.length <= index) {
            throw new DrawmasterApplicationException(
                    format("Unfortunately required param[%d] is missed from provided params - %s.", index, Arrays.toString(params)));
        }
        return params[index];
    }

    private <O> O convertSave(int index, Function<String, O> convertFunc) {
        String param = getParam(index);
        try {
            return convertFunc.apply(param);
        } catch (IllegalArgumentException ex) {
            throw new UserInputException(format("Input parameter[%s] has incorrect format.", param));
        }
    }

}
