package com.premierinc.dto;

import com.premierinc.util.JsonHelper;

/**
 *
 */
public class DumbDto {

	private String noneOfYourBusiness;
	private String whatAreYOULooknAt;

	public DumbDto(final String inNoneOfYourBusiness, final String inWhatAreYOULooknAt) {
		this.noneOfYourBusiness = inNoneOfYourBusiness;
		this.whatAreYOULooknAt = inWhatAreYOULooknAt;
	}

	public String getNoneOfYourBusiness() {
		return noneOfYourBusiness;
	}

	public String getWhatAreYOULooknAt() {
		return whatAreYOULooknAt;
	}

	@Override
	public String toString(){
		return JsonHelper.beanToJsonString(this);
	}
}
