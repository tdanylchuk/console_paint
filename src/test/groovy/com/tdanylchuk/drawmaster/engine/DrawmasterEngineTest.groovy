package com.tdanylchuk.drawmaster.engine

import com.tdanylchuk.drawmaster.engine.command.Command
import com.tdanylchuk.drawmaster.engine.command.processor.CommandProcessor
import com.tdanylchuk.drawmaster.engine.command.reader.CommandReader
import com.tdanylchuk.drawmaster.engine.output.OutputWriter
import spock.lang.Specification

import static com.tdanylchuk.drawmaster.engine.command.CommandType.*

class DrawmasterEngineTest extends Specification {

    private commandReader = Mock(CommandReader)
    private commandProcessor = Mock(CommandProcessor)
    private outputWriter = Mock(OutputWriter)

    private drawmasterEngine = new DrawmasterEngine(commandReader, commandProcessor, outputWriter)

    def "should process commands"() {
        given:
        def command1 = new Command(CREATE_CANVAS, [] as String[])
        def command2 = new Command(DRAW_LINE, [] as String[])
        def command3 = new Command(DRAW_RECTANGLE, [] as String[])

        when:
        drawmasterEngine.start()

        then:
        3 * commandReader.read() >>> [command1, command2, command3]
        and:
        1 * outputWriter.print(_)
        1 * commandProcessor.process(command1)
        and:
        1 * outputWriter.print(_)
        1 * commandProcessor.process(command2) >> { throw new IllegalArgumentException() }
        1 * outputWriter.println(_)
        and:
        1 * outputWriter.print(_)
        1 * commandProcessor.process(command3) >> { throw new IllegalArgumentException() }
        1 * outputWriter.println(_) >> { throw new IllegalArgumentException() }

        and:
        thrown(IllegalArgumentException)
        0 * _
    }
}

