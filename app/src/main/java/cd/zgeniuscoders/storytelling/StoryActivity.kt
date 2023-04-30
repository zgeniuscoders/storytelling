package cd.zgeniuscoders.storytelling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cd.zgeniuscoders.storytelling.databinding.ActivityStoryBinding
import com.bumptech.glide.Glide

class StoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("storyTitle")
        val content = intent.getStringExtra("storyContent")
        val createdAt = intent.getStringExtra("storyAt")
        val image = intent.getStringExtra("storyImage")

        binding.title.text = title
        binding.content.text = content
        Glide.with(this).load(image).into(binding.imageView2)
    }
}