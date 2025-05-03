package com.repsy.ApiStorage.Database.repository;

import com.repsy.ApiStorage.Database.entity.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<PackageEntity, Long> {}