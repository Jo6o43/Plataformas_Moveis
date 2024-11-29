package com.example.shoppinglist.models

class Item(
    var docId: String?,
    var name: String?,
    var qtd: Double?,
) {

    constructor() : this(null, null, null)
}