package com.tdanylchuk.drawmaster.engine.command.processor

import com.tdanylchuk.drawmaster.engine.command.Command
import com.tdanylchuk.drawmaster.engine.command.processor.strategy.CommandStrategy
import com.tdanylchuk.drawmaster.exception.DrawmasterApplicationException
import spock.lang.Specification

import static com.tdanylchuk.drawmaster.engine.command.CommandType.*

class DefaultCommandProcessorTest extends Specification {

    def quitStrategy = Mock(CommandStrategy)
    def eraseStrategy = Mock(CommandStrategy)

    def "should invoke proper strategy"() {
        given:
        def command = new Command(ERASE, [] as String[])

        when:
        def processor = new DefaultCommandProcessor([quitStrategy, eraseStrategy])
        processor.process(command)

        then:
        1 * quitStrategy.type() >> QUIT
        1 * eraseStrategy.type() >> ERASE

        and:
        1 * eraseStrategy.process(command)
        0 * _
    }

    def "should fail on missed strategy"() {
        given:
        def command = new Command(DRAW_RECTANGLE, [] as String[])

        when:
        def processor = new DefaultCommandProcessor([quitStrategy, eraseStrategy])
        processor.process(command)

        then:
        1 * quitStrategy.type() >> QUIT
        1 * eraseStrategy.type() >> ERASE

        and:
        thrown(DrawmasterApplicationException)
    }
}
