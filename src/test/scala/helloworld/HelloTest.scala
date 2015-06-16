package helloworld

import org.scalatest.FunSuite

/**
 * Created by leandromoras on 4/23/15.
 */
class HelloTest extends FunSuite{
  test("say Hello Method works correctly"){
    val hello = new Hello
    assert(hello.sayHello("World") == "Hello World")
  }
}
