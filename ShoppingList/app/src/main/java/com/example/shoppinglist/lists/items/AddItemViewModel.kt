package com.example.shoppinglist.lists.items

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.TAG
import com.example.shoppinglist.models.Item
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

data class AddItemState(
    val name: String = "",
    val quantity: Int = 1,
    val isLoading: Boolean = false,
    val error: String? = null
)

class AddItemViewModel : ViewModel() {

    var state = mutableStateOf(AddItemState())
        private set

    fun onNameChange(name: String) {
        state.value = state.value.copy(name = name)
    }

    fun onQuantityChange(quantity: Int) {
        state.value = state.value.copy(quantity = quantity)
    }

    fun addItem() {
        val db = Firebase.firestore
        val auth = Firebase.auth
        val currentUser = auth.currentUser
        val userId = currentUser?.uid

        val addItem = Item(
            "",
            state.value.name,
            state.value.quantity.toDouble()
        )

        db.collection("items")
            .add(addItem)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}
