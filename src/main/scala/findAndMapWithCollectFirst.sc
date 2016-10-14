Seq(Some(1), None, Some(2), None, Some(3)).collectFirst { case Some(_) => "wow" }

