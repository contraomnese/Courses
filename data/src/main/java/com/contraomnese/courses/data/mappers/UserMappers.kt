package com.contraomnese.courses.data.mappers

import com.contraomnese.courses.data.storage.datastore.models.UserEntity
import com.contraomnese.courses.data.storage.datastore.models.UserType
import com.contraomnese.courses.domain.models.auth.User
import com.contraomnese.courses.domain.models.auth.tokens.AccessToken

fun UserEntity.toDomain(): User {
    return User(
        id = nsid, username = userName, fullname = fullName,
        token = if (token != null && tokenSecret != null) AccessToken(
            accessToken = token,
            accessTokenSecret = tokenSecret
        ) else null
    )
}

fun User.toStorage(): UserEntity {
    return UserEntity(
        nsid = id,
        fullName = fullname,
        userName = username,
        token = token?.accessToken ?: "",
        tokenSecret = token?.accessTokenSecret ?: "",
        type = UserType.AUTHENTICATED
    )
}