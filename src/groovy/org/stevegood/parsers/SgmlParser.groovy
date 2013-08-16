package org.stevegood.parsers

/**
 * Created with IntelliJ IDEA.
 * User: steve
 * Date: 8/16/13
 * Time: 12:57 PM
 * To change this template use File | Settings | File Templates.
 */
class SgmlParser {

    def extractXml(File file) {
        def lines = []
        file.eachLine {
            def line = it.trim()
            if ( line?.length() && line?.getAt(0) == '<') {
                lines << line
            }
        }

        new XmlSlurper().parseText(lines.join(''))
    }

}
