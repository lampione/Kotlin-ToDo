package squaredem.com.kotlintodolist

import android.app.Application
import io.realm.Realm

/**
 * Created by Matteo Miceli on 04/02/2018.
 */

class ToDoApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }

}
