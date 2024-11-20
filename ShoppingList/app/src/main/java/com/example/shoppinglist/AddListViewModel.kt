package com.example.shoppinglist

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

data class AddListState(
    val name : String = "",
    val quantity : Int = 1,
    val check : Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)

class AddListViewModel : ViewModel(){

    var state = mutableStateOf(AddListState())
        private set

    fun onNameChange(name: String) {
        state.value = state.value.copy(name = name)
    }

    fun onQuantityChange(input: String) {
        val quantity = input.toIntOrNull() ?: 0
        state.value = state.value.copy(quantity = quantity)
    }

    fun onCheckChange(check: Boolean) {
        state.value = state.value.copy(check = check)
    }


    fun addList(){

        val db = Firebase.firestore

        val auth = Firebase.auth
        val currentUser = auth.currentUser
        val userId = currentUser?.uid

        val listItems = ListItems(
            "",
            state.value.name,
            state.value.quantity,
            state.value.check,
            arrayListOf(userId?:"")
        )

        db.collection("lists")
            .add(listItems)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}