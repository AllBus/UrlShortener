package com.kos.urlshortener.shortener

import com.google.inject.Inject
import com.kos.urlshortener.core.shortener.IUrlCreator
import com.kos.urlshortener.di.ShortenerHost
import java.net.URI
import kotlin.random.Random

class UrlCreator @Inject constructor(
	@ShortenerHost private val host: String
) : IUrlCreator {


	override fun createForWord(word: String): URI {
		return if (!checkSymbols(word, MAX_WORD_LENGTH))
			throw InvalidCharacterException()
		else
			URI(host + word)
	}

	override fun createRandom(symbolCount: Int): URI {
		return if (!checkSymbolCount(symbolCount))
			throw IndexOutOfBoundsException("symbolCount: $symbolCount must in range 2..20")
		else
			URI(host + randomWord(symbolCount))
	}

	private fun randomWord(symbolCount: Int): String {
		val random = Random(System.currentTimeMillis())
		return (1..symbolCount)
			.map { random.nextInt(ALPHABET_SIZE) }
			.map { ALPHABET[it] }
			.joinToString("")
	}


	private fun checkSymbolCount(symbolCount: Int): Boolean =
		symbolCount in 2..20

	private fun checkSymbols(word: String, maxLength: Int): Boolean {
		if (word.length > maxLength || word.length < 1)
			return false

		return word.all(ALPHABET_FOR_WORD::contains)
	}

	companion object {
		private val ALPHABET = listOf('0'..'9', 'a'..'z', 'A'..'Z').flatten().toCharArray()
		private val ALPHABET_SIZE = ALPHABET.size
		private val ALPHABET_FOR_WORD = ALPHABET + "-._~".toCharArray()
		private const val MAX_WORD_LENGTH = 20
	}
}