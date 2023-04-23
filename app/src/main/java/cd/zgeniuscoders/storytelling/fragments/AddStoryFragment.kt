package cd.zgeniuscoders.storytelling.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import cd.zgeniuscoders.storytelling.R
import cd.zgeniuscoders.storytelling.databinding.FragmentAddStoryBinding
import cd.zgeniuscoders.storytelling.models.Story
import cd.zgeniuscoders.storytelling.repositories.StoryRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*

class AddStoryFragment : Fragment() {
    private lateinit var binding: FragmentAddStoryBinding
    private var coverImage: Uri? = null
    var coverImageUrl: String? = ""
    lateinit var dialog: Dialog

    private var launchGalleryActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            coverImage = it.data!!.data
            binding.coverImage.setImageURI(coverImage)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddStoryBinding.inflate(layoutInflater)

        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.progress_layout)
        dialog.setCancelable(false)

        binding.coverImage.setOnClickListener {
            val intent = Intent("android.intent.action.GET_CONTENT")
            intent.type = "image/*"
            launchGalleryActivity.launch(intent)
        }

        binding.btnAdd.setOnClickListener {
            val edtTitle = binding.edtTitle.toString()
            val edtContent = binding.edtContent.toString()

            if (edtTitle.isNotEmpty() && edtContent.isNotEmpty() && coverImage != null) {
                uploadImage()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Veuillez remplir tout les champs",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        return binding.root
    }

    private fun uploadImage() {
        dialog.show()

        val fileName = UUID.randomUUID().toString() + ".jpg"
        val refStorage = FirebaseStorage
            .getInstance()
            .reference
            .child("stories/$fileName")

        refStorage.putFile(coverImage!!)
            .addOnSuccessListener {
                it.storage.downloadUrl.addOnSuccessListener { image ->
                    coverImageUrl = image.toString()
                    saveData()
                    dialog.dismiss()
                }
            }.addOnFailureListener {
                dialog.dismiss()
                Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT).show()
            }

    }

    @SuppressLint("SimpleDateFormat")
    private fun saveData() {
        val currentDate = Calendar.getInstance().time
        val dateString = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentDate)

        val storyRepository = StoryRepository()

        val data =
            Story(
                storyRepository.getKey(),
                binding.edtTitle.text.toString(),
                binding.edtContent.text.toString(),
                coverImageUrl!!,
                dateString
            )
        storyRepository.create(data)

    }

}