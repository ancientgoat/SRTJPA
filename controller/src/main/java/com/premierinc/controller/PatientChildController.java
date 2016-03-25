package com.premierinc.controller;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import com.premierinc.dto.DumbDto;
import com.premierinc.resourceprovider.DumbRestfulPatientResourceProvider;
import com.premierinc.service.ChildEntityService;
import com.premierinc.util.ExceptionHelper;
import com.premierinc.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/patient")
public class PatientChildController {

	private ChildEntityService childEntityService;

	@Autowired
	public PatientChildController(final ChildEntityService inChildEntityService) {
		this.childEntityService = inChildEntityService;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public String getPatient() {

		FhirContext ctx = FhirContext.forDstu2();
		Patient patient = DumbRestfulPatientResourceProvider._makeFakePatient("Elmo");

		try {
			String jsonString = ctx.newJsonParser().encodeResourceToString(patient);
			System.out.println(jsonString);
			return jsonString;
		} catch (Exception e) {
			System.out.println(ExceptionHelper.toString(e));
			throw new IllegalArgumentException(e);
		}
	}

	@RequestMapping(value = "/dumbdto/{name}", method = RequestMethod.GET, produces = "application/json")
	public String getDumoDto(@PathVariable("name") String inName) {

		try {
			DumbDto dumbDto = this.childEntityService.gimmeDatDumbDto(inName);
			return dumbDto.toString();
		} catch (Exception e) {
			System.out.println(ExceptionHelper.toString(e));
			throw new IllegalArgumentException(e);
		}
	}

	@RequestMapping(value = "/dumbdtos", method = RequestMethod.GET, produces = "application/json")
	public String getDemDumoDtos() {
		try {
			return JsonHelper.beanToJsonString(this.childEntityService.gimmeDemDumbDtos());
		} catch (Exception e) {
			System.out.println(ExceptionHelper.toString(e));
			throw new IllegalArgumentException(e);
		}
	}
}
