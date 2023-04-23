package cd.zgeniuscoders.storytelling.repositories

import cd.zgeniuscoders.storytelling.models.Favorite
import cd.zgeniuscoders.storytelling.models.Story
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class StoryRepository {

    companion object {
        val STORY_COLLECTION = Firebase.firestore.collection("stories")
        val docKey = STORY_COLLECTION.document().get()
    }

    fun getKey(): String = docKey.toString()

    fun create(story: Story): Task<Void> {
        return STORY_COLLECTION.document(story.id).set(story)
    }

    fun all(): DocumentReference {
        return STORY_COLLECTION.document()
    }

    fun findById(storyId: String): DocumentReference {
        return STORY_COLLECTION.document(storyId)
    }

    fun update(storyId: String, story: HashMap<String, Any>): Task<Void> {
        return findById(storyId).update(story)
    }

    fun delete(storyId: String): Task<Void> {
        return findById(storyId).delete()
    }


}