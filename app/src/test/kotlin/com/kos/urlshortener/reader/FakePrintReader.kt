package com.kos.urlshortener.reader

import com.kos.urlshortener.core.reader.IPrintReader

class FakePrintReader : IPrintReader {

	var inputStream: Iterator<String> = listOf<String>().iterator()

	var resultText = mutableListOf<String?>()

	fun resetInput(list: List<String>) {
		inputStream = list.iterator()
	}

	override fun readLine(): String? =
		if (inputStream.hasNext())
			inputStream.next()
		else
			null


	override fun print(text: String?) {

	}

	override fun println(text: String?) {
		resultText.add(text)
	}
}