package com.kos.urlshortener.shortener

import org.junit.Assert.*
import org.junit.Test
import java.net.URI

class UrlCreatorTest {

	@Test(expected = InvalidCharacterException::class)
	fun createForWordEmpty() {
		val creator = UrlCreator("https://empty.org/")

		creator.createForWord("")
	}

	@Test(expected = InvalidCharacterException::class)
	fun createForWordLong() {
		val creator = UrlCreator("https://empty.org/")

		creator.createForWord("ABC123456789123456789DEF")
	}

	@Test(expected = InvalidCharacterException::class)
	fun createForWordLong21() {
		val creator = UrlCreator("https://empty.org/")

		creator.createForWord("C12345678901234567890")
	}

	@Test
	fun createForWordLong20() {
		val creator = UrlCreator("https://empty.org/")
		assertEquals(URI("https://empty.org/C1234567890123456789"), creator.createForWord("C1234567890123456789"))
	}

	@Test
	fun createForWord1() {
		val creator = UrlCreator("https://empty.org/")
		assertEquals(URI("https://empty.org/E"), creator.createForWord("E"))
		assertEquals(URI("https://empty.org/-"), creator.createForWord("-"))
		assertEquals(URI("https://empty.org/-._~"), creator.createForWord("-._~"))
		assertEquals(URI("https://empty.org/.~"), creator.createForWord(".~"))
		assertEquals(URI("https://empty.org/-a._U~i"), creator.createForWord("-a._U~i"))
	}

	@Test
	fun createForWords() {
		val creator = UrlCreator("https://empty.org/")
		for (i in 1..20) {
			val c = "a".repeat(i)
			assertEquals(URI("https://empty.org/$c"), creator.createForWord(c))
		}
	}

	@Test(expected = InvalidCharacterException::class)
	fun createForWordInvalid() {
		val creator = UrlCreator("https://empty.org/")

		creator.createForWord("C123456789/01234567890")
	}

	@Test(expected = InvalidCharacterException::class)
	fun createForWordInvalid2() {
		val creator = UrlCreator("https://empty.org/")

		creator.createForWord("++")
	}

	@Test(expected = InvalidCharacterException::class)
	fun createForWordInvalid3() {
		val creator = UrlCreator("https://empty.org/")

		creator.createForWord("A-_@")
	}

	@Test(expected = InvalidCharacterException::class)
	fun createForWordInvalid4() {
		val creator = UrlCreator("https://empty.org/")

		creator.createForWord("?")
	}

	@Test
	fun createRandom() {
		val creator = UrlCreator("https:///")
		val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
		for (i in 2..20) {
			val r = creator.createRandom(i).path.drop(1)
			assertEquals(i, r.length)
			assertTrue( r.all { alphabet.contains(it) })

		}
	}



	@Test(expected = IndexOutOfBoundsException::class)
	fun createRandomIndexOutOfBounds0() {
		val creator = UrlCreator("https:///")
		creator.createRandom(0)
	}

	@Test(expected = IndexOutOfBoundsException::class)
	fun createRandomIndexOutOfBounds1() {
		val creator = UrlCreator("https:///")
		creator.createRandom(-1)

	}

	@Test(expected = IndexOutOfBoundsException::class)
	fun createRandomIndexOutOfBounds2() {
		val creator = UrlCreator("https:///")
		creator.createRandom(1)
	}

	@Test(expected = IndexOutOfBoundsException::class)
	fun createRandomIndexOutOfBounds3() {
		val creator = UrlCreator("https:///")
		creator.createRandom(21)
	}

	@Test(expected = IndexOutOfBoundsException::class)
	fun createRandomIndexOutOfBounds4() {
		val creator = UrlCreator("https:///")
		creator.createRandom(2100)
	}
}