package com.jefisu.contacts.features_contacts.data

import com.jefisu.contacts.features_contacts.domain.model.Contact

fun ContactEntity.toContact(): Contact {
    return Contact(
        name = name,
        phone = phone,
        email = email,
        isFavorite = isFavorite,
        id = id
    )
}
fun Contact.toContactEntity(): ContactEntity {
    return ContactEntity(
        name = name,
        phone = phone,
        email = email,
        isFavorite = isFavorite,
        id = id
    )
}