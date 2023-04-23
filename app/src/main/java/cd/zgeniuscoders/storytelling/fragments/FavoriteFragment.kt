package cd.zgeniuscoders.storytelling.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cd.zgeniuscoders.storytelling.R
import cd.zgeniuscoders.storytelling.adapters.FavoriteAdapter
import cd.zgeniuscoders.storytelling.databinding.FragmentFavoriteBinding
import cd.zgeniuscoders.storytelling.models.Favorite
import cd.zgeniuscoders.storytelling.repositories.FavoriteRepository
import com.google.firebase.firestore.ktx.toObject

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)

        val favoritesList = ArrayList<Favorite>()

        val favoriteRepository = FavoriteRepository()
        favoriteRepository.findById("").addSnapshotListener { value, error ->
            if (error != null) return@addSnapshotListener
            if (value != null) {
                favoritesList.clear()
                for (doc in value.documents) {
                    val data = doc.toObject(Favorite::class.java)
                    favoritesList.add(data!!)
                }
                binding.storyRecycler.adapter = FavoriteAdapter(requireContext(), favoritesList)
            }
        }

        return binding.root
    }

}