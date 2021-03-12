package com.kos.urlshortener.reader

import com.kos.urlshortener.core.reader.IPrintReader

class PrintReader : IPrintReader {
	override fun readLine(): String? = kotlin.io.readLine()

	override fun print(text: String?)  = kotlin.io.print(text)

	override fun println(text: String?)  = kotlin.io.println(text)
}