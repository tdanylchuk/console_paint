package com.tdanylchuk.drawmaster.engine.command.processor.strategy

import com.tdanylchuk.drawmaster.engine.Canvas
import com.tdanylchuk.drawmaster.engine.command.Command
import com.tdanylchuk.drawmaster.exception.UserInputException
import spock.lang.Specification
import spock.lang.Unroll

import static com.tdanylchuk.drawmaster.engine.command.CommandType.FILL_AREA

class FillAreaStrategyTest extends Specification {

    private canvas = Mock(Canvas)
    private strategy = new FillAreaStrategy(canvas)

    def "should fill area"() {
        given:
        def command = new Command(FILL_AREA, ['1', '1', 'a'] as String[])

        when:
        strategy.process(command)

        then:
        1 * canvas.isInitialized() >> true
        10 * canvas.getWidth() >> 2
        9 * canvas.getHeight() >> 2

        and:
        4 * canvas.getColorAt(0, 0) >>> [' ' as Character, ' ' as Character, 'a' as Character, 'a' as Character]
        1 * canvas.getColorAt(0, 1) >> (' ' as Character)
        1 * canvas.getColorAt(1, 0) >> (' ' as Character)
        2 * canvas.getColorAt(1, 1) >> ('p' as Character)

        1 * canvas.setColor(0, 0, 'a' as Character)
        1 * canvas.setColor(1, 0, 'a' as Character)
        1 * canvas.setColor(0, 1, 'a' as Character)

        and:
        1 * canvas.print()
        0 * _
    }

    @Unroll
    def "should fail on param[#params] validation"() {
        given:
        def command = new Command(FILL_AREA, params as String[])

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
        params           || _
        ['-2', '2', 'a'] || _
        ['2', '6', 'a']  || _
    }

    def "should return proper type"() {
        when:
        def type = strategy.type()

        then:
        type == FILL_AREA
    }
}
