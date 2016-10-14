//Duck typing - uses reflection in the underlying function call - similar to partial function without conditionals
private def getByMode(cacheOrPersistenceService: { def getUnclearedBy(mode: Mode): Vector[Incident] }, m: Mode) = {
      cacheOrPersistenceService.getUnclearedBy(m)
}
