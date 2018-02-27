package squaredem.com.kotlintodolist.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item.view.*
import squaredem.com.kotlintodolist.extensions.formatToString
import squaredem.com.kotlintodolist.model.ToDoItem

/**
 * Created by Matteo Miceli on 30/01/2018.
 */
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val items = listOf("Edit", "Delete")

    fun bind(toDoItem: ToDoItem) {

        with(toDoItem) {
            itemView.uuid.text = uuid
            itemView.content.text = content
            itemView.date.text = date.formatToString()

            /*itemView.setOnLongClickListener({
                MaterialDialog.Builder(itemView.context)
                        .items(items)
                        .itemsCallback({_, _, position, _ ->
                            if (position == 0) RealmDb.insertOrUpdate(toDoItem)
                            if (position == 1) RealmDb.delete(toDoItem)
                        }).build().show()
                true
            })*/
        }

    }

}
