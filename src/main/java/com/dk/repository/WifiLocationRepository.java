package com.dk.repository;

import com.dk.entity.WifiLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface WifiLocationRepository extends JpaRepository<WifiLocation,String> {

	//	保存wifi信息
	WifiLocation save(WifiLocation wifi);

	Page<WifiLocation> findByImei(@Param("imei") String imei, Pageable pageable);

}
