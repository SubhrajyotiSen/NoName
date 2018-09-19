package me.subhrajyoti.myday

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {

    companion object {
        fun makeMyDate(date: String) : MyDate {
            return MyDate(date.substring(0,2).toInt(), date.substring(3,5).toInt(), date.substring(6,10).toInt())
        }

        fun compare(date1: MyDate, date2: MyDate) = date1 < date2

    }
    override fun compareTo(other: MyDate) = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }

}

