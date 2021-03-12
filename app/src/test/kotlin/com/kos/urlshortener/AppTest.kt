package com.kos.urlshortener

import com.kos.urlshortener.shortener.FakeUrlCreator
import com.kos.urlshortener.store.FakeStore
import org.junit.Assert.*
import org.junit.Test
import java.net.URI
internal class AppTest {

	@Test
	fun putUrlWithKeyword() {
		val store = FakeStore()
		val creator = FakeUrlCreator()
		val app = App(creator, store)

		creator.resultUri = URI("http://a.com")
		store.putResult = true
		assertEquals(URI("http://a.com"),  app.putUrl(URI("https://test.test"),"keyword"))
		assertEquals(1, store.putCounter)


		store.putResult = false
		assertEquals( null,  app.putUrl(URI("https://test.test"),"keyword"))
		assertEquals(2, store.putCounter)
	}

	@Test
	fun putUrlRandom() {

		val store = FakeStore()
		val creator = FakeUrlCreator()
		val app = App(creator, store)

		creator.resultUri = URI("http://a.com")
		store.putResult = true
		assertEquals(URI("http://a.com"),  app.putUrl(URI("https://test.test")))
		assertEquals(1, store.putCounter)

		store.putCounter = 0
		store.putResult = false
		assertEquals( null,  app.putUrl(URI("https://test.test")))
		assertEquals(App.MAX_RANDOM_RETRY+1, store.putCounter)
	}

	@Test
	fun getUrl() {
		val store = FakeStore()
		val creator = FakeUrlCreator()
		val app = App(creator, store)

		store.getResult = URI("http:/b.by")
		assertEquals(URI("http:/b.by"), app.getUrl(URI("h")))

		store.getResult = null
		assertEquals(null, app.getUrl(URI("h")))
	}

	@Test
	fun removeUrl() {
		val store = FakeStore()
		val creator = FakeUrlCreator()
		val app = App(creator, store)

		store.removeResult = true
		assertEquals(true, app.removeUrl(URI("https://test.test")))

		store.removeResult = false
		assertEquals(false, app.removeUrl(URI("https://test.test")))
	}
}