//sort a collection by multiple fields
def sortRailIncidentsByStationSeverityAndUpdateTime(matchedStnsToIncidents: Vector[(String, RailIncident)]) = {
    matchedStnsToIncidents.sortBy(e => (e._1, e._2.severity, e._2.updateTime))
  }