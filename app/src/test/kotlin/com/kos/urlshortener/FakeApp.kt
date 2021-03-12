package com.kos.urlshortener

import com.kos.urlshortener.core.IApp
import java.lang.Exception
import java.net.URI

class FakeApp : IApp {

	var putResult: URI? = URI("")
	var getResult: URI? = URI("")
	var removeResult: Boolean = true
	var throwable: Exception? = null

	override fun getUrl(shortUrl: URI): URI? {
		return throwable?.let {
			throw it
		} ?: getResult

	}

	override fun putUrl(url: URI): URI? {
		return throwable?.let {
			throw it
		} ?: putResult
	}

	override fun putUrl(url: URI, keyword: String): URI? {
		return throwable?.let {
			throw it
		} ?: putResult
	}

	override fun removeUrl(shortUrl: URI): Boolean {
		return throwable?.let {
			throw it
		} ?: removeResult
	}
}