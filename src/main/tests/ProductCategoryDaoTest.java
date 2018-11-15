import com.codecool.shop.dao.DbConnection;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.codecool.shop.dao.DbConnection.getConnection;
import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoTest {

    private static boolean isDbTesting = false;
    private static ProductCategoryDao productCategoryData;
    private static List<ProductCategory> categoriesAdded = new ArrayList<>();

    @BeforeAll
    public static void setUp() {

        if (isDbTesting) {
            DbConnection.setTestingDatabase();
            productCategoryData = ProductCategoryDaoJdbc.getInstance();
        } else
            productCategoryData = ProductCategoryDaoMem.getInstance();

        //setting up a new product category
        ProductCategory tablet = new ProductCategory(
                1,"Tablet", "Hardware",
                "A tablet computer, commonly shortened to tablet.");
        productCategoryData.add(tablet);
        categoriesAdded.add(tablet);

        ProductCategory laptop = new ProductCategory(
                2,"Laptop", "Hardware",
                "These are portable laptops.");
        productCategoryData.add(laptop);
        categoriesAdded.add(laptop);

        ProductCategory mobile = new ProductCategory(
                3,"Mobile", "Hardware",
                "These are mobile phones.");
        productCategoryData.add(mobile);
        categoriesAdded.add(mobile);
    }

    @AfterAll
    static void emptyDatabase() {

        if (isDbTesting) {
            try (Connection connection = DbConnection.getConnection();
                 PreparedStatement emptyTable = connection.prepareStatement("DELETE FROM categories;")) {

                emptyTable.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}

    @Test
    void isGetAllMethodReturnsSameAmountOfObjects() {

        int numOfAddedCategories = 3;
        assertEquals(numOfAddedCategories, productCategoryData.getAll().size());
    }

    @Test
    void isGetAllMethodReturnsTheSameObjectsAdded() {

        assertEquals(categoriesAdded, productCategoryData.getAll());
    }


    @Test
    void isAfterRemoveDataListGetsShorter() {

        int idOfAddedObj = productCategoryData.getAll().get(0).getId();
        productCategoryData.remove(idOfAddedObj);
        assertEquals(productCategoryData.getAll().size(), categoriesAdded.size() - 1);

        productCategoryData.add(categoriesAdded.get(0));

    }

    @Test
    void findGivesBackSameObject() {
        // id auto increment

        ProductCategory addedObject = productCategoryData.getAll().get(0);

        assertEquals(productCategoryData.find(addedObject.getId()), addedObject);
    }
}