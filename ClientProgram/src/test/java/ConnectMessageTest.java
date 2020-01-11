import static org.junit.Assert.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test ConnectMessage class.
 */
public class ConnectMessageTest {
ConnectMessage connectMessage;
  @Before
  public void setUp() throws Exception {
    String username = "username";
    connectMessage = new ConnectMessage(username.getBytes());
  }


  @Test
  public void testEqualsA() {
    String username = "username";
    ConnectMessage connectMessage1 = new ConnectMessage(username.getBytes());
    Assert.assertEquals(connectMessage1,connectMessage);
  }

  @Test
  public void testEqualsB() {
    Assert.assertEquals(connectMessage,connectMessage);
  }

  @Test
  public void testEqualsC() {
    String username = "username1";
    ConnectMessage connectMessage1 = new ConnectMessage(username.getBytes());
    Assert.assertNotEquals(connectMessage1,connectMessage);
  }

  @Test
  public void testEqualsD() {
    Assert.assertNotEquals(connectMessage,null);
  }

  @Test
  public void testEqualsE() {
    String username = "Username";
    ConnectMessage connectMessage1 = new ConnectMessage(username.getBytes());
    Assert.assertNotEquals(connectMessage1,connectMessage);
  }

  @Test
  public void testEqualsF() {

    Assert.assertNotEquals(connectMessage,new ConnectMessageTest());
  }


  @Test
  public void testHashCode() {
    String username = "username";
    ConnectMessage connectMessage1 = new ConnectMessage(username.getBytes());
    Assert.assertEquals(connectMessage1.hashCode(),connectMessage.hashCode());
  }

  @Test
  public void testToString() {
    String username = "username";
    ConnectMessage connectMessage1 = new ConnectMessage(username.getBytes());
    Assert.assertEquals(connectMessage1.toString(),connectMessage.toString());
  }

  @Test
  public void writeToStream() {
    connectMessage.writeToStream(new DataOutputStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
      }
    }));
  }
}