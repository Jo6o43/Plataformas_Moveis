package com.example.n.Models

import com.example.n.ui.parseDate
import org.json.JSONObject
import java.util.Date

class Article(

    var title: String? = null,
    var description: String? = null,
    var urlToImg: String? = null,
    var url: String? = null,
    var publishedAt: Date? = null
){
    companion object{
        fun fromJson(json: JSONObject): Article{
            return Article(
                title = json.getString("title"),
                description = json.getString("description"),
                urlToImg = json.getString("urlToImage"),
                url = json.getString("url"),
                publishedAt = json.getString("publishedAt").parseDate()
            )
        }
    }
}



