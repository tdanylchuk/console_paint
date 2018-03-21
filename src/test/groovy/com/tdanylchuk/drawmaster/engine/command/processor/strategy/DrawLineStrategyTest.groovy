package com.tdanylchuk.drawmaster.engine.command.processor.strategy

import com.tdanylchuk.drawmaster.engine.Canvas
import com.tdanylchuk.drawmaster.engine.command.Command
import com.tdanylchuk.drawmaster.exception.UserInputException
import spock.lang.Specification
import spock.lang.Unroll

import static com.tdanylchuk.drawmaster.engine.command.CommandType.DRAW_LINE

class DrawLineStrategyTest extends Specification {

    private canvas = Mock(Canvas)
    private strategy = new DrawLineStrategy(canvas)

    @Unroll
    def "should draw line from params[#params]"() {
        given:
        def command = new Command(DRAW_LINE, params as String[])

        when:
        strategy.process(command)

        then:
        1 * canvas.isInitialized() >> true
        1 * canvas.getWidth() >> 4
        1 * canvas.getHeight() >> 2

        and:
        1 * canvas.setColor(2, 0, 'x' as Character)
        1 * canvas.setColor(2, 1, 'x' as Character)

        and:
        1 * canvas.print()
        0 * _

        where:
        params               || _
        ['3', '2', '3', '1'] || _
        ['3', '1', '3', '2'] || _
    }

    @Unroll
    def "should fail on param[#params] validation"() {
        given:
        def command = new Command(DRAW_LINE, params as String[])

        when:
        strategy.process(command)

        then:
        1 * canvas.isInitialized() >> true
        _ * canvas.getWidth() >> 4
        _ * canvas.getHeight() >> 2
        0 * _

        and:
        thrown(UserInputException)

        where:
        params                || _
        ['2', '2', '1', '1']  || _
        ['2', '2', '2', '-3'] || _
        ['5', '2', '2', '2']  || _
    }

    def "should fail on uninitialized canvas"() {
        given:
        def command = new Command(DRAW_LINE, [] as String[])

        when:
        strategy.process(command)

        then:
        1 * canvas.isInitialized() >> false
        0 * _

        and:
        thrown(UserInputException)
    }

    def "should return proper type"() {
        when:
        def type = strategy.type()

        then:
        type == DRAW_LINE
    }
}
