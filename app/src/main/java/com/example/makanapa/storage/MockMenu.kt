package com.example.makanapa.storage

import com.example.makanapa.model.Menu

class MockMenu {
    var menuObjectArr = listOf(
        Menu("NASI GORENG",
            "Sebuah masakan berupa nasi yang digoreng dan diaduk dalam minyak goreng, margarin atau mentega, dan ditambahkan kecap mania, bawang merah, bawang putih, dan bumbu lainnya.",
            "NASI INDONESIA",
            "NASI\n" +
                    "MENTEGA\n" +
                    "BAWANG MERAH\n" +
                    "BAWANG PUTIH\n" +
                    "KECAP\n" +
                    "TELUR\n" +
                    "... ",
            "1. Panaskan wajan yang berisikan mentega atau margarin\n" +
                    "2. Masak bawang merah dan bawang putih\n" +
                    "3. ..." ,
            "Racun, Lingsir Wengi"),
        Menu("BAKSO",
            "Merupakan olahan makanan yang terbuat dari campuran daging sapi/ayam/ikan dengan tepung tapioka yang dibentuk bola-bola. Bakso sering disajikan dengan mie.",
            "DAGING INDONESIA BERKUAH",
            "DAGING SAPI/AYAM/IKAN\n" +
                    "MIE KUNING\n" +
                    "BAWANG MERAH\n" +
                    "....",
            "1. Campurkan tepung tapioka dengan daging\n" +
                    "2. Bentuk menjadi bola-bola kecil\n" +
                    "3. ...",
            "Bakso Pak Doyok, Bakso Malang")
    )
}