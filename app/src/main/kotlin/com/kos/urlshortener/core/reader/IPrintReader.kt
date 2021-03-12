package com.kos.urlshortener.core.reader

interface IPrintReader {

	fun readLine():String?

	fun print(text:String?)

	fun println(text:String?)
}