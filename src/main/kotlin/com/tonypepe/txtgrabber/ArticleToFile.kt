package com.tonypepe.txtgrabber

import java.io.File

class ArticleToFile(val path: String) {
    fun toFile(index: Int, message: Map<String, String>) {
        val title = message[Grabber.TITLE]
        val article = "$title\n\n${message[Grabber.ARTICLE]}"
        val file = File(".\\$path\\$index. $title.txt").absoluteFile
        file.parentFile.mkdirs()
        file.createNewFile()
        val writer = file.bufferedWriter()
        writer.apply {
            write(article)
            flush()
            close()
        }
    }
}