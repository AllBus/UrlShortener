package com.kos.urlshortener.core.store

import java.net.URI

interface IUrlStore {

	/**
	 * put url to store
	 * @return true if success, false if shortUrl is exists in store
	 */
	fun putUrl(shortUrl: URI, originalUrl: URI):Boolean

	/**
	 * get original url for shortUrl
	 * @return original url or null if not exists
	 */
	fun getUrl(shortUrl:URI):URI?

	/**
	 * remove url from store
	 * @return true if shortUrl contains in store
	 */
	fun removeUrl(shortUrl:URI):Boolean

}