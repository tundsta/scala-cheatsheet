filingService.find(filingId).map {
      case Some(filing) if isApproved(filing) => Ok(views.html.filing.accounts.submission(form))
      case None => BadRequest(views.html.filing.accounts.submission(form.withGlobalError("errors.invalidFiling"))) //TODO error message
    }
