package com.example.note_chad

import Adapter.NotesAdapter
import DB.NotesDBHelper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.note_chad.databinding.FragmentNotesRVBinding

class NotesRVFragment : Fragment() {
    private var _binding: FragmentNotesRVBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: NotesDBHelper
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesRVBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = NotesDBHelper(requireContext())
        notesAdapter = NotesAdapter(db.getAllNotes(), requireActivity() as FragmentActivity)
        binding.rv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter = notesAdapter
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_notes_r_v_to_fragment_notes)
        }

    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
