package org.dsi.repo;

import org.dsi.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SupplierRepo  extends JpaRepository<Supplier,Long> {

}
