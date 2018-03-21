package com.tdanylchuk.drawmaster.functional

import com.tdanylchuk.drawmaster.configuration.DrawmasterApplicationConfiguration
import com.tdanylchuk.drawmaster.engine.Canvas
import com.tdanylchuk.drawmaster.engine.DrawmasterEngine
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

@ContextConfiguration(classes = [DrawmasterApplicationConfiguration, TestConfiguration])
class DrawmasterFunctionalTest extends Specification {

    private final static POLLING_CONDITION = new PollingConditions(timeout: 10, initialDelay: 1)

    private final static OUT_STREAM = new PipedOutputStream()

    @Autowired
    private ApplicationContext applicationContext
    @Autowired
    private DrawmasterEngine drawmasterEngine
    @Autowired
    private Canvas canvas

    def cleanup() {
        OUT_STREAM.close()
    }

    private char[][] fieldToAssert =
            [['o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'x', 'x', 'x', 'x', 'x', 'o', 'o'],
             ['x', 'x', 'x', 'x', 'x', 'x', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'x', ' ', ' ', ' ', 'x', 'o', 'o'],
             [' ', ' ', ' ', ' ', ' ', 'x', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'x', 'x', 'x', 'x', 'x', 'o', 'o'],
             [' ', ' ', ' ', ' ', ' ', 'x', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o']] as char[][]

    def "should calculate all bets"() {
        given:
        def inputs = ['C 20 4',
                      'L 1 2 6 2',
                      'L 6 3 6 4',
                      'R 14 1 18 3',
                      'B 10 3 o']
        def testWriter = new BufferedWriter(new OutputStreamWriter(OUT_STREAM))

        when: 'start drawmaster on background'
        Thread.start {
            drawmasterEngine.start()
        }

        then: 'imitate user input'
        inputs.forEach {
            testWriter.writeLine(it)
            testWriter.flush()
        }

        expect: 'check canvas data'
        POLLING_CONDITION.eventually {
            assert fieldToAssert == canvas.field
        }
    }

    @Configuration
    static class TestConfiguration {

        @Bean
        BufferedReader consoleReader() {
            new BufferedReader(new InputStreamReader(new PipedInputStream(OUT_STREAM)))
        }
    }

}
