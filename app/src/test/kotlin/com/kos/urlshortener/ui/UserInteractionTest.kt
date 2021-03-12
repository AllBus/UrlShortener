package com.kos.urlshortener.ui

import com.kos.urlshortener.FakeApp
import com.kos.urlshortener.reader.FakePrintReader
import org.junit.Assert.assertArrayEquals
import org.junit.Test
import java.io.IOException
import java.net.URI

internal class UserInteractionTest {

	@Test
	fun start() {

		val app = FakeApp()
		val reader = FakePrintReader()

		val ui = UserInteraction(app, reader)

		ui.start()

		assertArrayEquals(emptyArray<String>(), reader.resultText.toTypedArray())
	}


	@Test
	fun start1() {

		val app = FakeApp()
		val reader = FakePrintReader()
		val ui = UserInteraction(app, reader)

		reader.resetInput(
			listOf(
				"1",
				"text"
			)
		)

		app.putResult = URI("tam")

		ui.start()

		assertArrayEquals(arrayOf<String>("tam"), reader.resultText.toTypedArray())
	}


	@Test
	fun start2() {

		val app = FakeApp()
		val reader = FakePrintReader()
		val ui = UserInteraction(app, reader)

		reader.resetInput(
			listOf(
				"1",
				"text"
			)
		)

		app.putResult = null

		ui.start()

		assertArrayEquals(arrayOf<String>(UserInteraction.ERROR_CREATE_KEYWORD_URL), reader.resultText.toTypedArray())
	}


	@Test
	fun start3() {

		val app = FakeApp()
		val reader = FakePrintReader()
		val ui = UserInteraction(app, reader)

		reader.resetInput(
			listOf(
				"1",
				"text"
			)
		)

		app.throwable = IOException("My message")

		ui.start()

		assertArrayEquals(
			arrayOf<String>(
				UserInteraction.ERROR_CREATE_KEYWORD_URL,
				"My message"
			), reader.resultText.toTypedArray()
		)
	}

	@Test
	fun start4() {

		val app = FakeApp()
		val reader = FakePrintReader()
		val ui = UserInteraction(app, reader)

		reader.resetInput(
			listOf(
				"1",
				"text",
				"",
				"1",
				"text"
			)
		)

		app.putResult = URI("tam")

		ui.start()

		assertArrayEquals(arrayOf<String>("tam", "tam"), reader.resultText.toTypedArray())
	}

	@Test
	fun start5() {

		val app = FakeApp()
		val reader = FakePrintReader()


		reader.resetInput(
			listOf(
				"*"
			)
		)

		val ui = UserInteraction(app, reader)

		ui.start()

		assertArrayEquals(emptyArray<String>(), reader.resultText.toTypedArray())
	}


	@Test
	fun start11() {

		val app = FakeApp()
		val reader = FakePrintReader()
		val ui = UserInteraction(app, reader)

		reader.resetInput(
			listOf(
				"1",
				"text",
				"key"
			)
		)

		app.putResult = URI("tam")

		ui.start()

		assertArrayEquals(arrayOf<String>("tam"), reader.resultText.toTypedArray())
	}


	@Test
	fun start12() {

		val app = FakeApp()
		val reader = FakePrintReader()
		val ui = UserInteraction(app, reader)

		reader.resetInput(
			listOf(
				"1",
				"text",
				"key"
			)
		)

		app.putResult = null

		ui.start()

		assertArrayEquals(arrayOf<String>(UserInteraction.ERROR_CREATE_KEYWORD_URL), reader.resultText.toTypedArray())
	}


	@Test
	fun start13() {

		val app = FakeApp()
		val reader = FakePrintReader()
		val ui = UserInteraction(app, reader)

		reader.resetInput(
			listOf(
				"1",
				"sphere",
				"model"
			)
		)

		app.throwable = IOException("My message")

		ui.start()

		assertArrayEquals(
			arrayOf<String>(
				UserInteraction.ERROR_CREATE_KEYWORD_URL,
				"My message"
			), reader.resultText.toTypedArray()
		)
	}

	@Test
	fun start14() {

		val app = FakeApp()
		val reader = FakePrintReader()
		val ui = UserInteraction(app, reader)

		reader.resetInput(
			listOf(
				"1",
				"stone",
				"word",
				"1",
				"water",
				"ball",
			)
		)

		app.putResult = URI("control")

		ui.start()

		assertArrayEquals(arrayOf<String>("control", "control"), reader.resultText.toTypedArray())
	}

	@Test
	fun start15() {

		val app = FakeApp()
		val reader = FakePrintReader()
		val ui = UserInteraction(app, reader)

		reader.resetInput(
			listOf(
				"1",
				"stone",
				"word",
				"2",
				"water"
			)
		)

		app.getResult = URI("control")
		app.putResult = URI("window")

		ui.start()

		assertArrayEquals(arrayOf<String>("window", "control"), reader.resultText.toTypedArray())
	}

	@Test
	fun start20() {

		val app = FakeApp()
		val reader = FakePrintReader()
		val ui = UserInteraction(app, reader)

		reader.resetInput(
			listOf(
				"2",
				"code"
			)
		)

		app.getResult = URI("water")

		ui.start()

		assertArrayEquals(arrayOf<String>("water"), reader.resultText.toTypedArray())
	}

	@Test
	fun start21() {

		val app = FakeApp()
		val reader = FakePrintReader()
		val ui = UserInteraction(app, reader)

		reader.resetInput(
			listOf(
				"2",
				"code",
				"2",
				"code1",
				"2",
				"code3",
				"*"
			)
		)

		app.getResult = URI("water")

		ui.start()

		assertArrayEquals(arrayOf<String>("water", "water", "water"), reader.resultText.toTypedArray())
	}

	@Test
	fun start22() {

		val app = FakeApp()
		val reader = FakePrintReader()
		val ui = UserInteraction(app, reader)

		reader.resetInput(
			listOf(
				"2",
				"word"
			)
		)

		app.throwable = Exception("error get value")

		ui.start()

		assertArrayEquals(
			arrayOf<String>(
				UserInteraction.ERROR_URL_NOT_FOUND,
				"error get value"
			), reader.resultText.toTypedArray()
		)
	}
}