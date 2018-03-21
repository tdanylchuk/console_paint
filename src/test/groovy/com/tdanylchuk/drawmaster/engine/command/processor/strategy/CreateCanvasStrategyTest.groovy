package com.tdanylchuk.drawmaster.engine.command.processor.strategy

import com.tdanylchuk.drawmaster.engine.Canvas
import com.tdanylchuk.drawmaster.engine.command.Command
import com.tdanylchuk.drawmaster.exception.UserInputException
import spock.lang.Specification
import spock.lang.Unroll

import static com.tdanylchuk.drawmaster.engine.command.CommandType.CREATE_CANVAS

class CreateCanvasStrategyTest extends Specification {

    private canvas = Mock(Canvas)
    private canvasStrategy = new CreateCanvasStrategy(canvas)

    def "should process"() {
        given:
        def command = new Command(CREATE_CANVAS, ['1', '2'] as String[])

        when:
        canvasStrategy.process(command)

        then:
        1 * canvas.initialize(1, 2)
        1 * canvas.print()
        0 * _
    }

    @Unroll
    def "should fail on param[#params] validation"() {
        given:
        def command = new Command(CREATE_CANVAS, params as String[])

        when:
        canvasStrategy.process(command)

        then:
        thrown(UserInputException)
        0 * _

        where:
        params      || _
        ['-1', '2'] || _
        ['1', '-2'] || _
        ['a', '2']  || _
        ['0', '0']  || _
        ['0', '3']  || _
        ['3', '0']  || _
    }

    def "should return proper type"() {
        when:
        def type = canvasStrategy.type()

        then:
        type == CREATE_CANVAS
    }
}
