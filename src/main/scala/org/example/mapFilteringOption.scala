//retrieve map value filtering Option(None)
//
def transformStatuses(av: Map[String, AttributeValue]) ={
    val statuses = IncidentStatus.values map { status =>
      val dbFieldName = fieldMap(status).toString
      av.get(dbFieldName) flatMap { f => booleanToStatus(f.getS.toBoolean, status) }
    }
    statuses.flatten
  }