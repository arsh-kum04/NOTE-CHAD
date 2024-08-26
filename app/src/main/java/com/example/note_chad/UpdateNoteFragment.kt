package com.example.note_chad

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.note_chad.databinding.FragmentUpdateNoteBinding
import com.example.note_chad.roomdb.NotesEntity
import com.example.note_chad.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateNoteFragment : Fragment() {
    private var _binding: FragmentUpdateNoteBinding? = null
    private val binding get() = _binding!!
    private val notesViewModel: NotesViewModel by viewModels()
    private var noteid:Int=-1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        noteid= arguments?.getInt("id",id)!!
        _binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (noteid == -1) {
            parentFragmentManager.beginTransaction().remove(this).commit()
            return
        }
        notesViewModel.getNoteById(noteid).observe(viewLifecycleOwner){
            note->
            note?.let{
                binding.updatetitletv.setText(it.title)
                binding.updatecontentEditText.setText(it.content)
            }
        }

        binding.updatesaveIv.setOnClickListener {
            val newTitle = binding.updatetitletv.text.toString()
            val newContent = binding.updatecontentEditText.text.toString()
            if(newTitle.isNotEmpty() and newContent.isNotEmpty()){
                val updatednote= NotesEntity(noteid,newTitle,newContent,System.currentTimeMillis())
                notesViewModel.update(updatednote)
                Toast.makeText(requireContext(), "Changes Saved Successfully", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
            else {
                Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }


        }
        binding.updateToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
