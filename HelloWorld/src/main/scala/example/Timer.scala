package example

object Timer {

  def oncePerSecond(callback: () => Unit) {
    while (true) {
      callback()
      Thread.sleep(1000)
    }
  }

  def timeFlies(): Unit = {
    println("Time flies like an arrow")
  }


  def main(args: Array[String]): Unit = {
    val l = List("test", "one", "thing")
    val l2 = l.map(_.concat("!"))
    println(l2)
    oncePerSecond(timeFlies)
    // this second line will never run unless above line is commented
    oncePerSecond(() => {
      println("Time flies again in an anonymous function")
    })

  }


}
