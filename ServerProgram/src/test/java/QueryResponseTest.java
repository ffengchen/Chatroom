import static org.junit.Assert.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QueryResponseTest {
  private QueryResponse queryResponse;
  private Set<String> clientsUsernames = new HashSet<>();
  private Set<String> clientsUsernamesB = new HashSet<>();

  @Before
  public void setUp() throws Exception {
    clientsUsernames.add("AAA");
    clientsUsernames.add("BBB");
    clientsUsernamesB.add("QQQ");
    queryResponse = new QueryResponse(clientsUsernames);
  }

  @Test
  public void writeToStream() throws IOException {
    FileOutputStream fileOutputStream = new FileOutputStream("src/test/java/queryResponse.bin");
    DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
    FileInputStream fileInputStream = new FileInputStream("src/test/java/queryResponse.bin");
    DataInputStream dataInputStream = new DataInputStream(fileInputStream);
    queryResponse.writeToStream(dataOutputStream);
  }

  @Test
  public void testEqualsA() {
    Assert.assertEquals(queryResponse, queryResponse);
  }

  @Test
  public void testEqualsB() {
    Assert.assertEquals(queryResponse, new QueryResponse(clientsUsernames));
  }

  @Test
  public void testEqualsC() {
    Assert.assertFalse(queryResponse.equals(null));
  }

  @Test
  public void testEqualsD() {
    Assert.assertFalse(queryResponse.equals(new QueryResponse(clientsUsernamesB)));
  }

  @Test
  public void testHashCode() {
    Assert.assertEquals(queryResponse.hashCode(), queryResponse.hashCode());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("Protocol.QueryResponse{" +
        "MessageIdentifier=" + MessageIdentifiers.QUERY_USER_RESPONSE +
        ", numberOfUsers=" + clientsUsernames.size() +
        ", clientsUsernames=" + clientsUsernames +
        '}', queryResponse.toString());
  }
}