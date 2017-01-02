package dataMonitor

import org.apache.spark.sql.hive.HiveContext

/**
  * Created by leandro.mora on 12/29/2016.
  */
trait MonitorLogger {

  def log(dataMonitor:String, dataValidator: String,timestamp:String,state:String, message: String)
}


class HiveMonitorLogger (hiveContext: HiveContext, database: String, table: String) extends MonitorLogger {
  def log(dataService:String, dataValidator: String,timestamp:String,state:String, message: String) = {
    hiveContext.sql("insert into table "+ hiveContext +"."+table+" select t.* from " +
      "(select '"+dataService+"', " +
      "'"+dataValidator+"', " +
      "'"+timestamp+"', " +
      "'"+message+"') t")

  }
}


class ConsoleLogger extends MonitorLogger{
  def log(dataService:String, dataValidator: String,timestamp:String,state:String, description: String) ={
    print("DataService: "+dataService+" ,")
    print("Validation: "+dataValidator+" ,")
    print("Timestamp: "+timestamp+" ,")
    print("State: "+state+" ,")
    println("Description: "+description)
  }
}