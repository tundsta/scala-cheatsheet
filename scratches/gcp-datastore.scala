

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.cloud.datastore.StructuredQuery.{CompositeFilter, PropertyFilter}
import com.google.cloud.datastore.{DatastoreOptions, Entity, Query, ReadOption}

import scala.io.Source

val datastore = DatastoreOptions.getDefaultInstance.getService

//GoogleCredential.fromStream(Source.fromFile())
//val key  = datastore.newKeyFactory().setKind("consumptionChargeApplied").newKey("E03B982C-5260-48DA-A473-86AF4D8EDB1C");
//datastore.fetch(key)

// The kind for the new entity
val kind = "Task"
// The name/ID for the new entity
val name = "sampletask1"
// The Cloud Datastore key for the new entity
val taskKey = datastore.newKeyFactory.setKind(kind).newKey(name)
val task = Entity.newBuilder(taskKey)
  .set("description", "Buy milk")
  .build()

// Saves the entity
//datastore.put(task)

System.out.printf("Saved %s: %s%n", task.getKey.getName, task.getString("description"))

//Retrieve entity
val retrieved = datastore.get(taskKey, Seq.empty[ReadOption]:_*)

System.out.printf("Retrieved %s: %s%n", taskKey.getName, retrieved.getString("description"))



val keyFactory = datastore.newKeyFactory().setKind("__namespace__");
        val startNamespace = keyFactory.newKey("g")
        val endNamespace = keyFactory.newKey("h")
       val query = Query.newKeyQueryBuilder()
        query.setKind("__namespace__")
        .setFilter(CompositeFilter.and(
        PropertyFilter.gt("__key__", startNamespace),
        PropertyFilter.lt("__key__", endNamespace)))
        .build()

       val results = datastore.run(query)

        while (results.hasNext()) {
        namespaces.add(results.next().getName());
