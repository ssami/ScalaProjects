package example

import scala.sys._
/**
  * Does not extend App, so needs a
  * def entry point
  */

trait Ord {
  def <(that: Any):Boolean
  def >(that: Any):Boolean = { !(this <= that) }
  def <=(that: Any): Boolean = {this < that || this == that}
  def >=(that: Any): Boolean = { !(this < that) }
}

class FrenchDate(y: Int, m: Int, d: Int) extends Ord {

  def year = y

  def month = m

  def day = d

  override def toString(): String = year + "-" + month + "-" + day

  override def equals(that: Any): Boolean = {
    that.isInstanceOf[FrenchDate] && {          // isinstanceof = instanceof in Java
      val o = that.asInstanceOf[FrenchDate]     // asinstanceof = cast in Java
      o.day == day && o.month == month && o.year == year
    }
  }

  def <(that: Any): Boolean = {
    if (!that.isInstanceOf[FrenchDate])     // note that isInstanceOf does not take FrenchDate as an *argument*!
      error("cannot compare " + that + " and a Date")
    val o = that.asInstanceOf[FrenchDate]
    (year < o.year) ||
      (year == o.year && (month < o.month ||
        (month == o.month && day < o.day)))
  }



}

object FrenchDate extends App {

  val f = new FrenchDate(2017, 6, 20)
  val f2 = new FrenchDate(2017, 7, 20)
  println(f2 > f)


}