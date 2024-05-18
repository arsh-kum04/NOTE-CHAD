package Adapter

import DB.Note
import DB.NotesDBHelper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.note_chad.R

class NotesAdapter(private var notes: List<Note>, private val listener: FragmentActivity) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    private var db:NotesDBHelper= NotesDBHelper(listener)
    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextview)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val deleteButton:ImageView=itemView.findViewById(R.id.deleteButton)
        val updateNote:LinearLayout=itemView.findViewById(R.id.note_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content

        holder.updateNote.setOnClickListener {
            val actionId = R.id.action_fragment_notes_r_v_to_noteUpdateFragment
            val bundle = Bundle().apply {
                putInt("note_id", note.id)
            }
            listener.findNavController(R.id.nav_host_fragment).navigate(actionId, bundle)
        }
        holder.deleteButton.setOnClickListener{
            db.deleteNote(note.id)
            refreshData(db.getAllNotes())
            Toast.makeText(listener,"Note Successfully Deleted",Toast.LENGTH_SHORT).show()

        }
    }

    fun refreshData(newNote: List<Note>?) {
        if (newNote != null) {
            notes = newNote
            notifyDataSetChanged()
        }
    }
}
