package org.stevegood.meta

import org.stevegood.sec.User

class UserCategory extends Category {

    static belongsTo = [user:User]

    static constraints = {
    }
}
