import dao.DepartmentDao;
import dao.WorkerDao;
import entity.Availability;
import entity.Department;
import entity.Worker;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.DaoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WorkerDaoTest {
    private static Logger logger;
    private static DaoUtil daoUtil;

    private static DepartmentDao departmentDao;
    private static WorkerDao workerDao;


    @BeforeClass
    public static void setup() {
        logger = LogManager.getLogger(DepartmentDaoTest.class);
        daoUtil = new DaoUtil("hibernate-test.cfg.xml");

        departmentDao = new DepartmentDao(daoUtil);
        workerDao = new WorkerDao(daoUtil);
    }

    @Before
    public void save() {
        daoUtil.openSession();

        Department dep1 = new Department("1_dep", true);
        departmentDao.save(dep1);

        Department dep2 = new Department("2_dep", true);
        departmentDao.save(dep2);

        Worker worker1 = new Worker(22, Availability.PART_TIME, "ABC", dep1);
        workerDao.save(worker1);

        Worker worker2 = new Worker(30, Availability.FULL_TIME, "ZzZ", dep1);
        workerDao.save(worker2);

        Worker worker3 = new Worker(12, Availability.PART_TIME, "qwe", dep2);
        workerDao.save(worker3);
    }

    @After
    public void remove() {
        workerDao.deleteAll();
        departmentDao.deleteAll();
        daoUtil.getCurrentSession().close();
    }

    @Test
    public void givenDepartmentIdWhenFindWorkersThenGetWorkers_HQLPositive() {

        List<String> expected = Arrays.asList("ABC", "ZzZ");

        List<String> actual = new ArrayList<>();

        for (Worker worker : workerDao.findByDepartmentIdHql(1)) {
            actual.add(worker.getFull_name());
        }

        assertEquals(expected, actual);
    }

    @Test
    public void givenDepartmentIdWhenFindWorkersThenGetWorkers_HQLNegative() {

        List<String> expected = Arrays.asList("qwe");

        List<String> actual = new ArrayList<>();

        for (Worker worker : workerDao.findByDepartmentIdHql(1)) {
            actual.add(worker.getFull_name());
        }

        assertNotEquals(expected, actual);
    }

    @Test
    public void givenDepartmentIdWhenFindWorkersThenGetWorkers_CriteriaPositive() {

        List<String> expected = Arrays.asList("ABC", "ZzZ");

        List<String> actual = new ArrayList<>();

        for (Worker worker : workerDao.findByDepartmentIdCriteria(1)) {
            actual.add(worker.getFull_name());
        }

        assertEquals(expected, actual);
    }

    @Test
    public void givenDepartmentIdWhenFindWorkersThenGetWorkers_CriteriaNegative() {

        List<String> expected = Arrays.asList("qwe");

        List<String> actual = new ArrayList<>();

        for (Worker worker : workerDao.findByDepartmentIdCriteria(1)) {
            actual.add(worker.getFull_name());
        }

        assertNotEquals(expected, actual);
    }

    @Test
    public void givenAvailabilityWhenFindWorkersThenGetWorkers_HQLPositive() {

        List<String> expected = Arrays.asList("ABC", "qwe");

        List<String> actual = new ArrayList<>();

        for (Worker worker : workerDao.findByAvailabilityHql(Availability.PART_TIME)) {
            actual.add(worker.getFull_name());
        }

        assertEquals(expected, actual);
    }

    @Test
    public void givenAvailabilityWhenFindWorkersThenGetWorkers_HQLNegative() {

        List<String> expected = Arrays.asList("ZzZ");

        List<String> actual = new ArrayList<>();

        for (Worker worker : workerDao.findByAvailabilityHql(Availability.PART_TIME)) {
            actual.add(worker.getFull_name());
        }

        assertNotEquals(expected, actual);
    }

    @Test
    public void givenAvailabilityWhenFindWorkersThenGetWorkers_CriteriaPositive() {

        List<String> expected = Arrays.asList("ABC", "qwe");

        List<String> actual = new ArrayList<>();

        for (Worker worker : workerDao.findByAvailabilityCriteria(Availability.PART_TIME)) {
            actual.add(worker.getFull_name());
        }

        assertEquals(expected, actual);
    }

    @Test
    public void givenAvailabilityWhenFindWorkersThenGetWorkers_CriteriaNegative() {

        List<String> expected = Arrays.asList("ZzZ");

        List<String> actual = new ArrayList<>();

        for (Worker worker : workerDao.findByAvailabilityCriteria(Availability.PART_TIME)) {
            actual.add(worker.getFull_name());
        }

        assertNotEquals(expected, actual);
    }
}
