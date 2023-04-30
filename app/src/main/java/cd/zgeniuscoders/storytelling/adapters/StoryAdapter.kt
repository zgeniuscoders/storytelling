package cd.zgeniuscoders.storytelling.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import cd.zgeniuscoders.storytelling.StoryActivity
import cd.zgeniuscoders.storytelling.databinding.ItemStoryBinding
import cd.zgeniuscoders.storytelling.models.Favorite
import cd.zgeniuscoders.storytelling.models.Story
import cd.zgeniuscoders.storytelling.repositories.FavoriteRepository
import com.bumptech.glide.Glide


class StoryAdapter(private val context: Context, private val list: ArrayList<Story>) :
    Adapter<StoryAdapter.StoryViewHolder>() {
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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {

        val story = list[position]

        holder.binding.title.text = story.title
        holder.binding.content.text = "${story.content.substring(0, 90)} ..."
        Glide.with(context).load(story.image).into(holder.binding.coverImage)

        holder.binding.itemView.setOnClickListener {
            Intent(context, StoryActivity::class.java).apply {
                this.putExtra("storyTitle", story.title)
                this.putExtra("storyContent", story.content)
                this.putExtra("storyAt", story.createdAt)
                this.putExtra("storyImage", story.image)
                context.startActivity(this)
            }
        }

        holder.binding.addFavoriteBtn.setOnClickListener {
            val favorite = Favorite("", list[position].id)
            FavoriteRepository().create("", favorite)
            Toast.makeText(
                context,
                "${story.title} a été mis dans vos histoires favorite",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}