package com.kos.urlshortener.shortener

import com.kos.urlshortener.core.shortener.IUrlCreator
import java.net.URI

class FakeUrlCreator : IUrlCreator {

	var resultUri : URI = URI("")

	override fun createForWord(word: String): URI = resultUri

	override fun createRandom(symbolCount: Int): URI = resultUri
}