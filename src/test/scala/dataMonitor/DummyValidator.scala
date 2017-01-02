package dataMonitor

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by leandro.mora on 12/29/2016.
  */
class DummyValidator extends FlatSpec with Matchers {

  "A Dummy Value" should "return green if it is equal to another" in {

    val dummyval = DummyValueValidator(1,1)

    dummyval.validate(new Green()) should be (new Green())

  }

  "A Dummy Value" should "return Red if it is not equal to another" in {

    val dummyval = DummyValueValidator(1,2)

    dummyval.validate(new Green()) should be (new Red("ERROR -" + 1 + " is not equal to: "+2))

  }

  "A DataMonitor" should "return green after two operations if both operations are valid" in {

    val dummyval1 = DummyValueValidator(1,1)
    val dummyval2 = DummyValueValidator(1,1)

    val dataMonitor = new DataMonitor("Dummy Validator")

    val monitorFunc: (Seq[Validator] => State)  = dataMonitor.monitor(new ConsoleLogger(),Green(),_)

    val outputState = monitorFunc(List(dummyval1,dummyval2) )

    outputState should be (Green())


  }

  "A DataMonitor" should "return red if the unique operations is invalid" in {

    val dummyval1 = DummyValueValidator(1,2)

    val dataMonitor = new DataMonitor("Dummy Validator")

    val monitorFunc: (Seq[Validator] => State) = dataMonitor.monitor(new ConsoleLogger(),Green(),_)

    val outputState = monitorFunc(List(dummyval1) )

    outputState should be (Red("ERROR -" + 1 + " is not equal to: "+2))


  }

  "A DataMonitor" should "return red if the first operations is invalid" in {

    val dummyval1 = DummyValueValidator(1,2)
    val dummyval2 = DummyValueValidator(1,1)

    val dataMonitor = new DataMonitor("Dummy Validator")

    val monitorFunc: (Seq[Validator] => State) = dataMonitor.monitor(new ConsoleLogger(),Green(),_)

    val outputState = monitorFunc(List(dummyval1,dummyval2) )

    outputState should be (Red("ERROR -" + 1 + " is not equal to: "+2))


  }

  "A DataMonitor" should "return red if any operation is invalid" in {

    val dummyval1 = DummyValueValidator(1,1)
    val dummyval2 = DummyValueValidator(1,2)
    val dummyval3 = DummyValueValidator(1,1)

    val dataMonitor = new DataMonitor("Dummy Validator")

    val monitorFunc: (Seq[Validator] => State) = dataMonitor.monitor(new ConsoleLogger(),Green(),_)

    val outputState = monitorFunc(List(dummyval1,dummyval2, dummyval3) )

    outputState should be (Red("ERROR -" + 1 + " is not equal to: "+2))


  }


  "A DataMonitor" should "return red if the last operation is invalid" in {

    val dummyval1 = DummyValueValidator(1,1)
    val dummyval2 = DummyValueValidator(1,1)
    val dummyval3 = DummyValueValidator(1,2)

    val dataMonitor = new DataMonitor("Dummy Validator")

    val monitorFunc: (Seq[Validator] => State) = dataMonitor.monitor(new ConsoleLogger(),Green(),_)

    val outputState = monitorFunc(List(dummyval1,dummyval2, dummyval3) )

    outputState should be (Red("ERROR -" + 1 + " is not equal to: "+2))


  }
}
