import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoTest {

    private static ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getInstance();

    @BeforeAll
    public static void setUp() {

        
    }

    @Test
    public void testAddMethod() {

        int listSizeBefore = productCategoryDao.getAll().size();
        System.err.println(productCategoryDao.getAll().size());
        productCategoryDao.add(new com.codecool.shop.model.ProductCategory("info1", "info2", "info3"));
        int listSizeAfter = productCategoryDao.getAll().size();

        assertEquals(++listSizeBefore, listSizeAfter);
    }

    @Test
    public void testRemoveMethod() {


    }

}