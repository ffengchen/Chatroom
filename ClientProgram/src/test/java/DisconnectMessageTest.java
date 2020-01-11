import static org.junit.Assert.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test DisconnectMessage class.
 */
public class DisconnectMessageTest {
DisconnectMessage disconnectMessage;

  @Before
  public void setUp() throws Exception {
    String username = "username";
    disconnectMessage = new DisconnectMessage(username.getBytes());
  }

  @Test
  public void testEquals() {
    String username = "username";
    DisconnectMessage disconnectMessage1 = new DisconnectMessage(username.getBytes());
    Assert.assertEquals(disconnectMessage1,disconnectMessage);
  }

  @Test
  public void testEquals1() {

    Assert.assertEquals(disconnectMessage,disconnectMessage);
  }

  @Test
  public void testEquals2() {

    Assert.assertNotEquals(disconnectMessage,null);
  }

  @Test
  public void testEquals3() {

    Assert.assertNotEquals(disconnectMessage,new DisconnectMessageTest());
  }

  @Test
  public void testEquals4() {
    String username = "username1";
    DisconnectMessage disconnectMessage1 = new DisconnectMessage(username.getBytes());
    Assert.assertNotEquals(disconnectMessage1,disconnectMessage);
  }

  @Test
  public void testEquals5() {
    String username = "Username";
    DisconnectMessage disconnectMessage1 = new DisconnectMessage(username.getBytes());
    Assert.assertNotEquals(disconnectMessage1,disconnectMessage);
  }


  @Test
  public void writeToStream() {
    disconnectMessage.writeToStream(new DataOutputStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
      }
    }));
  }

  @Test
  public void testHashCode() {
    String username = "username";
    DisconnectMessage disconnectMessage1 = new DisconnectMessage(username.getBytes());
    Assert.assertEquals(disconnectMessage1.hashCode(),disconnectMessage.hashCode());
  }

  @Test
  public void testToString() {
    String username = "username";
    DisconnectMessage disconnectMessage1 = new DisconnectMessage(username.getBytes());
    Assert.assertEquals(disconnectMessage1.toString(),disconnectMessage.toString());
  }


}