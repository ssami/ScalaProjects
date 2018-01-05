import org.scalatest.FunSuite
import org.scalacheck.Prop.forAll

class RecursiveTest extends FunSuite {

  test("assert that recursion works") {
    // need to figure out how to use ScalaCheck for methods
    val propReverseList = forAll { l: List[String] => l.reverse.reverse == l }

  }


}
