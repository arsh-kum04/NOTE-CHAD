package com.example.note_chad

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.note_chad.databinding.FragmentNotesBinding
import com.example.note_chad.roomdb.NotesEntity
import com.example.note_chad.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment() {
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private val notesViewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.notesaveIv.setOnClickListener {
            val title = binding.titleTv.text.toString()
            val content = binding.contentEditText.text.toString()

            if(title.length<0 && content.length<0)
                Toast.makeText(requireContext(), "Empty Title or Content", Toast.LENGTH_SHORT).show()
            else
            {
                val note = NotesEntity(0, title, content,System.currentTimeMillis())
                notesViewModel.insert(note)
                Toast.makeText(requireContext(), "Note ADDED", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
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
