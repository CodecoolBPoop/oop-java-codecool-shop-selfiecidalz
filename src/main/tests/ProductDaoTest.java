import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import org.junit.jupiter.api.BeforeAll;

public class ProductDaoTest {

    private static ProductDao productDao = ProductDaoMem.getInstance();

    @BeforeAll
    public static void setUp() {


    }

}
