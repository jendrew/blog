package to.ogarne.ogarneblog.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import to.ogarne.ogarneblog.model.File;

@Service
public interface FileService {


    File findById(Long id);

    Resource loadAsResource(Long id);

    void save(File file);

    void delete(File file);
}
