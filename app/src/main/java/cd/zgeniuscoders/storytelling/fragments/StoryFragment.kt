package cd.zgeniuscoders.storytelling.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cd.zgeniuscoders.storytelling.R
import cd.zgeniuscoders.storytelling.databinding.FragmentStoryBinding

class StoryFragment : Fragment() {
    private lateinit var binding: FragmentStoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoryBinding.inflate(layoutInflater)
        return binding.root
    }

}