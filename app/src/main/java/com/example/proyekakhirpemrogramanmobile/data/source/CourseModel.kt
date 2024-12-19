package com.example.proyekakhirpemrogramanmobile.data.source

data class CourseModel(
    val course: String
)

val listCourse = listOf(
    CourseModel(
        course = "Pemrograman Mobile",
    ),
    CourseModel(
        course = "Grafika Komputer"
    ),
    CourseModel(
        course = "Business Intelligence"
    ),
    CourseModel(
        course = "Kriptografi"
    )
)