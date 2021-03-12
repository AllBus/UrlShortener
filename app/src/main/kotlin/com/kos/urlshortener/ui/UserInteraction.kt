package com.kos.urlshortener.ui

import com.google.inject.Inject
import com.kos.urlshortener.core.IApp
import com.kos.urlshortener.core.reader.IPrintReader
import java.net.URI

class UserInteraction @Inject constructor(
	private val app: IApp,
	private val reader: IPrintReader
) {

	fun start() {
		while (true) {
			printHelpText(HELP_TEXT)
			when (readLine()) {
				"1" -> inputKeyword()
				"2" -> getUrl()
				"*" -> return
				null -> return
			}
		}
	}

	private fun getUrl() {
		printHelpText(HELP_GET_URL)
		val shortUrl = readLine()

		var exception: Exception? = null
		val outUrl =
			try {
				shortUrl?.let {
					app.getUrl(URI(it))
				}
			} catch (e: Exception) {
				exception = e
				null
			}

		if (outUrl != null) {
			println(outUrl.toString())
		} else {
			println(ERROR_URL_NOT_FOUND)
			exception?.also {
				println(it.message)
			}
		}
	}

	private fun inputKeyword() {
		printHelpText(HELP_KEYWORD_URL)
		val originalUrl = readLine()
		printHelpText(HELP_KEYWORD)
		val keyword = readLine()

		var exception: Exception? = null
		val result =
			try {
				originalUrl?.let {
					if (keyword.isNullOrEmpty())
						app.putUrl(URI(it))
					else
						app.putUrl(URI(it), keyword)
				}
			} catch (e: Exception) {
				exception = e
				null
			}

		if (result != null) {
			println(result.toString())
		} else {
			println(ERROR_CREATE_KEYWORD_URL)
			exception?.also {
				println(it.message)
			}
		}
	}

	private fun printHelpText(text: List<String>) {
		reader.print(text.joinToString("\n"))
	}

	private fun println(text:String?){
		reader.println(text)
	}

	private fun readLine():String? = reader.readLine()

	companion object {
		val HELP_TEXT = listOf(
			"",
			"Select command:",
			"1. Shortening of original URL;",
			"2. Retrieve original URL by shorten URL;",
			"*. For exit",
			"> "
		)

		val HELP_GET_URL = listOf(
			"Retrieve original URL by shorten URL",
			"Input: "
		)

		val HELP_KEYWORD_URL = listOf(
			"Shortening of original URL",
			"Original URL: "
		)

		val HELP_KEYWORD = listOf("Keyword: ")

		const val ERROR_URL_NOT_FOUND = "Url not found"

		const val ERROR_CREATE_KEYWORD_URL = "Failed to create Url"

	}
}