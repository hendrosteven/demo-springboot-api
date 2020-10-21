package com.brainmatics.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brainmatics.models.entity.Karyawan;
import com.brainmatics.models.repo.KaryawanRepo;

@Service
@Transactional
public class KaryawanService {

	@Autowired
	private KaryawanRepo repo;
	
	public Karyawan insert(Karyawan karyawan)throws Exception {
		Karyawan temp = repo.findByNip(karyawan.getNip());
		if(temp != null) {
			throw new Exception("Nip already in used");
		}
		return repo.save(karyawan);
	}
	
	public Karyawan update(Karyawan karyawan) {
		return repo.save(karyawan);
	}
	
	public boolean remove(int id) {
		repo.deleteById(id);
		return true;
	}
	
	public Iterable<Karyawan> findAll(){
		return repo.findAll();
	}
	
	public Karyawan findById(int id) {
		return repo.findById(id).get();
	}
	
	public List<Karyawan> findByDepartemen(int departemenId){
		return repo.findByDepartemenId(departemenId);
	}
}
