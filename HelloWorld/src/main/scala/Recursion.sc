val test = "String"

// recursively sum elements of a list
val li = List(1, 2, 3, 4,5)

// a quick look at case

def test(x: Int): Unit =  x match {
  case 0 => println("x is 0")
  case 5 => println("x is 5")
  case _ => println("x is not 0 or 5")
}


test(2)

def sum(list: List[Int]): Int = list match {
  case Nil => 0
  case head :: tail => head + sum(tail)

}
val res = sum (List(1, 2, 3, 4))