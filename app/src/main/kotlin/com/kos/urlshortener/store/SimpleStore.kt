package com.kos.urlshortener.store

import com.google.inject.Inject
import com.google.inject.Singleton
import com.kos.urlshortener.core.store.IUrlStore
import java.net.URI
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@Singleton
class SimpleStore @Inject constructor() : IUrlStore {

	private val map: ConcurrentMap<URI, URI> = ConcurrentHashMap()

	override fun putUrl(shortUrl: URI, originalUrl: URI): Boolean {
		return map.putIfAbsent(shortUrl, originalUrl) == null
	}

	override fun getUrl(shortUrl: URI): URI? {
		return map[shortUrl]
	}

	override fun removeUrl(shortUrl: URI): Boolean {
		return map.remove(shortUrl) != null
	}
}