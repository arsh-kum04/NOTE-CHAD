package com.example.note_chad

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.note_chad.Adapter.NotesAdapter
import com.example.note_chad.databinding.FragmentNotesRVBinding
import com.example.note_chad.viewmodel.AuthViewModel
import com.example.note_chad.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesRVFragment : Fragment() {
    private var _binding: FragmentNotesRVBinding? = null
    private val binding get() = _binding!!
    private lateinit var notesAdapter: NotesAdapter
    private val notesViewModel: NotesViewModel by viewModels()
    private val authViewModel :AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                authViewModel.signOut()
                requireActivity().finish()
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesRVBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesAdapter = NotesAdapter(emptyList(), requireActivity()){
            note->
            notesViewModel.delete(note)
        }
        binding.rv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter = notesAdapter

        notesViewModel.allNotes.observe(viewLifecycleOwner){
            notes -> notesAdapter.submitList(notes)
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_notes_r_v_to_fragment_notes)
        }

        findNavController().popBackStack()

    }
    

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
