package example

/**
  * The fact that classes have parameters
  * suggest that default constructor is built in.
  * How to create alternative constructors?
  *
  * all Scala classes derive ultimately from scala.AnyRef
  * if they don't inherit from another class.

    Difference between imperative - code that has some side effect,
    even if it's within some class whose behavior is meant to be described like this:
    order.calculateTaxes()
    order.updatePrices()

    And functional -- code that depends on a return value:
    val tax = calculateTax(order)
    val price = calculatePrice(order)
  */

class Complex(real: Double, imaginary: Double) {
  /**
    * From ScalaTutorial PDF, As a simple rule, beginner Scala programmers
    should try to omit type declarations which seem to be easy to deduce from the context,
    and see if the compiler agrees.
    IntelliJ complained about return value, so introduced
    ": Double"
    */

  /**
    * Note we can define functions to access the member variables...
    */

  //def re(): Double = real
  //def im(): Double = imaginary

  /**
    * But since val is read-only, we can use those as getters.
    * See ScalaKoans
    */
//  val re = real
//  val im = imaginary

  /**
    * We can also define methods without arguments so we can call
    * them in the same way as the val
    */

  def re = real
  def im = imaginary

  /**
    * Scala suggests removing the parameters if the method
    * does not actually require them, and the method
    * has no side effect
    * @return
    */
  override def toString: String = {
    "Real: " + real + "; Imaginary: " + imaginary
  }

}

object ComplexCompanion extends App {
  val c = new Complex(5.0, 1.0)
  println(c)
//  println("imaginary part: " + c.im())
  println("imaginary part: " + c.im)

}
