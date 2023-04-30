package cd.zgeniuscoders.storytelling.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import cd.zgeniuscoders.storytelling.StoryActivity
import cd.zgeniuscoders.storytelling.databinding.ItemBestStoryBinding
import cd.zgeniuscoders.storytelling.models.Story
import com.bumptech.glide.Glide

class BestStoryAdapter(private val context: Context, private val list: ArrayList<Story>) :
    Adapter<BestStoryAdapter.BestStoryViewHolder>() {

    inner class BestStoryViewHolder(binding: ItemBestStoryBinding) : ViewHolder(binding.root) {
        val binding = ItemBestStoryBinding.bind(binding.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestStoryViewHolder {
        val binding = ItemBestStoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return BestStoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BestStoryViewHolder, position: Int) {

        val story = list[position]

        holder.binding.title.text = story.title
        holder.binding.content.text = "${story.content.substring(0, 65)} ..."
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
    }

}