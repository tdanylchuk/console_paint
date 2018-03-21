package com.tdanylchuk.drawmaster.engine.command.processor.strategy

import com.tdanylchuk.drawmaster.engine.ShutDownHook
import com.tdanylchuk.drawmaster.engine.command.Command
import com.tdanylchuk.drawmaster.engine.output.OutputWriter
import spock.lang.Specification

import static com.tdanylchuk.drawmaster.engine.command.CommandType.ERASE
import static com.tdanylchuk.drawmaster.engine.command.CommandType.QUIT

class QuitStrategyTest extends Specification {

    private outputWriter = Mock(OutputWriter)
    private shutDownHook = Mock(ShutDownHook)

    private strategy = new QuitStrategy(outputWriter, shutDownHook)

    def "should properly invoke shutdown hook"() {
        given:
        def command = new Command(ERASE, [] as String[])

        when:
        strategy.process(command)

        then:
        1 * outputWriter.println(_)
        1 * shutDownHook.quit()

        and:
        0 * _
    }

    def "should return proper type"() {
        when:
        def type = strategy.type()

        then:
        type == QUIT
    }
}
