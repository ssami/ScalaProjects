package example

import scala.annotation.tailrec

object RecursiveSum extends App {

//  @tailrec -- can't even use this here, throws compiler error
  def sum(list: List[Int]): Int = list match {
    case Nil => 0
    case x :: xs => x + sum(xs)
  }

}

object TailRecursiveSum extends App {

  def sum(list: List[Int]): Int = {
    sumAccumlate(list, 0)
  }

  // can put inside the parent function so it's truly private and local scoped
  @tailrec
  def sumAccumlate(list: List[Int], accumulate: Int): Int = list match {
    case Nil => accumulate
    case x :: xs => sumAccumlate(xs, x + accumulate)
  }

}
