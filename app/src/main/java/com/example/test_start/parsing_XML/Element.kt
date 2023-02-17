package com.example.test_start.parsing_XML

class Element {
    var date: String? = null
    var description: String? = null
    var id: Int = 0
    var keywords: String? = null
    var title: String? = null

    override fun toString(): String {
        return "date = $date\n Description = $description\n Id = $id\n " +
                "Keywords = $keywords\n Title = $title"
    }
}