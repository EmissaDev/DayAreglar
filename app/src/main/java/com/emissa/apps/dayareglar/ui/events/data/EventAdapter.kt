package com.emissa.apps.dayareglar.ui.events.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emissa.apps.dayareglar.R
import com.emissa.apps.dayareglar.utils.getFullTimeStamp

class EventAdapter(
    private val eventClickListener: (Event) -> Unit,
    private var eventList: MutableList<Event> = mutableListOf()
): RecyclerView.Adapter<EventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val eventView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)

        return EventViewHolder(eventView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventList[position]
        holder.itemView.setOnClickListener {
            eventClickListener(event)
        }
        holder.bind(event)
    }

    override fun getItemCount(): Int = eventList.size

    // this updates the event list
    fun updateEventData(event: Event) {
        // add the new event at the top of the list
        eventList.add(0, event)
        // notifies the adapter that a new event was inserted
        notifyItemInserted(eventList.indexOf(event))
    }

    fun updateEventsList(events: List<Event>) {
        this.eventList.clear()
        this.eventList = events as MutableList<Event>
        notifyItemRangeChanged(0, itemCount)
    }
}

class EventViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.event_title)
    private val category: TextView = itemView.findViewById(R.id.event_description)
    private val date: TextView = itemView.findViewById(R.id.event_date)

    fun bind(event: Event) {
        title.text = event.title
        category.text = event.description
        date.text = "Happening: ${getFullTimeStamp(event.date)}"
    }
}