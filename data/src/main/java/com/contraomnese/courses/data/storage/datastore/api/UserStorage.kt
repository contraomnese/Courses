package com.contraomnese.courses.data.storage.datastore.api

import com.contraomnese.courses.data.storage.datastore.models.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserStorage {

    val user: Flow<UserEntity?>

    suspend fun save(user: UserEntity)

    suspend fun saveAsGuest()

    suspend fun delete()
}