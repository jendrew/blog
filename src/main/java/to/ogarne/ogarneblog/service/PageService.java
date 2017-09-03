package to.ogarne.ogarneblog.service;

import to.ogarne.ogarneblog.model.Page;

import java.util.List;

public interface PageService {

    List<Page> findAll();
    List<Page> getPagesForMenu();
    Page findById(Long id);
    void save (Page page);
    void delete(Page page);
}
