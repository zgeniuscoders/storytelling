package cd.zgeniuscoders.storytelling

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cd.zgeniuscoders.storytelling.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        binding.progressBar.progress = 0

        supportActionBar?.hide()

        Thread {
            try {
                for (progress in 0..100) {
                    binding.progressBar.progress = progress
                    Thread.sleep(50)
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            // Lancer l'activité principale de votre application ici
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }.start()

        setContentView(binding.root)
    }
}