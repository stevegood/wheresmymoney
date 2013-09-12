package org.stevegood.meta



import grails.test.mixin.*
import org.junit.*


@TestFor(UserCategory)
class UserCategoryTests {

    void testToString() {
        def category = new UserCategory(name: 'Testing', color: '#000000')
        assert category.toString() == 'Testing'
    }
}
