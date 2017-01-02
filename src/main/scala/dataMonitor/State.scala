package dataMonitor

/**
  * Created by leandro.mora on 12/28/2016.
  */
trait State {

  val message: String
}

case class Green() extends State {
  val message = "The validation was completed successfully"
};
case class Red(message: String)extends State;
