package com.tdanylchuk.drawmaster.engine

import com.tdanylchuk.drawmaster.engine.output.OutputWriter
import spock.lang.Specification

class CanvasTest extends Specification {

    private outputWriter = Mock(OutputWriter)
    private Canvas canvas

    def setup() {
        canvas = new Canvas(outputWriter)
    }

    def "should return isInitialized"() {
        when:
        def result = canvas.isInitialized()

        then:
        !result

        and:
        canvas.initialize(1, 2)

        expect:
        canvas.isInitialized()
    }

    def "should set color"() {
        given:
        canvas.initialize(1, 2)
        def color = 't' as Character

        when:
        canvas.setColor(0, 1, color)

        then:
        def result = canvas.getColorAt(0, 1)

        expect:
        color == result
    }

    def "should return height"() {
        given:
        canvas.initialize(1, 2)

        when:
        def result = canvas.getHeight()

        then:
        2 == result
    }

    def "should return width"() {
        given:
        canvas.initialize(1, 2)

        when:
        def result = canvas.getWidth()

        then:
        1 == result
    }

    def "should print canvas"() {
        given:
        canvas.initialize(2, 1)

        when:
        canvas.print()

        then:
        4 * outputWriter.print('-')
        1 * outputWriter.newLine()
        and:
        1 * outputWriter.print('|')
        and:
        2 * outputWriter.print(' ')
        and:
        1 * outputWriter.print('|')
        1 * outputWriter.newLine()
        and:
        4 * outputWriter.print('-')
        and:
        2 * outputWriter.newLine()

        and:
        0 * _

    }
}
