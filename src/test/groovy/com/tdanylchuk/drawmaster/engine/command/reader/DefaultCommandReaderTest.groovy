package com.tdanylchuk.drawmaster.engine.command.reader

import com.tdanylchuk.drawmaster.engine.command.Command
import com.tdanylchuk.drawmaster.exception.UserInputException
import spock.lang.Specification
import spock.lang.Unroll

import static com.tdanylchuk.drawmaster.engine.command.CommandType.CREATE_CANVAS

class DefaultCommandReaderTest extends Specification {

    private reader = Mock(BufferedReader)
    private commandReader = new DefaultCommandReader(reader)

    @Unroll
    def "should read command from input[#input]"() {
        when:
        def command = commandReader.read()

        then:
        1 * reader.readLine() >> input
        0 * _

        expect:
        new Command(CREATE_CANVAS, ['25', '20'] as String[]) == command

        where:
        input       | _
        'C 25 20'   | _
        ' C 25 20 ' | _
    }

    @Unroll
    def "should fail on input[#input] validation"() {
        when:
        commandReader.read()

        then:
        1 * reader.readLine() >> input
        0 * _

        and:
        thrown(UserInputException)

        where:
        input     | _
        'C 25'    | _
        'Z 25 32' | _
        '    '    | _
    }
}
