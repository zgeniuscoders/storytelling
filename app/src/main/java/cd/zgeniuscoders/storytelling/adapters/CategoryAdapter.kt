package cd.zgeniuscoders.storytelling.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import cd.zgeniuscoders.storytelling.databinding.ItemCategoryBinding
import cd.zgeniuscoders.storytelling.models.Category

class CategoryAdapter(private val context: Context, private val list: ArrayList<Category>) :
    Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(binding: ItemCategoryBinding) : ViewHolder(binding.root) {
        val binding = ItemCategoryBinding.bind(binding.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.categoryName.text = list[position].name
    }

}