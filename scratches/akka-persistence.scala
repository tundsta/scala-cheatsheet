/**
  * Akka Persistence provides 3 different streams:
  * the stream of all persistence ids
  * the stream of events for a given persistence id
  * the stream of events for a given tag
  *
  * Using flatMapMerge we can create a single stream of all the events:
  *
  **/
val journal = PersistenceQuery(sys)
  .readJournalFor[CassandraReadJournal](CassandraReadJournal.Identifier)

journal.allPersistenceIds().flatMapMerge(Int.MaxValue, { persistenceId =>
  journal.eventsByPersistenceId(persistenceId, 0L, Long.MaxValue)
})
