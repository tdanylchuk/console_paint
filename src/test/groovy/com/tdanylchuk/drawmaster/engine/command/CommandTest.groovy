package com.tdanylchuk.drawmaster.engine.command

import com.tdanylchuk.drawmaster.exception.DrawmasterApplicationException
import com.tdanylchuk.drawmaster.exception.UserInputException
import spock.lang.Specification

import static com.tdanylchuk.drawmaster.engine.command.CommandType.DRAW_LINE

class CommandTest extends Specification {

    private command = new Command(DRAW_LINE, ['2', '3', '4gdg'] as String[])

    def "should return int param"() {
        when:
        def result = command.getIntParam(1)

        then:
        3 == result
    }

    def "should fail on parsing int param"() {
        when:
        command.getIntParam(2)

        then:
        thrown(UserInputException)
    }

    def "should fail on retrieving out of bound int param"() {
        when:
        command.getIntParam(3)

        then:
        thrown(DrawmasterApplicationException)
    }
}
