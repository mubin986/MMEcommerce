package com.theappbangla.data.admin

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
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

    fun addItem(item: BaseProduct) : Task<DocumentReference> {
        return getType(item).add(item)
    }

    fun addItemByRef(item: BaseProduct) : Task<Void> {
        return getType(item).document(item.ref).set(item)
    }


    fun getLimitItems(cls: Class<out BaseProduct>, noOfItems: Long) : Task<QuerySnapshot> {
        return getType(cls.newInstance()).orderBy(ORDER_BY_TIMESTAMP).limit(noOfItems).get()
    }

    fun getType(item: BaseProduct) : CollectionReference {
        return when(item) {
            is Shoe ->
                fs.collection(NODE_SHOE)

            is Cloth ->
                fs.collection(NODE_CLOTH)

            else ->
                fs.collection(NODE_OTHER)
        }
    }

    fun test() : Task<QuerySnapshot> {
        return fs.collection(NODE_SHOE).orderBy(ORDER_BY_TIMESTAMP).limit(4).get()
    }

}