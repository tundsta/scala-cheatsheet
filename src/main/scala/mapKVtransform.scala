//retrieve map value filtering Option(None)
//
def transformStatuses(av: Map[String, AttributeValue]) ={
    val statuses = IncidentStatus.values map { status =>
      val dbFieldName = fieldMap(status).toString
      av.get(dbFieldName) flatMap { f => booleanToStatus(f.getS.toBoolean, status) }
    }
    statuses.flatten
  }

val m = Map("int"->5,"string"->"five")            //Map[String,Any] = Map(int -> 5, string -> five)
m..map { case (k,v:Int) => (k, v+1); case x => x }
