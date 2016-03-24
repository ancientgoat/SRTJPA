package com.premierinc.controller;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import com.premierinc.resourceprovider.DumbRestfulPatientResourceProvider;
import com.premierinc.util.ExceptionHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/patient")
public class PatientChildController {

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public String getPatient() {

		FhirContext ctx = FhirContext.forDstu2();
		Patient patient = DumbRestfulPatientResourceProvider._makeFakePatient("Elmo");
		// Set Some Name - wow what a lot of work

		try {
			String jsonString = ctx.newJsonParser().encodeResourceToString(patient);
			System.out.println(jsonString);
			return jsonString;
		} catch (Exception e) {
			System.out.println(ExceptionHelper.toString(e));
			throw new IllegalArgumentException(e);
			//return "{ 'err' : 'ouch' }";
		}
	}
}
