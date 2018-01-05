import example.CubeCalculator
import org.scalatest.FunSuite

class CubeCalculatorTest extends FunSuite {
  test("test that cube of 3 is 27") {
    assert(CubeCalculator.cube(3) === 2)
  }
}
