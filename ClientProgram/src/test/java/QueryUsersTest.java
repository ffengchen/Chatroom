import static org.junit.Assert.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test QueryUser class.
 */
public class QueryUsersTest {
QueryUsers queryUsers;
  @Before
  public void setUp() throws Exception {
    String username = "username";
    queryUsers = new QueryUsers(username.getBytes());
  }

  @Test
  public void testEquals() {
    String username = "username";
    QueryUsers queryUsers1 = new QueryUsers(username.getBytes());
    Assert.assertEquals(queryUsers1,queryUsers);
  }

  @Test
  public void testEquals1() {

    Assert.assertEquals(queryUsers,queryUsers);
  }

  @Test
  public void testEquals2() {

    Assert.assertNotEquals(queryUsers,null);
  }

  @Test
  public void testEquals3() {

    Assert.assertNotEquals(queryUsers,new QueryUsersTest());
  }

  @Test
  public void testEquals4() {
    String username = "username1";
    QueryUsers queryUsers1 = new QueryUsers(username.getBytes());
    Assert.assertNotEquals(queryUsers1,queryUsers);
  }
  @Test
  public void writeToStream() {
    queryUsers.writeToStream(new DataOutputStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
      }
    }));
  }
  @Test
  public void testEquals5() {
    String username = "Username";
    QueryUsers queryUsers1 = new QueryUsers(username.getBytes());
    Assert.assertNotEquals(queryUsers1,queryUsers);
  }





  @Test
  public void testHashCode() {
    String username = "username";
    QueryUsers queryUsers1 = new QueryUsers(username.getBytes());
    Assert.assertEquals(queryUsers1.hashCode(),queryUsers.hashCode());
  }

  @Test
  public void testToString() {
    String username = "username";
    QueryUsers queryUsers1 = new QueryUsers(username.getBytes());
    Assert.assertEquals(queryUsers1.toString(),queryUsers.toString());
  }
}