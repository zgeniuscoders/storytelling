package cd.zgeniuscoders.storytelling.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
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

    override fun onBindViewHolder(holder: BestStoryViewHolder, position: Int) {
        holder.binding.title.text = list[position].title
        holder.binding.content.text = "${list[position].content.substring(0, 65)} ..."
        Glide.with(context).load(list[position].image).into(holder.binding.coverImage)
    }

}