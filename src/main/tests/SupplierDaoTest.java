import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import org.junit.jupiter.api.BeforeAll;

public class SupplierDaoTest {

    private static SupplierDao supplierDao = SupplierDaoMem.getInstance();

    @BeforeAll
    public static void setUp() {


    }

}
