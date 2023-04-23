package cd.zgeniuscoders.storytelling.repositories

import cd.zgeniuscoders.storytelling.models.Favorite
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FavoriteRepository {

    companion object {
        val FAVORITE_COLLECTION = Firebase.firestore.collection("favorites")
    }

    fun create(userId: String, favorite: Favorite): Task<Void> {
        return FAVORITE_COLLECTION.document(userId).collection("favorites").document().set(favorite)
    }

    fun all(): DocumentReference {
        return FAVORITE_COLLECTION.document()
    }

    fun findById(userId: String): CollectionReference {
        return FAVORITE_COLLECTION.document(userId).collection("favorites")
    }

    fun delete(userId: String, favoriteId: String): Task<Void> {
        return findById(userId).document(favoriteId).delete()
    }


}