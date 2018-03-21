package com.tdanylchuk.drawmaster.engine.command.processor.strategy

import com.tdanylchuk.drawmaster.engine.Canvas
import com.tdanylchuk.drawmaster.engine.command.Command
import com.tdanylchuk.drawmaster.exception.UserInputException
import spock.lang.Specification
import spock.lang.Unroll

import static com.tdanylchuk.drawmaster.engine.command.CommandType.DRAW_RECTANGLE

class DrawRectangleStrategyTest extends Specification {

    private canvas = Mock(Canvas)
    private strategy = new DrawRectangleStrategy(canvas)

    @Unroll
    def "should draw rectangle from params[#params]"() {
        given:
        def command = new Command(DRAW_RECTANGLE, params as String[])

        when:
        strategy.process(command)

        then:
        1 * canvas.isInitialized() >> true
        1 * canvas.getWidth() >> 4
        1 * canvas.getHeight() >> 2

        and:
        2 * canvas.setColor(0, 0, 'x' as Character)
        1 * canvas.setColor(1, 0, 'x' as Character)
        2 * canvas.setColor(2, 0, 'x' as Character)
        2 * canvas.setColor(0, 1, 'x' as Character)
        1 * canvas.setColor(1, 1, 'x' as Character)
        2 * canvas.setColor(2, 1, 'x' as Character)

        and:
        1 * canvas.print()
        0 * _

        where:
        params               || _
        ['1', '1', '3', '2'] || _
        ['3', '2', '1', '1'] || _
    }

    @Unroll
    def "should fail on param[#params] validation"() {
        given:
        def command = new Command(DRAW_RECTANGLE, params as String[])

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
        ['2', '2', '2', '-3'] || _
        ['5', '2', '2', '2']  || _
    }

    def "should return proper type"() {
        when:
        def type = strategy.type()

        then:
        type == DRAW_RECTANGLE
    }
}
