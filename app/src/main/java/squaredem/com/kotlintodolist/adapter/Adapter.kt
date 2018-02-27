package squaredem.com.kotlintodolist.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import squaredem.com.kotlintodolist.R
import squaredem.com.kotlintodolist.model.ToDoItem

/**
 * Created by Matteo Miceli on 30/01/2018.
 */
class Adapter(private val context: Context): RecyclerView.Adapter<ViewHolder>() {

    var data: List<ToDoItem>? = ArrayList()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // data? check for null value and return valid value if not null
        // get(position)? same as before
        // let function is kind of a THEN in an if(!=null) check
        data?.get(position)?.let { holder.bind(it) }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        //data?.size?.let { return it }
        //return 0
        return if (data != null) data!!.size else 0
    }

}
