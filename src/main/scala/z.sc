 
//Strategy pattern using function types
 type GetIncidentByModeCacheStrategy = (Mode) => Vector[Incident]

  val CacheOnByModeStrategy: GetIncidentByModeCacheStrategy = underlyingCache.get(_)

  val CacheOffByModeStrategy:GetIncidentByModeCacheStrategy = incidentPersistenceService.getUnclearedBy(_)

// Create child actor
  val deleter = context.actorOf(Props[IngestNotificationDeleter])
//Actor with non-default constructor
 ActorConfig.system.actorOf(Props(new IncidentCacheLoader("arg1", "arg2")), name = "incidentCacheLoader")


//Pattern matching function
 def escapeChars(s: String): String = s match{
    case a if s contains('"') => s.replaceAll(""""""", """""""")
    case _ => s""""$s"""" 

  }


(A) => B can be interpreted as a function that transforms a type A into a resulting type B.

JSON XPath (json4s)

def retrieveIngestTopicArn = {
    val json = Source.fromURL(statusEndpoint).getLines().mkString("\n")
    val parsed = parse(json)
    val identifiers = (parsed \\ "identifier")
    identifiers.children(2).extract[String]
  }

 val system = ActorSystem("MySystem")
 val s3downloadRouter = system.actorOf(Props[S3DownloadActor].withRouter(RoundRobinRouter(nrOfInstances = 50)))
 sortedObjectKeys.foreach(s3downloadRouter ! _)

class S3DownloadActor extends Actor with ActorLogging {
    val fileDateFormat = new java.text.SimpleDateFormat("HH-mm-ss")
    def receive = {
      case tpegMessageSet(key, size, modified) => {
        log.info(s"Downloading $key...")
        val f = new File(localPath + key + "-" + fileDateFormat.format(modified) + ".xml")
        client.getObject(new GetObjectRequest(sourceBucket, key), f)
        log.info(s"...to ${f.getAbsolutePath}")
      }
    }
  }
