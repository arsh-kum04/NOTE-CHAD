package com.example.note_chad

import DB.Note
import DB.NotesDBHelper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.note_chad.databinding.FragmentUpdateNoteBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class UpdateNoteFragment : Fragment() {
    private var _binding: FragmentUpdateNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: NotesDBHelper
    private var noteid: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = NotesDBHelper(requireContext())

        noteid = requireArguments().getInt("note_id", -1)
        if (noteid == -1) {
            parentFragmentManager.beginTransaction().remove(this).commit()
            return
        }
        val datenow = Calendar.getInstance().time
        val sdf = SimpleDateFormat("EEE h:mm a", Locale.getDefault())
        val formattedDate = sdf.format(datenow)

        binding.noteTimingTv.text = formattedDate.toUpperCase(Locale.getDefault())

        val note = db.getNoteByID(noteid)
        binding.updatetitletv.setText(note.title)
        binding.updatecontentEditText.setText(note.content)

        binding.updatesaveIv.setOnClickListener {
            val newTitle = binding.updatetitletv.text.toString()
            val newContent = binding.updatecontentEditText.text.toString()
            db.updateNotes(Note(noteid, newTitle, newContent))
            Toast.makeText(requireContext(), "Changes Saved Successfully", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
        binding.updateToolbar.setNavigationOnClickListener(){
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
