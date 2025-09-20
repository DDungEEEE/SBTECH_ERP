//package com.sbtech.erp.permission.adapter.out.persistence.repository;
//
//import com.sbtech.erp.permission.adapter.out.persistence.entity.PermissionEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface PermissionJpaRepository extends JpaRepository<PermissionEntity, Long> {
//    List<PermissionEntity> findByIdIn(List<Long> permissionIds);
//    List<PermissionEntity> findAllByResource(String resource);
//}
