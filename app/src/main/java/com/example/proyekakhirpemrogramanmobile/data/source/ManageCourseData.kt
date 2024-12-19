package com.example.proyekakhirpemrogramanmobile.data.source

import com.example.proyekakhirpemrogramanmobile.data.model.archive.ScheduleStatus

data class ClassStatus (
    var status: ScheduleStatus,
    val meet: String,
    val time: String
)

var listClassStatus = listOf(
    ClassStatus(
        status = ScheduleStatus.PRESENT,
        meet = "Pertemuan 1",
        time = "Jumat, 4 Oktober 2024"
    ),
    ClassStatus(
        status = ScheduleStatus.CANCELLED,
        meet = "Pertemuan 2",
        time = "Jumat, 11 Oktober 2024"
    ),
    ClassStatus(
        status = ScheduleStatus.UNKNOWN,
        meet = "Pertemuan 3",
        time = "Jumat, 18 Oktober 2024"
    ),
    ClassStatus(
        status = ScheduleStatus.PRESENT,
        meet = "Pertemuan 4",
        time = "Jumat, 4 Oktober 2024"
    ),
    ClassStatus(
        status = ScheduleStatus.CANCELLED,
        meet = "Pertemuan 5",
        time = "Jumat, 11 Oktober 2024"
    ),
    ClassStatus(
        status = ScheduleStatus.UNKNOWN,
        meet = "Pertemuan 6",
        time = "Jumat, 18 Oktober 2024"
    ),
    ClassStatus(
        status = ScheduleStatus.PRESENT,
        meet = "Pertemuan 7",
        time = "Jumat, 4 Oktober 2024"
    ),
    ClassStatus(
        status = ScheduleStatus.CANCELLED,
        meet = "Pertemuan 8",
        time = "Jumat, 11 Oktober 2024"
    ),
    ClassStatus(
        status = ScheduleStatus.UNKNOWN,
        meet = "Pertemuan 9",
        time = "Jumat, 18 Oktober 2024"
    ),
    ClassStatus(
        status = ScheduleStatus.PRESENT,
        meet = "Pertemuan 10",
        time = "Jumat, 4 Oktober 2024"
    ),
    ClassStatus(
        status = ScheduleStatus.CANCELLED,
        meet = "Pertemuan 11",
        time = "Jumat, 11 Oktober 2024"
    ),
    ClassStatus(
        status = ScheduleStatus.UNKNOWN,
        meet = "Pertemuan 12",
        time = "Jumat, 18 Oktober 2024"
    ),
    ClassStatus(
        status = ScheduleStatus.PRESENT,
        meet = "Pertemuan 13",
        time = "Jumat, 4 Oktober 2024"
    ),
    ClassStatus(
        status = ScheduleStatus.CANCELLED,
        meet = "Pertemuan 14",
        time = "Jumat, 11 Oktober 2024"
    ),
    ClassStatus(
        status = ScheduleStatus.UNKNOWN,
        meet = "Pertemuan 15",
        time = "Jumat, 18 Oktober 2024"
    ),
    ClassStatus(
        status = ScheduleStatus.UNKNOWN,
        meet = "Pertemuan 16",
        time = "Jumat, 18 Oktober 2024"
    ),
)

//val listClassStatus = emptyList<ClassStatus>()


data class CourseTask(
    val name: String,
    val status: Boolean,
    val time: String,
    val taskType: Boolean
)

val listTaskDetails = listOf(
    CourseTask(
        name = "Buat PPT",
        status = true,
        time = "Jumat, 04 Oktober 2024",
        taskType = true
    ),
    CourseTask(
        name = "Buat Start Up",
        status = false,
        time = "Jumat, 04 September 2024",
        taskType = false
    )
)
//val listTaskDetails = emptyList<CourseTask>()

data class CourseModul(
    val name : String
)

val listModulDetails = listOf(
    CourseModul(name="Modul 1"),
    CourseModul(name="Modul 2"),
    CourseModul(name="Bayesian Belief Network"),
)

//val listModulDetails = emptyList<CourseModul>()

data class CourseInfo(
    val informasi : String
)

val listInfoDetails = listOf(
    CourseInfo(informasi =  "Besok Libur jadi jangan ke kampus ya, wkwkwkwkwkwkwkwkwkwk"),
    CourseInfo(informasi =  "Before the story begin its just a sin"),
    CourseInfo(informasi =  "Must have stabbed her 50 fucking times i can't believe it"),
    CourseInfo(informasi =  "eat it eat it eat it eat it eat it eat it eat it eat it eat it eat it eat it eat it "),
)

//val listInfoDetails = emptyList<CourseInfo>()


