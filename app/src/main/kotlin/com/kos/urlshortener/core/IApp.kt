package com.kos.urlshortener.core

import java.net.URI

interface IApp {

	fun getUrl(shortUrl: URI): URI?

	fun putUrl(url: URI): URI?

	fun putUrl(url: URI, keyword: String): URI?

	fun removeUrl(shortUrl: URI): Boolean
}