package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        if (this.year - other.year != 0)
            return this.year - other.year
        else if (this.month - other.month != 0)
            return this.month - other.month
        return this.dayOfMonth - other.dayOfMonth
    }
}

// Operator for the "date1..date2" range syntax
operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate)

// Operator for the "date in DateRange(date1, date2)" condition syntax
operator fun DateRange.contains(date: MyDate): Boolean = date >= start && date <= endInclusive

// Operator for the "for date in DateRange(date1, date2)" loop syntax
operator fun DateRange.iterator() = object: Iterator<MyDate> {
    var current: MyDate = this@iterator.start
    override fun next(): MyDate {
        val result = current
        current = current.nextDay()
        return result
    }
    override fun hasNext(): Boolean =  current in this@iterator
}
// NOTE to self: Use RepeatedTimeInterval
class MyTimeInterval(val days: Int, val weeks: Int, val years: Int)
operator fun MyTimeInterval.plus(other: MyTimeInterval): MyTimeInterval {
    return MyTimeInterval(this.days + other.days, this.weeks + other.weeks, this.years + other.years)
}

// Operator for adding time intervals to the date
operator fun MyDate.plus(interval: MyTimeInterval): MyDate {
    return this
            .addTimeIntervals(TimeInterval.YEAR, interval.years)
            .addTimeIntervals(TimeInterval.WEEK, interval.weeks)
            .addTimeIntervals(TimeInterval.DAY, interval.days)
}