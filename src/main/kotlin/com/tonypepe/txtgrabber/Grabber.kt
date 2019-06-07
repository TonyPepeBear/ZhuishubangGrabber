package com.tonypepe.txtgrabber

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class Grabber {
    companion object {
        const val TITLE = "TITLE"
        const val ARTICLE = "ARTICLE"
        const val NEXT_LINE = "IWantNextLine"
    }

    fun getAllChapterFromMenu(number: Int): List<String> {
        val document = Jsoup.connect("https://www.zhuishubang.com/$number/").get()
        val chapterClass = document.getElementsByClass("chapterCon")
        val chapters = mutableListOf<String>()
        chapterClass.select("a").forEach {
            val split = it.attr("href").split("/")
            val replace = split[2].replace(".html", "")
            chapters.add(replace)
        }
        return chapters.sorted()
    }

    fun getArticleByTitleChapter(title: String, chapter: String): Map<String, String> {
        val document = Jsoup.connect("https://www.zhuishubang.com/$title/$chapter.html").get()
        val myTitle = getTitleFromDocument(document)
        val article = getArticleFromDocument(document)
        return mapOf(TITLE to myTitle, ARTICLE to article)
    }

    private fun getArticleFromDocument(document: Document): String {
        val x = document.getElementsByClass("articleCon")
        val doc = Jsoup.parse(x.html())
        doc.select("br").append(NEXT_LINE)
        val pretty = doc.outputSettings(Document.OutputSettings().prettyPrint(false))
        return pretty.text().replace(NEXT_LINE, "\n")
    }

    private fun getTitleFromDocument(document: Document): String {
        return document.getElementsByClass("articleTitle").select("h2").text()
    }
}
