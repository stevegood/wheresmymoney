package org.stevegood.util.color

/**
 * Created with IntelliJ IDEA.
 * User: steve
 * Date: 9/9/13
 * Time: 12:13 AM
 * To change this template use File | Settings | File Templates.
 */
class ColorUtils {

    static String LETTERS = '0123456789ABCDEF'

    static String randomHexColor() {
        String color = '#'
        6.times {
            color += LETTERS[Math.round(Math.random() * 15) as int]
        }
        return color
    }

}
