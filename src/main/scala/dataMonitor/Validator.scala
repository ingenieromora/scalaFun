package dataMonitor
import scala.util.{Failure, Success, Try}
import org.apache.spark.sql.hive.HiveContext

/**
  * Created by leandro.mora on 12/28/2016.
  */
trait Validator {
  def validate(state: State): State
  val name: String
}
trait HiveValidator extends Validator {

  def validate(state: State): State;

}

case class VerifyIfTableExists(hiveContext: HiveContext, databaseName: String, tableName:String) extends HiveValidator{

  val name = "Check if" + databaseName + "."+ tableName + " table exist"

  override def validate(state: State): State ={
    val queryString: String =   "SELECT 1 FROM "+databaseName+"."+tableName+" LIMIT 1"
    Try(hiveContext.sql(queryString)) match {
      case Success(_) => Green()
      case Failure(_) => Red("There was a problem while validating if the table "+ databaseName +"." +tableName+" exists")
    }
  }

}

case class DummyValueValidator(value:Int,expectedValue: Int) extends Validator{

  val name = "Check if this value: " + value + " is equal to this value: "+ expectedValue

  override def validate(state: State): State = {
    if(value == expectedValue) Green() else Red("ERROR -" + value + " is not equal to: "+expectedValue)
  }

}

