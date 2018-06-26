package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.domain.Permission;

import java.util.List;

/**
 * Created by Ivan V. on 26-Jun-18
 */
public interface PermissionService {

    List<Permission> getPermissions();

    Permission findOne(Long id);

    Permission findByEndpoint(String endpoint);
}
