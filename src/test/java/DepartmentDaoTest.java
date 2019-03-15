import dao.DepartmentDao;
import entity.Department;
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


public class DepartmentDaoTest {
    private static Logger logger;
    private static DaoUtil daoUtil;

    private static DepartmentDao departmentDao;

    @BeforeClass
    public static void setup() {
        logger = LogManager.getLogger(DepartmentDaoTest.class);
        daoUtil = new DaoUtil("hibernate-test.cfg.xml");

        departmentDao = new DepartmentDao(daoUtil);
    }

    @Before
    public void save() {
        daoUtil.openSession();

        Department dep1 = new Department("1_dep", true);
        departmentDao.save(dep1);

        Department dep2 = new Department("2_dep", true);
        departmentDao.save(dep2);
    }

    @After
    public void remove() {
        departmentDao.deleteAll();
        daoUtil.getCurrentSession().close();
    }

    @Test
    public void givenActiveStatusWhenFindDepartmentsThenGetDepartments_HQLPositive() {

        List<String> expected = Arrays.asList("1_dep", "2_dep");

        List<String> actual = new ArrayList<>();

        for (Department department : departmentDao.findActiveDepartmentsHql()) {
            actual.add(department.getName());
        }

        assertEquals(expected, actual);
    }

    @Test
    public void givenActiveStatusWhenFindDepartmentsThenGetDepartments_HQLNegative() {

        List<String> expected = Arrays.asList("1_dep", "2_dep", "3_dep");

        List<String> actual = new ArrayList<>();

        for (Department department : departmentDao.findActiveDepartmentsHql()) {
            actual.add(department.getName());
        }

        assertNotEquals(expected, actual);
    }

    @Test
    public void givenActiveStatusWhenFindDepartmentsThenGetDepartments_CriteriaPositive() {

        List<String> expected = Arrays.asList("1_dep", "2_dep");

        List<String> actual = new ArrayList<>();

        for (Department department : departmentDao.findActiveDepartmentsCriteria()) {
            actual.add(department.getName());
        }

        assertEquals(expected, actual);
    }

    @Test
    public void givenActiveStatusWhenFindDepartmentsThenGetDepartments_CriteriaNegative() {

        List<String> expected = Arrays.asList("1_dep", "2_dep", "3_dep");

        List<String> actual = new ArrayList<>();

        for (Department department : departmentDao.findActiveDepartmentsCriteria()) {
            actual.add(department.getName());
        }

        assertNotEquals(expected, actual);
    }
}
