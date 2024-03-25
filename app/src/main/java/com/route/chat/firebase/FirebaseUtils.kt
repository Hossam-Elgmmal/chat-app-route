package com.route.chat.firebase

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.route.chat.model.ChatUser


object FirebaseUtils {

    fun addUser(
        user: ChatUser,
        onSuccessListener: OnSuccessListener<Void>,
        onFailureListener: OnFailureListener
    ) {

        Firebase.firestore.collection(ChatUser.COLLECTION_NAME)
            .document(user.uid!!)
            .set(user)
            .addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)
    }

    fun getUser(
        uid: String,
        onSuccessListener: OnSuccessListener<DocumentSnapshot>,
        onFailureListener: OnFailureListener
    ) {

        Firebase.firestore.collection(ChatUser.COLLECTION_NAME)
            .document(uid)
            .get()
            .addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)
    }

}