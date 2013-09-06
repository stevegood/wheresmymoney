package org.stevegood.parsers

/**
 * Created with IntelliJ IDEA.
 * User: steve
 * Date: 8/16/13
 * Time: 12:57 PM
 * To change this template use File | Settings | File Templates.
 */
class SgmlParser {

    def extractXml(File file) throws ParserException {
        def lines = []
        Boolean xmlFound = false
        file.eachLine {
            def line = it.trim()
            if (!xmlFound && line?.length() && line?.getAt(0) == '<') {
                xmlFound = true
            }

            if (xmlFound) {
                lines << line
            }
        }

        def linesReversed = lines.reverse()
        lines = []
        xmlFound = false
        linesReversed.each {
            if (!xmlFound && it?.length() && it?.getAt(0) == '<') {
                xmlFound = true
            }

            if (xmlFound) {
                lines << it
            }
        }

        lines = lines.reverse()

        def xmlSlurper
        try {
            xmlSlurper = new XmlSlurper().parseText(lines.join(''))
        } catch ( e ) {
            throw new ParserException(message: "Could not parse file for reason: ${e.message}" ,detail: e.message, file: file)
        }

        return xmlSlurper
    }

}
