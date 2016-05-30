import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AppCacheTest.class, RepositoryTests.class, FeedWebServiceTest.class })
public class AllTests {

}
