package dao;

import entity.Department;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.DaoUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DepartmentDao {

    private DaoUtil daoUtil;

    public DepartmentDao(DaoUtil daoUtil) {
        this.daoUtil = daoUtil;
    }

    public void save(Department entity) {
        Session session = daoUtil.getCurrentSessionWithTransaction();
        session.save(entity);
        daoUtil.getCurrentTransaction().commit();
    }

    public void update(Department entity) {
        Session session = daoUtil.getCurrentSessionWithTransaction();
        session.update(entity);
        daoUtil.getCurrentTransaction().commit();
    }

    public Department findById(int id) {
        Session session = daoUtil.getCurrentSession();
        Department department = (Department) session.get(Department.class, id);
        return department;
    }

    public void delete(int id) {
        Session session = daoUtil.getCurrentSessionWithTransaction();
        Department department = session.get(Department.class, id);
        session.delete(department);
        daoUtil.getCurrentTransaction().commit();
    }

    public void deleteAll() {
        Session session = daoUtil.getCurrentSessionWithTransaction();
        Query query = session.createQuery("delete from Department");
        query.executeUpdate();
        daoUtil.getCurrentTransaction().commit();
    }

    public List<Department> findActiveDepartmentsHql() {
        Session session = daoUtil.getCurrentSession();

        Query query = session.createQuery("from Department where status= :statusParam");
        query.setParameter("statusParam", true);

        List<Department> departments = query.list();

        return departments;
    }

    public List<Department> findActiveDepartmentsCriteria() {
        Session session = daoUtil.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Department> query = criteriaBuilder.createQuery(Department.class);
        Root<Department> root = query.from(Department.class);

        query.select(root).where(criteriaBuilder.equal(root.get("status"), true));
        Query<Department> q = session.createQuery(query);
        List<Department> departments = q.getResultList();

        return departments;
    }
}
