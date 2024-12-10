package com.example.shoppinglist.lists.items

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.example.shoppinglist.TAG
import com.example.shoppinglist.models.Item

data class ListItemsState(
    val items : List<Item> = arrayListOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ListItemsViewModel : ViewModel() {

    var state = mutableStateOf(ListItemsState())
        private set

    fun getItems(listId: String) {
        val db = Firebase.firestore

        db.collection("lists")
            .document(listId)
            .collection("items")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    state.value = state.value.copy(
                        error = error.message
                    )
                    return@addSnapshotListener
                }

                val items = arrayListOf<Item>()
                for (document in value?.documents!!) {
                    val item = document.toObject(Item::class.java)
                    item?.docId = document.id
                    items.add(item!!)
                }
                state.value = state.value.copy(
                    items = items
                )
            }
    }

    fun toggleItemChecked(listId: String, item: Item) {
        val db = Firebase.firestore

        db.collection("lists")
            .document(listId)
            .collection("items")
            .document(item.docId!!)
            .set(item)
    }

    fun addItem(listId: String, item: Item) {
        val db = Firebase.firestore
        db.collection("lists")
            .document(listId)
            .collection("items")
            .add(item)
    }

    fun deleteList(listId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val db = Firebase.firestore

        db.collection("lists")
            .document(listId)
            .collection("items")
            .get()
            .addOnSuccessListener { items ->
                val batch = db.batch()
                for (item in items.documents) {
                    batch.delete(item.reference)
                }
                batch.commit().addOnSuccessListener {
                    db.collection("lists")
                        .document(listId)
                        .delete()
                        .addOnSuccessListener {
                            onSuccess()
                        }
                        .addOnFailureListener { e ->
                            onFailure(e)
                        }
                }.addOnFailureListener { e ->
                    onFailure(e)
                }
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }
}