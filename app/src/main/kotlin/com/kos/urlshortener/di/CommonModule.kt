package com.kos.urlshortener.di

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.kos.urlshortener.App
import com.kos.urlshortener.core.IApp
import com.kos.urlshortener.core.reader.IPrintReader
import com.kos.urlshortener.core.shortener.IUrlCreator
import com.kos.urlshortener.core.store.IUrlStore
import com.kos.urlshortener.reader.PrintReader
import com.kos.urlshortener.shortener.UrlCreator
import com.kos.urlshortener.store.SimpleStore

class CommonModule : AbstractModule() {

	override fun configure() {
		super.configure()
		bind(IUrlStore::class.java).to(SimpleStore::class.java)
		bind(IUrlCreator::class.java).to(UrlCreator::class.java)
		bind(IPrintReader::class.java).to(PrintReader::class.java)
		bind(IApp::class.java).to(App::class.java)
	}

	@Provides
	@ShortenerHost
	fun hostUrl(): String {
		return "https://short.en/"
	}

}
