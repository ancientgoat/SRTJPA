package com.premierinc.repo;

import com.premierinc.entity.ChildEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 */
//@RepositoryRestResource(collectionResourceRel = "childentity", path = "childentity")
//~~@RepositoryRestResource(collectionResourceRel = "childentity", path = "childentity",exported = false)
//@RepositoryRestResource(exported = false)
public interface ChildEntityRepo extends JpaRepository<ChildEntity, Long> {

//	/**
//	 * Force (with FETCH) EAGER loading of parent with Child
//	 *
//	 * @param inPageable
//	 * @return
//	 */
//	@Query(value = "SELECT C FROM ChildEntity C JOIN FETCH C.parent P")
//	Slice<ChildEntity> sliceAll(final Pageable inPageable);

	/**
	 * Force (with FETCH) EAGER loading of parent with Child
	 *
	 * @param inPageable
	 * @return
	 */
	@Query(value = "SELECT C FROM ChildEntity C LEFT JOIN FETCH C.parent P",
			countQuery = "SELECT count(C) FROM ChildEntity C")
	Page<ChildEntity> pageAll(final Pageable inPageable);

	ChildEntity findFirstByNameOrNameGivenOrNameFamily(String inName, String inNameGiven,
			String inNameFamily);
}
