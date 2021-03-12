package com.kos.urlshortener.store

import com.kos.urlshortener.core.store.IUrlStore
import java.net.URI

class FakeStore : IUrlStore {

	var putResult = false
	var getResult : URI? = URI("")
	var removeResult = false

	var putCounter = 0

	override fun putUrl(shortUrl: URI, originalUrl: URI): Boolean {
		putCounter++
		return putResult
	}

	override fun getUrl(shortUrl: URI): URI?  = getResult

	override fun removeUrl(shortUrl: URI): Boolean = removeResult
}