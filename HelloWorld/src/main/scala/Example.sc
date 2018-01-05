val stringList: List[String] = List[Nothing]()  // works

val stringList2: List[String] = Nil             // Nil = List[Nothing]

//def somethingFunc(input: String): Nothing = {return "stuff"}  // expression of String =/= Nothing!
//def somethingFunc2(input: String): Nothing = {println("Stuff")}   // expression of type Unit =/= Nothing!

val isEven: Int => Boolean = (i: Int) => i%2 == 0   // define function with return type
val isEqual = (i: Int, k: Int) => i == k
val thing:String = "hello"

// but what about def vs. val? seems to be no difference here
val someList = List.range(1, 10)
val modListVal: Int => Boolean = (i: Int) => i % 2 == 0
def modListDef(i: Int): Boolean = { i % 2 == 0 }
val filteredList = someList.filter(modListVal)
val filtered2 = filteredList.filter(modListDef)

val funcMap = Map (
  "isEven" -> isEven,
  "isEqual"-> isEqual
)
val isE = funcMap("isEqual")

/**
  * but note i cannot call isE(6)!
  * "object" does not take parameters
  * why is that? because these two functions
  * have different signatures
  * Watch how this changes with two functions defined below
  */

val double = (i: Int) => i * 2
val triple = (i: Int) => i * 3
/**
  * now these functions are the same
  * and can be identified as
  * Map[String, Int=>Int]
  */

val functions = Map(
  "2x" -> double,
  "3x" -> triple
)
val dub = functions("2x")
dub(2)

case class Person(firstName: String,
                  lastName: String)
val fullName: Person => String =
  (p: Person) => s"${p.firstName} ${p.lastName}"
println(fullName(Person("Sumita", "Sami")))

// note the difference when we use Unit as return type
val fullNameUnit: Person => Unit =
  (p: Person) => s"${p.firstName} ${p.lastName}"
println(fullNameUnit(Person("Sumita", "Sami")))

// creating functions from defs


def triple(i: Int): Int = i * i * i
//triple    // won't work -- missing argument list
// following REPL's suggestion...
val tripFunc = triple _   // that's triple with a space, then _
// now you can use it as a function in say a list

val newList = List.range(1, 10)
newList.map(tripFunc)
// note you can also do the following:
newList.filter(_ % 2 == 0)
newList.map(_*2)    // this works, but what about tripling?

def sayHi(callback:(String) => Unit): Unit = {
  callback("Sumi")
}

def callback(s: String): Unit = println("hello " + s)

// sayHi(callback("Sumi")) --> this won't work because you're INVOKING the function!

sayHi(callback)

// but can you partially apply a callback with a value?
// e.g. val s = callback("Sumi") _
// sayHi(s)

// instead, do:
def sayHiAgain(callback:(String) => Unit, s: String): Unit = {
  callback(s)
}

sayHiAgain(callback, "Sumita")


def executeTwoFuncs(
                   f1:(Int, Int) => Int,
                   f2:(Int, Int) => Int,
                   x: Int,
                   y: Int
                   ): Tuple2[Int, Int] = {

  Tuple2(f1(x, y), f2(x, y))
}

def f1(i: Int, k: Int): Int = i * k
def f2(i:Int, k:Int): Int = i + k
executeTwoFuncs(f1, f2, 1, 3)


/**
  * A big mapping function!
  * Takes a list of Int as input
  * and outputs a list of A(any) type
  * To do that, it applies a function
  * that takes in an Int and outputs an A type
  */
def map[A, B](f: (A) => B, list: List[A]): List[B] = {
  val mappedList = List[B]()
  for {
    x <- list
  } yield f(x)
}

def transform(i: Int): Int = i * 10

val li = List.range(1, 10)

val mappedList = map(transform, li)


/**
  * Now let's write a filter function
  */

def filter[A](f: A => Boolean, list: Seq[A]): Seq[A] = {
  for {
    x <- list if f(x)
  } yield x
}

