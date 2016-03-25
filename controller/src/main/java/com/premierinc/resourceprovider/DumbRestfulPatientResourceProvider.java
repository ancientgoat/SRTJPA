package com.premierinc.resourceprovider;

import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.model.primitive.UriDt;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import com.premierinc.entity.ChildEntity;
import com.premierinc.entity.ParentEntity;
import com.premierinc.service.ChildEntityService;
import com.premierinc.util.JsonHelper;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * All resource providers must implement IResourceProvider
 */
@Component
public class DumbRestfulPatientResourceProvider implements IResourceProvider {

	private ChildEntityService childEntityService;

	@Autowired
	public DumbRestfulPatientResourceProvider(final ChildEntityService inChildEntityService) {
		this.childEntityService = inChildEntityService;
	}

	/**
	 * The getResourceType method comes from IResourceProvider, and must
	 * be overridden to indicate what type of resource this provider
	 * supplies.
	 */
	@Override
	public Class<Patient> getResourceType() {
		return Patient.class;
	}

	/**
	 * The "@Read" annotation indicates that this method supports the
	 * read operation. Read operations should return a single resource
	 * instance.
	 *
	 * @param theId
	 *    The read operation takes one parameter, which must be of type
	 *    IdDt and must be annotated with the "@Read.IdParam" annotation.
	 * @return
	 *    Returns a resource matching this identifier, or null if none exists.
	 */
	@Read()
	public Patient getResourceById(@IdParam IdDt theId) {
		Patient patient = _makeFakePatient(theId.getValueAsString());
		return patient;
	}

	/**
	 * The "@Search" annotation indicates that this method supports the
	 * search operation. You may have many different method annotated with
	 * this annotation, to support many different search criteria. This
	 * example searches by family name.
	 *
	 * @param inFamily
	 *    This operation takes one parameter which is the search criteria. It is
	 *    annotated with the "@Required" annotation. This annotation takes one argument,
	 *    a string containing the name of the search criteria. The datatype here
	 *    is StringParam, but there are other possible parameter types depending on the
	 *    specific search criteria.
	 * @return
	 *    This method returns a list of Patients. This list may contain multiple
	 *    matching resources, or it may also be empty.
	 */
	@Search()
	public List<Patient> getPatientByFamily(
			@RequiredParam(name = Patient.SP_FAMILY) StringParam inFamily) {

		ChildEntity child = this.childEntityService.findFirstByNameGivenOrNameFamily(null,
				inFamily.getValue());

		final Patient patient = _makeFakePatient("ByFamily", child, inFamily.getValue());
		return Collections.singletonList(patient);
	}

	@Search()
	public List<Patient> getPatientByName(
			@RequiredParam(name = Patient.SP_NAME) StringParam inName) {

		ChildEntity child = this.childEntityService.findFirstByNameOrNameGivenOrNameFamily(
				inName.getValue(), "", "");

		final Patient patient = _makeFakePatient("ByName", child, inName.getValue());
		return Collections.singletonList(patient);
	}

	@Search()
	public List<Patient> getPatientByGiven(
			@RequiredParam(name = Patient.SP_GIVEN) StringParam inGivenName) {

		ChildEntity child = this.childEntityService.findFirstByNameGivenOrNameFamily(
				inGivenName.getValue(), null);

		final Patient patient = _makeFakePatient("ByGiven", child, inGivenName.getValue());
		return Collections.singletonList(patient);
	}

	/**
	 *
	 * @param inName
	 * @return
	 */
	public static final Patient _makeFakePatient(final String inName) {
		return _makeFakePatient(null, null, inName);
	}

	/**
	 *
	 * @param inName
	 * @return
	 */
	public static final Patient _makeFakePatient(final String inPrefix, final ChildEntity inChild,
			final String inName) {

		String fullName = inName;

		if (null != inChild) {
			if (inChild.haveParent()) {
				final ParentEntity parent = inChild.getParent();
				fullName = String.format("%s:%s: Parent:%s", inPrefix, inChild.getFullName(),
						parent.getFullName());
			} else {
				fullName = String.format("%s:%s: Parent:%s", inPrefix, inChild.getFullName(),
						"*NULL*");
			}
		}

		Patient patient = new Patient();
		patient.addIdentifier();
		patient.getIdentifier().get(0).setSystem(new UriDt("urn:hapitest:mrns"));
		patient.getIdentifier().get(0).setValue("00001");
		patient.getIdentifier().get(0).setElementSpecificId("42");

		IdDt theId = new IdDt();
		theId.setElementSpecificId("42");
		theId.setValue("43");
		patient.setId(theId);

		patient.addName();
		patient.getName().get(0).addFamily(fullName);
		patient.getName().get(0).addGiven(String.format("PatientOne(%s)", fullName));
		patient.setGender(AdministrativeGenderEnum.MALE);
		return patient;
	}
}
