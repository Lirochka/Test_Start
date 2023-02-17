package com.example.test_start.parsing_XML

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class XmlPullParserHandler {
    private val elements = ArrayList<Element>()
    private var element: Element? = null
    private var text: String? = null

    fun parse(inputStream: InputStream): ArrayList<Element> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            while(eventType != XmlPullParser.END_DOCUMENT) {
                val tagname = parser.name
                when(eventType) {
                    XmlPullParser.START_TAG -> if(tagname.equals("element", ignoreCase = true)) {
                        element = Element()
                    }
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> if(tagname.equals("element", ignoreCase = true)) {
                        element?.let { elements.add(it) }
                    }  else if(tagname.equals("date", ignoreCase = true)) {
                        element!!.date = text
                    } else if(tagname.equals("id", ignoreCase = true)) {
                        element!!.id = Integer.parseInt(text)
                    } else if(tagname.equals("description", ignoreCase = true)) {
                        element!!.description = text
                    } else if(tagname.equals("keywords", ignoreCase = true)) {
                        element!!.keywords = text
                    }else if(tagname.equals("description", ignoreCase = true)) {
                        element!!.description = text
                    }

                    else -> {
                    }
                }
                eventType = parser.next()
            }

        } catch(e: XmlPullParserException) {
            e.printStackTrace()
        } catch(e: IOException) {
            e.printStackTrace()
        }
        return elements
    }
}