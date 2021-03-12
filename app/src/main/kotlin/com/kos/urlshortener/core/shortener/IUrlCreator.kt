package com.kos.urlshortener.core.shortener

import java.net.URI

interface IUrlCreator {

	fun createForWord(word:String):URI

	fun createRandom(symbolCount:Int):URI
}