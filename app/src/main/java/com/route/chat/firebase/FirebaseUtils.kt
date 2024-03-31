package com.route.chat.firebase

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.route.chat.model.ChatUser
import com.route.chat.model.Message
import com.route.chat.model.Room


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

    fun addRoom(
        room: Room,
        onSuccessListener: OnSuccessListener<Void>,
        onFailureListener: OnFailureListener
    ) {
        val docRef = Firebase.firestore
            .collection(Room.COLLECTION_NAME)
            .document()

        room.roomId = docRef.id

        docRef.set(room)
            .addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)

    }

    fun getRooms(
        onSuccessListener: OnSuccessListener<QuerySnapshot>,
        onFailureListener: OnFailureListener
    ) {
        Firebase.firestore.collection(Room.COLLECTION_NAME)
            .get()
            .addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)

    }

    fun addMessage(
        message: Message,
        onSuccessListener: OnSuccessListener<Void>,
        onFailureListener: OnFailureListener
    ) {

        Firebase.firestore.collection(Room.COLLECTION_NAME)
            .document(message.roomId)
            .collection(Message.COLLECTION_NAME)
            .document()
            .set(message)
            .addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)

    }

    fun getMessages(
        roomId: String,
        eventListener: EventListener<QuerySnapshot>
    ) {

        Firebase.firestore.collection(Room.COLLECTION_NAME)
            .document(roomId)
            .collection(Message.COLLECTION_NAME)
            .addSnapshotListener(eventListener)

    }

}
