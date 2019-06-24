package com.theappbangla.data.admin

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.theappbangla.data.BaseRepo
import com.theappbangla.data.model.BaseProduct
import com.theappbangla.data.model.Cloth
import com.theappbangla.data.model.Shoe
import com.theappbangla.data.model.User

class AdminRepository : BaseRepo() {
    val TAG = "ADMIN_REPOSITORY"

    fun addUser(user: User): Task<DocumentReference> {
        return userRef.add(user)
    }

    fun addItem(item: Any) : Task<Void> {
        return when(item) {
            is Shoe ->
                productRef.document(NODE_SHOE).set(item)

            is Cloth ->
                productRef.document(NODE_CLOTH).set(item)

            is BaseProduct ->
                productRef.document("tmp").set(item)

            else ->
                productRef.document("other").set(item)
        }
    }
}