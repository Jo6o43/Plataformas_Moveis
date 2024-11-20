package com.example.shoppinglist

data class ListItems (
    var docId : String?,
    var name : String?,
    var quantity : Int?,
    var check : Boolean?,
    var owners : List<String>?) {

    constructor() : this(null,null,null,null,null)
}