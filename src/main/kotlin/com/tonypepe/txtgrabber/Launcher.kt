package com.tonypepe.txtgrabber

import com.tonypepe.txtgrabber.Grabber.Companion.TITLE

fun main(args: Array<String>) {
    println("Enter the menu number: ")
    val number = readLine()
    val grabber = Grabber()
    val allMenu = grabber.getAllChapterFromMenu(number!!.toInt())
    val toFile = ArticleToFile(number)
    allMenu.forEachIndexed { index, it ->
        val article = grabber.getArticleByTitleChapter(number, it)
        println(article[TITLE])
        toFile.toFile(index + 1, article)
    }
}
