package com.emissa.apps.dayareglar.ui.notes.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.emissa.apps.dayareglar.databinding.ItemNoteBinding
import com.emissa.apps.dayareglar.interfaces.ClickListeners
import com.emissa.apps.dayareglar.utils.getDate

class NoteAdapter(
    private val noteClickListeners: ClickListeners,
) : ListAdapter<Note, NoteAdapter.NoteViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            noteClickListeners
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
    }


    class NoteViewHolder(
        private val binding: ItemNoteBinding,
        private val noteClicked: ClickListeners
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.apply {
                noteTitle.text = note.noteTitle
                noteContent.text = note.noteContent
                noteDate.text = getDate(note.timeStamp)

                root.setOnClickListener {
                    noteClicked.onItemClicked(note)
                }

                root.setOnLongClickListener {
                    noteClicked.onItemLongClicked(note)
                }
            }
        }
    }
}

private class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}