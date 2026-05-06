package com.example.myapplication

import java.util.Scanner

fun main() {
    println("Merhaba bu bir denemedir....")

    println("İsmini giriniz:")

    val okuyucu= Scanner(System.`in`)
    val ad=okuyucu.next()

    println("İsminiz: $ad")
}