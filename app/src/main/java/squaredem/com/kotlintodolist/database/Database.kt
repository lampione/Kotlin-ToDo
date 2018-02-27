package squaredem.com.kotlintodolist.database

import io.realm.Realm
import squaredem.com.kotlintodolist.model.ToDoItem
import java.util.*

/**
 * Created by Matteo Miceli on 04/02/2018.
 * Utils functions for database management.
 */
object RealmDb {

    // this get initialized upon first usage, and IT IS immutable
    val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    fun insertOrUpdate(toDoItem: ToDoItem) {
        realm.beginTransaction()
        realm.insertOrUpdate(toDoItem)
        realm.commitTransaction()
    }

    fun delete(toDoItem: ToDoItem) {
        val results = realm.where(ToDoItem::class.java).equalTo("uuid", toDoItem.uuid).findFirst()
        realm.beginTransaction()
        results?.deleteFromRealm()
        realm.commitTransaction()
    }

    fun delete(id: String) {
        val results = realm.where(ToDoItem::class.java).equalTo("uuid", id).findFirst()
        realm.beginTransaction()
        results?.deleteFromRealm()
        realm.commitTransaction()
    }

    fun clearAll() {
        realm.beginTransaction()
        realm.delete(ToDoItem::class.java)
        realm.commitTransaction()
    }

    fun getAll(): List<ToDoItem> {
        return realm.where(ToDoItem::class.java).findAll()
    }

    fun getUuid(): String {
        // NEVER DO THIS!! Try to always follow a criteria for id generations.
        return UUID.randomUUID().toString()
    }

}
