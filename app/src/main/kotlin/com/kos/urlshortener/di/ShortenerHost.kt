package com.kos.urlshortener.di

import com.google.inject.BindingAnnotation

@BindingAnnotation
@Target(allowedTargets = [ AnnotationTarget.FIELD , AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION])
@Retention(AnnotationRetention.RUNTIME)
annotation class ShortenerHost {
}