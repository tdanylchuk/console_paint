package com.tdanylchuk.drawmaster.engine.output

import spock.lang.Specification

class DefaultOutputWriterTest extends Specification {

    private writer = Mock(BufferedWriter)
    private bufferedOutputWriter = new DefaultOutputWriter(writer)

    def "should print string"() {
        given:
        def printString = 'print me'

        when:
        bufferedOutputWriter.print(printString)

        then:
        1 * writer.write(printString)
        1 * writer.flush()
        0 * _
    }

    def "should print char"() {
        given:
        def printChar = 'a' as Character

        when:
        bufferedOutputWriter.print(printChar)

        then:
        1 * writer.write(printChar)
        1 * writer.flush()
        0 * _
    }

    def "should println"() {
        given:
        def printString = 'print me'

        when:
        bufferedOutputWriter.println(printString)

        then:
        1 * writer.write(printString)
        1 * writer.newLine()
        2 * writer.flush()
        0 * _
    }

    def "should print newLine"() {
        when:
        bufferedOutputWriter.newLine()

        then:
        1 * writer.newLine()
        1 * writer.flush()
        0 * _
    }
}
