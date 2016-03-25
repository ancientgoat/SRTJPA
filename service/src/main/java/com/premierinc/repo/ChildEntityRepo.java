package com.premierinc.repo;

import com.premierinc.dto.DumbDto;
import com.premierinc.entity.ChildEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 */
//@RepositoryRestResource(collectionResourceRel = "childentity", path = "childentity", exported = false)
@RepositoryRestResource(exported = false)
public interface ChildEntityRepo extends JpaRepository<ChildEntity, Long> {

	/**
	 * Force (with FETCH) EAGER loading of parent with Child
	 *
	 * @param inPageable
	 * @return
	 */
	@Query(value = "SELECT C FROM ChildEntity C JOIN FETCH C.parent P")
	Slice<ChildEntity> sliceAll(final Pageable inPageable);

	/**
	 * Force (with FETCH) EAGER loading of parent with Child
	 *
	 * @param inPageable
	 * @return
	 */
	@Query(value = "SELECT C FROM ChildEntity C LEFT JOIN FETCH C.parent P",
			countQuery = "SELECT count(C) FROM ChildEntity C")
	Page<ChildEntity> pageAll(final Pageable inPageable);

	/**
	 * Notice: No @Query, no implementation, and yet it works.  Must be magic.
	 *
	 * @param inName
	 * @param inNameGiven
	 * @param inNameFamily
	 * @return
	 */
	ChildEntity findFirstByNameOrNameGivenOrNameFamily(String inName, String inNameGiven,
			String inNameFamily);

	/**
	 * Force (with FETCH) EAGER loading of parent with Child.
	 *
	 * @param inNameGiven
	 * @param inNameFamily
	 * @return
	 */
	@Query(value = "SELECT C FROM ChildEntity C LEFT JOIN FETCH C.parent P "
			+ " WHERE C.nameGiven = :givenName OR C.nameFamily = :familyName")
	ChildEntity findFirstByNameGivenOrNameFamily(@Param("givenName") String inNameGiven,
			@Param("familyName") String inNameFamily);

	/**
	 *
	 * @param name
	 * @return
	 */
	@Query(value = "SELECT new com.premierinc.dto.DumbDto( C.name, C.nameFamily ) FROM ChildEntity C "
			+ " WHERE C.name = :name")
	DumbDto gimmeDatDumbDto(@Param("name") String name);

	/**
	 *
	 * @param name
	 * @return
	 */
	@Query(value = "SELECT new com.premierinc.dto.DumbDto( C.name, C.nameFamily ) FROM ChildEntity C ")
	List<DumbDto> gimmeDemDumbDtos();
}
