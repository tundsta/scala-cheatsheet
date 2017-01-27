//synchronous action
def index = Action { implicit request =>
  Ok(...)
}

//asynchronous action
def index = Action.async { implicit request =>
  Future(Ok(...))
}

 //Content Negotiation
  val list = Action { implicit request =>
    val items = Item.findAll
    render {
      case Accepts.Html() => Ok(views.html.list(items))
      case Accepts.Json() => Ok(Json.toJson(items))
    }
  }
  val AcceptsMp3 = Accepting("audio/mp3")
    render {
      case AcceptsMp3() => ???
    }
  }

