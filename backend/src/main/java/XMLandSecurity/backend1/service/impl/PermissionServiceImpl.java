package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.domain.Permission;
import XMLandSecurity.backend1.repository.PermissionRepository;
import XMLandSecurity.backend1.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ivan V. on 26-Jun-18
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> getPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission findOne(Long id) {
        return permissionRepository.findOne(id);
    }

    @Override
    public Permission findByEndpoint(String endpoint) {
        return permissionRepository.findByEndpoint(endpoint);
    }

    @Override
    public Permission findByEndpointAndMethod(String endpoint, String method) {
        return permissionRepository.findByEndpointAndMethod(endpoint, method);
    }
}
