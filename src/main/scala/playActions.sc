//synchronous action
def index = Action { implicit request =>
  Ok(...)
}

//asynchronous action
def index = Action.async { implicit request =>
  Future(Ok(...))
}
