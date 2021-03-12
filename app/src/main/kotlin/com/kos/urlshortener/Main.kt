package com.kos.urlshortener

import com.google.inject.Guice
import com.google.inject.Injector
import com.kos.urlshortener.di.CommonModule
import com.kos.urlshortener.ui.UserInteraction

fun main(args: Array<String>) {

	val injector: Injector = Guice.createInjector(CommonModule())

	val ui = injector.getInstance(UserInteraction::class.java)
	ui.start()

}