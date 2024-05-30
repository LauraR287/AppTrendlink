package com.lrincon.trendlink

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

object FirebaseHelperCrear {
    private val storage: FirebaseStorage by lazy { FirebaseStorage.getInstance() }
    private val storageRef: StorageReference by lazy { storage.reference.child("creaciones") }
    fun guardarCreacion(fileUri: Uri) {
        val fileRef = storageRef.child("${UUID.randomUUID()}.jpg")
        fileRef.putFile(fileUri)
    }

}
