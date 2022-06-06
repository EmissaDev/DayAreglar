package com.emissa.apps.dayareglar.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.emissa.apps.dayareglar.MainActivity
import com.emissa.apps.dayareglar.databinding.FragmentHomeBinding
import com.emissa.apps.dayareglar.ui.events.EventsListFragment
import com.emissa.apps.dayareglar.ui.notes.NotesListFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnEventList.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeNavToEventsNav()
                findNavController().navigate(action)
            }
            btnNoteList.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeNavToNotesNav()
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}