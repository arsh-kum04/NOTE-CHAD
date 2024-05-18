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
import com.example.note_chad.databinding.FragmentNotesBinding

class NotesFragment : Fragment() {
    private lateinit var db: NotesDBHelper
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = NotesDBHelper(requireContext())
        binding.notesaveIv.setOnClickListener {
            val title = binding.titleTv.text.toString()
            val content = binding.contentEditText.text.toString()

            if(title.length>0 || content.length>0)
            {
                val note = Note(0, title, content)
                db.insertNote(note)
                Toast.makeText(requireContext(), "Note ADDED", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
            else
            {
                Toast.makeText(requireContext(), "Empty Note", Toast.LENGTH_SHORT).show()
            }

        }
        binding.noteToolbar.setNavigationOnClickListener()
        {

            findNavController().popBackStack()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
