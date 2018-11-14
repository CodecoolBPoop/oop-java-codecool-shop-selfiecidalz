import com.codecool.shop.dao.DbConnection;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoTest {

    private static ProductCategoryDao productCategoryData = ProductCategoryDaoJdbc.getInstance();

    @BeforeAll
    public static void setUp() {

        DbConnection.setTestingDatabase();

        //setting up a new product category
        ProductCategory tablet = new ProductCategory(
                1,"Tablet", "Hardware",
                "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryData.add(tablet);

        ProductCategory laptop = new ProductCategory(
                2,"Laptop", "Hardware",
                "These are portable laptops.");
        productCategoryData.add(laptop);

        ProductCategory mobile = new ProductCategory(
                3,"Mobile", "Hardware",
                "These are mobile phones.");
        productCategoryData.add(mobile);
    }



    @Test
    void testGetAllMethod() {

        int numOfAddedCategories = 3;
        //assertEquals(numOfAddedCategories, productCategoryData.getAll().size());

        for (ProductCategory pc: productCategoryData.getAll()) {
        }
    }

    @Test
    public void testAddMethod() {


    }

    @Test
    public void testRemoveMethod() {


    }

}