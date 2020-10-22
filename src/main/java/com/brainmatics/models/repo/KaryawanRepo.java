package com.brainmatics.models.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.brainmatics.models.entity.Karyawan;

public interface KaryawanRepo extends PagingAndSortingRepository<Karyawan, Integer> {

	@Query("SELECT k FROM Karyawan k WHERE k.departemen.id = :departemenId")
	public List<Karyawan> findAllByDepartemenId(@Param("departemenId") int departemenId);

	public List<Karyawan> findByDepartemenId(int departemenId, Pageable pageable);
	
	public Karyawan findByNip(String nip);
	
	
	
}
