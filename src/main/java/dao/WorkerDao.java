package dao;

import entity.Availability;
import entity.Worker;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.DaoUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class WorkerDao {

    private DaoUtil daoUtil;

    public WorkerDao(DaoUtil daoUtil) {
        this.daoUtil = daoUtil;
    }

    public void save(Worker entity) {
        Session session = daoUtil.getCurrentSessionWithTransaction();
        session.save(entity);
        daoUtil.getCurrentTransaction().commit();
    }

    public void update(Worker entity) {
        Session session = daoUtil.getCurrentSessionWithTransaction();
        session.update(entity);
        daoUtil.getCurrentTransaction().commit();
    }

    public Worker findById(int id) {
        Session session = daoUtil.getCurrentSession();
        Worker worker = (Worker) session.get(Worker.class, id);
        return worker;
    }

    public void delete(int id) {
        Session session = daoUtil.getCurrentSessionWithTransaction();
        Worker worker = session.get(Worker.class, id);
        session.delete(worker);
        daoUtil.getCurrentTransaction().commit();
    }

    public void deleteAll() {
        Session session = daoUtil.getCurrentSessionWithTransaction();
        Query query = session.createQuery("delete from Worker");
        query.executeUpdate();
        daoUtil.getCurrentTransaction().commit();
    }

    public List<Worker> findByDepartmentIdHql(int department_id) {
        Session session = daoUtil.getCurrentSession();

        Query query = session.createQuery("from Worker where department_id= :idParam");
        query.setParameter("idParam", department_id);

        List<Worker> workers = query.list();

        return workers;
    }

    public List<Worker> findByAvailabilityHql(Availability availability) {
        Session session = daoUtil.getCurrentSession();

        Query query = session.createQuery("from Worker where availability= :availabilityParam");
        query.setParameter("availabilityParam", availability);

        List<Worker> workers = query.list();

        return workers;
    }

    public List<Worker> findByDepartmentIdCriteria(int department_id) {
        Session session = daoUtil.getCurrentSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Worker> query = builder.createQuery(Worker.class);
        Root<Worker> root = query.from(Worker.class);

        query.select(root).where(builder.equal(root.get("department"), department_id));
        Query<Worker> q = session.createQuery(query);
        List<Worker> workers = q.getResultList();

        return workers;
    }

    public List<Worker> findByAvailabilityCriteria(Availability availability) {
        Session session = daoUtil.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Worker> query = criteriaBuilder.createQuery(Worker.class);
        Root<Worker> root = query.from(Worker.class);

        query.select(root).where(criteriaBuilder.equal(root.get("availability"), availability));
        Query<Worker> q = session.createQuery(query);
        List<Worker> workers = q.getResultList();

        return workers;
    }
}
