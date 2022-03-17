package cinema

import java.util.Locale

const val FIRST_HALF_PRICE = 10
const val SECOND_HALF_PRICE = 8

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val sits = readln().toInt()
    val cinemaHall: MutableList<MutableList<String>> = createSits(rows, sits)

    while(true) {
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        when (readln().toInt()) {
            1 -> showCinemaHall(cinemaHall)
            2 -> bookSit(cinemaHall, rows, sits)
            3 -> showStats(cinemaHall, rows, sits)
            else -> break
        }
    }
}

fun createSits(rows: Int, sits: Int): MutableList<MutableList<String>> {
    val cinemaHall: MutableList<MutableList<String>> = mutableListOf()
    for (i in 0 until rows + 1) {
        cinemaHall.add(mutableListOf())
        for (j in 0 until sits + 1) {
            var sitChar = " "
            when {
                i == 0 && j == 0 -> sitChar = " "
                i == 0 -> sitChar = j.toString()
                j == 0 -> sitChar = i.toString()
                else -> sitChar = "S"
            }
            cinemaHall[i].add(sitChar)
        }
    }
    return cinemaHall
}

fun showCinemaHall(cinemaHall: MutableList<MutableList<String>>): Unit {
    println("Cinema:")
    for (row in cinemaHall) {
        println(row.joinToString(" "))
    }
}

fun calculateIncome(rows: Int, sits: Int): Int {
    var totalPrice = when {
        rows * sits <= 60 -> rows * sits * FIRST_HALF_PRICE
        else -> (rows/ 2) * sits * FIRST_HALF_PRICE + (rows - (rows / 2)) * sits * SECOND_HALF_PRICE
    }
    return totalPrice
}

fun isSitTaken(cinemaHall: MutableList<MutableList<String>>, row: Int, sit: Int): Boolean {
    return cinemaHall[row][sit] == "B"
}

fun calculateTicketPrice(rows: Int, sits: Int, row: Int): Int {
    return when  {
        rows * sits <= 60 -> FIRST_HALF_PRICE
        row in rows / 2 downTo 1 -> FIRST_HALF_PRICE
        else -> SECOND_HALF_PRICE
    }
}

fun bookSit(cinemaHall: MutableList<MutableList<String>>, rows: Int, sits: Int): MutableList<MutableList<String>> {
    do {
        var ticketBooked = false
        println("Enter a row number:")
        val row = readln().toInt()
        println("Enter a seat number in that row:")
        val sit = readln().toInt()
        try {
            if (!isSitTaken(cinemaHall, row, sit)) {
                cinemaHall[row][sit] = "B"
                ticketBooked = true
                println("Ticket price: $${calculateTicketPrice(rows, sits, row)}")
            } else {
                println("That ticket has already been purchased!")
            }
        } catch (e: IndexOutOfBoundsException) {
            println("Wrong input!")
        }
    } while (!ticketBooked)
    return cinemaHall
}

fun showStats(cinemaHall: MutableList<MutableList<String>>, rows: Int, sits: Int): Unit {
    val bookedSitsCount = countBookedSits(cinemaHall)
    println("Number of purchased tickets: $bookedSitsCount")
    println("Percentage: ${"%.2f".format(Locale("en", "US"), calculateBookedSitsPercentage(bookedSitsCount, rows, sits))}%")
    println("Current income: $${calculateCurrentIncome(cinemaHall, rows, sits)}")
    println("Total income: $${calculateIncome(rows, sits)}")
}

fun countBookedSits(cinemaHall: MutableList<MutableList<String>>): Int {
    var countBookedSits = 0
    for (row in cinemaHall) countBookedSits += row.count { it == "B" }
    return countBookedSits
}

fun calculateBookedSitsPercentage(bookedSitsCount: Int, rows: Int, sits: Int): Double {
    val allSits = rows * sits
    return (bookedSitsCount.toDouble() / allSits) * 100
}

fun calculateCurrentIncome(cinemaHall: MutableList<MutableList<String>>, rows: Int, sits: Int): Int {
    var income = 0
    for (index in cinemaHall.indices)  income += cinemaHall[index].count { it == "B" } * calculateTicketPrice(rows, sits, index)
    return income
}