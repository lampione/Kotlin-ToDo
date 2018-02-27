package squaredem.com.kotlintodolist.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Matteo Miceli on 29/01/2018.
 */

// This class has to specify 'open' because
// classes in kotlin are 'final' by default, and some libraries may not see them (like Realm)
open class ToDoItem : RealmObject() {

    @PrimaryKey
    var uuid: String = ""

    var content: String = ""

    var date: Long = 0

}
