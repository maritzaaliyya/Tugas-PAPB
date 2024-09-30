package com.tifd.prakpapb

data class Schedule(
    val id: Int,
    val kodeMK: String,
    val namaMK: String,
    val kelas: String,
    val waktu: String,
    val dosen: String
)

val dummySchedule = listOf(
    Schedule(0, "IF01", "Pemrograman Aplikasi Perangkat Bergerak", "F2.5", "Senin, 07.00-09.30", "Pak Agi"),
    Schedule(1, "IF02", "Jaringan Nirkabel", "F4.5", "Kamis, 07.00-09.30", "Pak Kasyful"),
    Schedule(2, "IF03", "Metode Penelitian dan Penulisan Ilmiah", "F2.5", "Selasa, 07.00-09.30", "Bu Candra"),
    Schedule(3, "IF04", "Rekayasa Perangkat Lunak", "F2.5", "Rabu, 07.00-09.30", "Pak Bayu"),
    Schedule(4, "IF05", "Forensik Digital", "F2.5", "Jumat, 07.00-09.30", "Pak Fariz")
)