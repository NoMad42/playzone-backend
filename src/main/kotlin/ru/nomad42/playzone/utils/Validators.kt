package ru.nomad42.playzone.utils

fun String.isValidEmail(): Boolean = this.contains('@') && this.contains('.')
