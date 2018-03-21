package com.tdanylchuk.drawmaster.engine.command.processor.strategy

import com.tdanylchuk.drawmaster.engine.Canvas
import com.tdanylchuk.drawmaster.engine.command.Command
import spock.lang.Specification

import static com.tdanylchuk.drawmaster.engine.command.CommandType.ERASE

class EraseStrategyTest extends Specification {

    private canvas = Mock(Canvas)
    private strategy = new EraseStrategy(canvas)

    def "should erase canvas"() {
        given:
        def command = new Command(ERASE, [] as String[])
        def height = 2
        def width = 4

        when:
        strategy.process(command)

        then:
        1 * canvas.isInitialized() >> true
        1 * canvas.getWidth() >> width
        1 * canvas.getHeight() >> height

        and:
        1 * canvas.initialize(width, height)

        and:
        1 * canvas.print()
        0 * _
    }

    def "should return proper type"() {
        when:
        def type = strategy.type()

        then:
        type == ERASE
    }
}
