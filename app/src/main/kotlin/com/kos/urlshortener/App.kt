package com.kos.urlshortener

import com.google.inject.Inject
import com.google.inject.Singleton
import com.kos.urlshortener.core.IApp
import com.kos.urlshortener.core.shortener.IUrlCreator
import com.kos.urlshortener.core.store.IUrlStore
import java.net.URI


@Singleton
class App @Inject constructor(
	private val urlCreator: IUrlCreator,
	private val store: IUrlStore
) : IApp {

	override fun putUrl(url: URI, keyword: String): URI? {
		val shortUrl = urlCreator.createForWord(keyword)
		return if (store.putUrl(shortUrl, url))
			shortUrl
		else null
	}

	override fun putUrl(url: URI): URI? {

		tailrec fun putRandom(counter: Int): URI? {
			val shortUrl = urlCreator.createRandom(RANDOM_KEYWORD_LENGTH)
			val result = store.putUrl(shortUrl, url)
			return if (!result) {
				if (counter < MAX_RANDOM_RETRY)
					putRandom(counter + 1)
				else
					null
			} else
				shortUrl
		}

		return putRandom(0)
	}

	override fun getUrl(shortUrl: URI): URI? =
		store.getUrl(shortUrl)

	override fun removeUrl(shortUrl: URI): Boolean =
		store.removeUrl(shortUrl)

	companion object {
		const val RANDOM_KEYWORD_LENGTH = 5
		const val MAX_RANDOM_RETRY = 1000


	}
}

