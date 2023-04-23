package cd.zgeniuscoders.storytelling.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cd.zgeniuscoders.storytelling.R
import cd.zgeniuscoders.storytelling.adapters.BestStoryAdapter
import cd.zgeniuscoders.storytelling.adapters.CategoryAdapter
import cd.zgeniuscoders.storytelling.adapters.StoryAdapter
import cd.zgeniuscoders.storytelling.databinding.FragmentHomeBinding
import cd.zgeniuscoders.storytelling.models.Category
import cd.zgeniuscoders.storytelling.models.Story
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        getBestStories()
        getCategories()
        getStories()
        return binding.root
    }

    private fun getCategories() {
        val categories = ArrayList<Category>()
        Firebase.firestore.collection("categories").addSnapshotListener { querySnap, error ->
            if (error != null) {
                return@addSnapshotListener
            }

            categories.clear()
            if (querySnap != null) {
                for (doc in querySnap.documents) {
                    val data = doc.toObject(Category::class.java)
                    categories.add(data!!)
                }
                categories.shuffle()
                binding.categoryRecycler.adapter = CategoryAdapter(requireContext(), categories)
            }
        }
    }

    private fun getStories() {
        val stories = ArrayList<Story>()
        Firebase.firestore.collection("stories").addSnapshotListener { querySnap, error ->
            if (error != null) {
                return@addSnapshotListener
            }

            stories.clear()
            if (querySnap != null) {
                for (doc in querySnap.documents) {
                    val data = doc.toObject(Story::class.java)
                    stories.add(data!!)
                }
                val lm = LinearLayoutManager(requireContext())
                binding.storyRecycler.layoutManager = lm
                binding.storyRecycler.adapter = StoryAdapter(requireContext(), stories)
            }
        }
    }

    private fun getBestStories() {
        val stories = ArrayList<Story>()
        Firebase.firestore.collection("stories").addSnapshotListener { querySnap, error ->
            if (error != null) {
                return@addSnapshotListener
            }

            stories.clear()
            if (querySnap != null) {
                for (doc in querySnap.documents) {
                    val data = doc.toObject(Story::class.java)
                    stories.add(data!!)
                }
                stories.shuffle()
                val lm = LinearLayoutManager(requireContext())
                binding.bestStoryRecycler.layoutManager = lm
                lm.orientation = RecyclerView.HORIZONTAL
                binding.bestStoryRecycler.adapter = BestStoryAdapter(requireContext(), stories)
            }
        }
    }
}