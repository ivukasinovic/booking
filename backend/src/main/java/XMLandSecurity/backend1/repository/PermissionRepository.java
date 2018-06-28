package XMLandSecurity.backend1.repository;

import XMLandSecurity.backend1.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ivan V. on 26-Jun-18
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByEndpoint(String endpoint);

    Permission findByEndpointAndMethod(String endpoint, String method);
}
