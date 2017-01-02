package dataMonitor

import java.text.SimpleDateFormat
import java.util.Date

/**
  * Created by leandro.mora on 12/28/2016.
  */
class DataMonitor(name: String){

  def monitor[T <% Validator](monitorLogger: MonitorLogger, initialState: State, validatorLists: Seq[T]) :State = {

    validatorLists.foldLeft(initialState){ (lastState, currentValidator) => {

      val outputState = currentValidator.validate(lastState)
      val timeStamp: String = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

      outputState match {
        case Green() => monitorLogger.log(name, currentValidator.name, timeStamp,"Green", "")
        case Red(message) => monitorLogger.log(name, currentValidator.name, timeStamp,"Red", message)
      }

      lastState match {

        case Green() => outputState

        case _ => lastState
      }


    }}

  }


}

//val sc = new SparkContext(conf)
//val sc: SparkContext
//object HiveClusterConennector extends HiveConnector(sc: SparkContext)
//object VerifyNewUserWidget extends DataMonitor("New user Widget Validator", List(VerifyIfTableExists(HiveClusterConennector, "a", "v")))

