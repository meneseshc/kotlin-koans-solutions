package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {

  operator fun plus(interval: TimeInterval): MyDate {
    println("plus: $interval")
    return this.addTimeIntervals(interval, 1)
  }

  operator fun plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate {
    println("plus: $repeatedTimeInterval")
    return this.addTimeIntervals(repeatedTimeInterval.ti, repeatedTimeInterval.n)
  }

  override fun compareTo(other: MyDate): Int {
    return when {
      equalTo(other) -> 0
      afterTo(other) -> 1
      else -> -1
    }
  }

  private fun afterTo(other: MyDate): Boolean {
    return if (this.year > other.year)
      true
    else if (this.year == other.year && this.month > other.month)
      true
    else
      (this.year == other.year && this.month == other.month && this.dayOfMonth > other.dayOfMonth)
  }

  private fun equalTo(other: MyDate) =
    this.year == other.year && this.month == other.month && this.dayOfMonth == other.dayOfMonth
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
  DAY,
  WEEK,
  YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {

  override fun iterator(): Iterator<MyDate> {
    return MyDateIterator(start, endInclusive)
  }
}

class MyDateIterator(val start: MyDate, val endInclusive: MyDate) : Iterator<MyDate> {
  private var currentDate: MyDate = start

  override fun next(): MyDate {
    val result = currentDate
    currentDate = currentDate.nextDay()
    return result
  }

  override fun hasNext(): Boolean {
    return (currentDate <= endInclusive)
  }
}

operator fun TimeInterval.times(n: Int): RepeatedTimeInterval = RepeatedTimeInterval(this, n)

class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)
