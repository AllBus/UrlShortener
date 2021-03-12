package com.kos.urlshortener.store

import org.junit.Assert.*
import org.junit.Test
import java.net.URI


class SimpleStoreTest {

	@Test
	fun putUrl() {
		val store = SimpleStore()

		assertTrue(store.putUrl(URI(""), URI("https://test.com")))
		assertTrue(store.putUrl(URI("KEY"), URI("https://test.com")))
		assertTrue(store.putUrl(URI("https://short.ru/KEY"), URI("https://test.com/validtext")))
		assertFalse(store.putUrl(URI("https://short.ru/KEY"), URI("https://test.com/invalid")))
		assertTrue(
			store.putUrl(
				URI("https://wwww.video.com/detective?name=Sherlok"),
				URI("https://wwww.video.com/detective?name=Sherlok")
			)
		)
		assertFalse(store.putUrl(URI("KEY"), URI("other")))
		assertTrue(store.putUrl(URI("https://test.com"), URI("")))
	}

	@Test
	fun getUrl() {
		val store = SimpleStore()

		assertEquals(null, store.getUrl(URI("any")))

		store.putUrl(URI(""), URI("https://wwww.video.com/detective?name=Sherlok"))
		store.putUrl(URI("KEY/test"), URI("https://test.com"))
		store.putUrl(URI("any"), URI("control"))

		assertEquals(URI("control"), store.getUrl(URI("any")))
		assertEquals(URI("https://test.com"), store.getUrl(URI("KEY/test")))
		assertEquals(URI("https://test.com"), store.getUrl(URI("KEY/test")))
		assertEquals(URI("https://wwww.video.com/detective?name=Sherlok"), store.getUrl(URI("")))
	}

	@Test
	fun removeUrl() {
		val store = SimpleStore()

		assertFalse(store.removeUrl(URI("KEY/test")))
		assertFalse(store.removeUrl(URI("")))

		store.putUrl(URI(""), URI("https://wwww.video.com/detective?name=Sherlok"))
		store.putUrl(URI("KEY/test"), URI("https://test.com"))

		assertTrue(store.removeUrl(URI("KEY/test")))
		assertFalse(store.removeUrl(URI("KEY/test")))

		store.putUrl(URI("any"), URI("control"))
		store.putUrl(URI("https://short.ru/KEY"), URI("https://test.com/validtext"))

		assertFalse(store.removeUrl(URI("control")))
		assertTrue(store.removeUrl(URI("")))
		assertTrue(store.removeUrl(URI("any")))

		assertNull(store.getUrl(URI("KEY/test")))
		assertNull(store.getUrl(URI("any")))
		assertEquals(URI("https://test.com/validtext"), store.getUrl(URI("https://short.ru/KEY")))

		assertTrue(store.removeUrl(URI("https://short.ru/KEY")))
		assertNull(store.getUrl(URI("https://short.ru/KEY")))
		assertFalse(store.removeUrl(URI("https://short.ru/KEY")))
	}
}
