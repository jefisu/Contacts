package com.jefisu.contacts.features_contacts.domain.util

sealed class OrderType {
    object Descending : OrderType()
    object Ascending : OrderType()
}
