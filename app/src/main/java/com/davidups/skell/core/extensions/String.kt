package com.davidups.skell.core.extensions

fun String.Companion.empty() = ""

fun String.Companion.randomImage() = "https://i.picsum.photos/id/${(0..300).random()}/1920/1080.jpg"