import home.blog.dto.UserDTO;
import home.blog.service.RoleService;
import home.blog.service.impementation.UserServiceImpl;
import org.junit.*;

public class UserServiceTest {

    private UserServiceImpl userService;

    private RoleService roleService;

    @BeforeClass
    public static void setup() {
        System.out.println("initial setup");

    }

    @Before
    public void populateSetUp() {
        userService.create("22@gmail.com", "123", "nametest", "lastname");
        userService.create("221@gmail.com", "1234", "nametest", "lastname");
    }

    @Test
    public void baseIsNotEmpty() {
        assert !userService.getAll().isEmpty();
    }

    @AfterClass
    public static void finished(){
        System.out.println("testing was finished");
    }
}
