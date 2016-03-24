package com.premierinc.service;

import com.premierinc.entity.ChildEntity;
import com.premierinc.repo.ChildEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ChildEntityService {

	private ChildEntityRepo childEntityRepo;

	@Autowired
	public ChildEntityService(final ChildEntityRepo inChildEntityRepo) {
		this.childEntityRepo = inChildEntityRepo;
	}

	/**
	 * Force (with FETCH) EAGER loading of parent with Child
	 *
	 * @param inPageable
	 * @return
	 */
	public Slice<ChildEntity> sliceAll(final Pageable inPageable) {
		return childEntityRepo.sliceAll(inPageable);
	}

	/**
	 * Force (with FETCH) EAGER loading of parent with Child
	 *
	 * @param inPageable
	 * @return
	 */
	public Page<ChildEntity> pageAll(final Pageable inPageable) {
		return childEntityRepo.pageAll(inPageable);
	}

	/**
	 *
	 * @param inName
	 * @param inNameGiven
	 * @param inNameFamily
	 * @return
	 */
	public ChildEntity findFirstByNameOrNameGivenOrNameFamily(String inName, String inNameGiven,
			String inNameFamily) {
		return childEntityRepo.findFirstByNameOrNameGivenOrNameFamily(inName, inNameGiven,
				inNameFamily);
	}

	/**
	 *
	 * @param inNameGiven
	 * @param inNameFamily
	 * @return
	 */
	public ChildEntity findFirstByNameGivenOrNameFamily(String inNameGiven, String inNameFamily) {
		return childEntityRepo.findFirstByNameGivenOrNameFamily(inNameGiven, inNameFamily);
	}
}
