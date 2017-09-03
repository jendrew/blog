package to.ogarne.ogarneblog.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import to.ogarne.ogarneblog.model.Page;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PageDaoImpl extends ExperimentDaoImpl<Page, Long> implements PageDao {


    @Override
    public List<Page> getPagesForMenu() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Page> criteria = builder.createQuery(Page.class);
        Root<Page> root =  criteria.from(Page.class);

        // order descending by date
        criteria.where(builder.isNotNull(root.get("menuPosition")));
        criteria.orderBy(builder.asc(root.get("menuPosition")));

        List<Page> pages = session.createQuery(criteria)
                .getResultList();
        session.close();

        return pages;
    }
}
