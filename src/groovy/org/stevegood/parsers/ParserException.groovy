package org.stevegood.parsers

/**
 * Created with IntelliJ IDEA.
 * User: steve
 * Date: 9/6/13
 * Time: 12:55 AM
 * To change this template use File | Settings | File Templates.
 */
class ParserException extends RuntimeException {
    String message
    String detail
    File file
    long lineNumber
}
