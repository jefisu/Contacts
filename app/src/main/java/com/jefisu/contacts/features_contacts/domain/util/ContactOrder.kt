package com.jefisu.contacts.features_contacts.domain.util

sealed class ContactOrder(val orderType: OrderType) {
    class Name(orderType: OrderType) : ContactOrder(orderType)
    class Date(orderType: OrderType) : ContactOrder(orderType)

    fun copy(orderType: OrderType): ContactOrder {
        return when (this) {
            is Name -> Name(orderType)
            is Date -> Date(orderType)
        }
    }
}
