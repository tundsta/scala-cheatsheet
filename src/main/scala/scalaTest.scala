import org.scalatest.prop.TableDrivenPropertyChecks._

val authenticationParameters = Table(
        ("loc", "number", "code", "expectedFormError", "testDescription"),
        ("EW", "", "90900", List(FormError("number", "xAuthentication.number.error.empty")), "fail if the x number is not specified"),
        ("EW", "abc", "90900", List(FormError("number", "b.xAuthentication.number.error.numeric")), "fail if the x number is not numeric"),
        ("SC", "1234567", "90900", List(FormError("number", "Maximum size exceeded. Do not enter more than 6 characters.")), "fail if the x number is over 6 characters in Scotland"),
        ("SC", "1234567", "90900", List(FormError("number", "Maximum size exceeded. Do not enter more than 6 characters.")), "fail if the x number is over 6 characters in Northern Ireland"),
        ("EW", "123456789", "90900", List(FormError("number", "Maximum size exceeded. Do not enter more than 8 characters.")), "fail if the x number is over 8 characters in England/Wales"),
        ("EW", "00006400", "", List(FormError("code", "b.xAuthentication.code.error.empty")), "fail if the code is not specified"),
        ("EW", "00006400", "1234567", List(FormError("code", "You must enter 6 digits.")), "fail if the code is over 6 characters")
      )

      forAll(authenticationParameters) { (location, number, code, expectedFormErrors, testDesc) =>
        info(testDesc)
        implicit val request = fakeRequest.withFormUrlEncodedBody("loc" -> location, "number" -> number, "code" -> code)
        the[PreMappingFormException] thrownBy await(xAuthBlockDef.preMappingActions(currentFiling, user)) should have('errors(expectedFormErrors))
      }

//Scalatest logical matchers
i.incidentId must( be (1) or be (2) )

