package squaredem.com.kotlintodolist

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.view.*
import squaredem.com.kotlintodolist.adapter.Adapter
import squaredem.com.kotlintodolist.database.RealmDb
import squaredem.com.kotlintodolist.database.RealmDb.realm
import squaredem.com.kotlintodolist.model.ToDoItem
import java.util.*

class MainActivity : AppCompatActivity() {

    private var TAG = "MainActivity"

    private var results: RealmResults<ToDoItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Kotlin ToDo Sample with Realm"

        recycler.layoutManager = LinearLayoutManager(this)
        val adapter = Adapter(this)
        recycler.adapter = adapter

        results = realm.where(ToDoItem::class.java).findAll()

        // Important, SET DATA to the adapter and notify it
        adapter.data = results
        adapter.notifyDataSetChanged()
        results?.addChangeListener { t: List<ToDoItem>? ->
            adapter.data = t
            adapter.notifyDataSetChanged()
        }

        val calendar = Calendar.getInstance()

        val dateListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }

        date_picker.setOnClickListener {
            DatePickerDialog(
                    this,
                    dateListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        button.setOnClickListener({

            // check input (e.g. anything >= 4)
            if (input.text.length < 4) {

            } else {

                // create item on the fly, whynot
                val toDoItem = ToDoItem()
                toDoItem.uuid = RealmDb.getUuid()
                toDoItem.content = input.text.toString()
                toDoItem.date = calendar.timeInMillis

                // insert or update, we should check for success
                RealmDb.insertOrUpdate(toDoItem)

                // reset input
                input.text.clear()

                // reset calendar
                resetCalendar(calendar)

            }

        })

        val callback = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                //TODO implement undo action
                /*val snack = Snackbar.make(root, "Item deleted", Snackbar.LENGTH_LONG)
                snack.setAction("UNDO", {})
                snack.addCallback(Snackbar.Callback())*/

                RealmDb.delete(viewHolder.itemView.uuid.text.toString())
                adapter.notifyItemRemoved(viewHolder.adapterPosition)

            }

        }

        ItemTouchHelper(callback).attachToRecyclerView(recycler)

    }

    private fun resetCalendar(calendar: Calendar) {
        calendar.set(
                calendar.getActualMinimum(Calendar.YEAR),
                calendar.getActualMinimum(Calendar.MONTH),
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH)
        )
    }

}