val anotherList = List.range(1, 10)

def removeIfEven(i: Int): Boolean = i % 2 != 0

val filList = filter(removeIfEven, anotherList)

// "by value" function input
def timer[A](codeToRun:(String) =>  A) = {
  val start = System.nanoTime()
  val result = codeToRun("hello world")    // what if this needs an input?
  val end = System.nanoTime()
  (result, end-start)
}

val (result, time) = timer(println)

// "by name" function input
def timer2[A](codeToRun: => A) = {
  val start = System.nanoTime()
  val result = codeToRun    // what if this needs an input?
  val end = System.nanoTime()
  (result, end-start)
}

val (result2, time2) = timer2(println("hello world"))

// different input parameters
def sum(a: Int)(b: Int)(c: Int) = a + b + c

val summed = sum(1)(2)(3)

// think of something similar to a while loop
/**
  * var i = 0
whilst (i < 5) {
    println(i)
    i += 1
}
  */
// how is whilst defined? with 2 parameter groups
// e.g.  def whilst(f: => Boolean)(g: => Unit)

def whilst(predicate: => Boolean)(runCode: => Unit): Unit = ???

// note that if the predict were NOT a by-name parameter,
// it would immediately evaluate the expression passed in,
// e.g. whilst(0 < 5) {...}
// which evaluates to "true" and so runs forever

// this means we can write a control structure where you
// always define the last group of parameters as a block of code
// taken in as a by-name parameter


def ifBothTrue(condition1: => Boolean)
              (condition2: => Boolean)
              (executeThis: => Unit): Unit = {
 if (condition1 && condition2) executeThis
}


// how to create a control structure that closes an open
// file handle when you're done?
// you can call it e.g. using(fileHandle) {...}
// def using(file: FileHandle)(f: => Unit){...}


def printIntIfTrue(a: Int)(implicit b: Boolean) = if (b) println(a)

printIntIfTrue(5)(5<0)

// but this works too:
implicit val bool = true
printIntIfTrue(10)


// default valued parameters
def f(i: Int = 1)(k: Int = 2) = i + k

val defaultParams = f()()
val defaultOneParam = f(10)()
val defaultTwoParam = f()(5)

// here is where defaults come in:

def f2(i: Int = 1)(k: Int = i) = i+k
val defaultParam = f2()()


// creating partially applied functions
def summer(i: Int)(p: Int) = i + p
def res = summer(2)(_)    // if this were a val, the val type would be Int => Function

val actualRes = res(2)

def suffixAndPrefix(prefix: String)(content: String)(suffix: String) = {
  prefix + content + suffix
}

def wrapWithDiv = suffixAndPrefix("<div>")(_: String)("</div>")
val divedInput = wrapWithDiv("helloWorld")

def htmlTagFactory(start: String, end: String) = {
  suffixAndPrefix(start)(_:String)(end)
}

val divWrapper = htmlTagFactory("<div>", "</div>")
val preWrapper = htmlTagFactory("<pre>", "</pre>")

divWrapper("test")
preWrapper("hello")

// can also create partially applied functions from
// methods which are defined as single parameter
// groups
def suffixAndPrefix2(prefix: String, content: String,
                    suffix: String) = {
  prefix + content + suffix
}

val paf = suffixAndPrefix2("<div>", _:String, "</div>")
val divedWrap = paf("hello world!")

// look at how partially applied functions work here
// modN is passed in a curried format to
// the filter list because it will take an int
//and return a Boolean

def filter(xs: List[Int], p: Int => Boolean): List[Int] =
  if (xs.isEmpty) xs
  else if (p(xs.head)) xs.head :: filter(xs.tail, p)
  else filter(xs.tail, p)

def modN(n: Int)(x: Int) = ((x % n) == 0)

val nums = List(1, 2, 3, 4, 5, 6, 7, 8)
filter(nums, modN(2))
