package cd.zgeniuscoders.storytelling.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import cd.zgeniuscoders.storytelling.databinding.ItemStoryBinding
import cd.zgeniuscoders.storytelling.models.Favorite
import cd.zgeniuscoders.storytelling.models.Story
import cd.zgeniuscoders.storytelling.repositories.StoryRepository
import com.bumptech.glide.Glide


class FavoriteAdapter(private val context: Context, private val list: ArrayList<Favorite>) :
    Adapter<FavoriteAdapter.StoryViewHolder>() {
    inner class StoryViewHolder(binding: ItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val binding = ItemStoryBinding.bind(binding.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = ItemStoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return StoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val favorite = list[position]
        val storyRepository = StoryRepository()

        storyRepository.findById(favorite.storyId).addSnapshotListener { value, error ->
            if (error != null) return@addSnapshotListener
            if (value != null) {
                val story = value.toObject(Story::class.java)
                holder.binding.title.text = story!!.title
                holder.binding.content.text = "${story.content.substring(0, 90)} ..."
                Glide.with(context).load(story.image).into(holder.binding.coverImage)
            }
        }


    }
}