import static org.junit.Assert.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test DirectMessage class.
 */
public class DirectMessageTest {
DirectMessage directMessage;
  @Before
  public void setUp() throws Exception {
    String sender = "sender";
    String receiver = "receiver";
    String msg = "message";
    directMessage = new DirectMessage(sender.getBytes(),receiver.getBytes(),msg.getBytes());
  }

  @Test
  public void testEquals() {
    String sender = "sender";
    String receiver = "receiver";
    String msg = "message";
    DirectMessage directMessage1 = new DirectMessage(sender.getBytes(),receiver.getBytes(),msg.getBytes());
    Assert.assertEquals(directMessage1,directMessage);
  }

  @Test
  public void testEquals1() {

    Assert.assertEquals(directMessage,directMessage);
  }

  @Test
  public void testEquals2() {

    Assert.assertNotEquals(directMessage,null);
  }

  @Test
  public void testEquals3() {
    String sender = "sender1";
    String receiver = "receiver";
    String msg = "message";
    DirectMessage directMessage1 = new DirectMessage(sender.getBytes(),receiver.getBytes(),msg.getBytes());
    Assert.assertNotEquals(directMessage1,directMessage);
  }

  @Test
  public void testEquals4() {
    String sender = "sender";
    String receiver = "receiver1";
    String msg = "message";
    DirectMessage directMessage1 = new DirectMessage(sender.getBytes(),receiver.getBytes(),msg.getBytes());
    Assert.assertNotEquals(directMessage1,directMessage);
  }

  @Test
  public void testEquals5() {
    String sender = "sender";
    String receiver = "receiver";
    String msg = "message1";
    DirectMessage directMessage1 = new DirectMessage(sender.getBytes(),receiver.getBytes(),msg.getBytes());
    Assert.assertNotEquals(directMessage1,directMessage);
  }

  @Test
  public void testEquals6() {

    Assert.assertNotEquals(directMessage,new DirectMessageTest());
  }

  @Test
  public void testEquals7() {
    String sender = "Sender";
    String receiver = "receiver";
    String msg = "message";
    DirectMessage directMessage1 = new DirectMessage(sender.getBytes(),receiver.getBytes(),msg.getBytes());
    Assert.assertNotEquals(directMessage1,directMessage);
  }
  @Test
  public void testEquals8() {
    String sender = "sender";
    String receiver = "Receiver";
    String msg = "message";
    DirectMessage directMessage1 = new DirectMessage(sender.getBytes(),receiver.getBytes(),msg.getBytes());
    Assert.assertNotEquals(directMessage1,directMessage);
  }
  @Test
  public void testEquals9() {
    String sender = "sender";
    String receiver = "receiver";
    String msg = "Message";
    DirectMessage directMessage1 = new DirectMessage(sender.getBytes(),receiver.getBytes(),msg.getBytes());
    Assert.assertNotEquals(directMessage1,directMessage);
  }

  @Test
  public void testHashCode() {
    String sender = "sender";
    String receiver = "receiver";
    String msg = "message";
    DirectMessage directMessage1 = new DirectMessage(sender.getBytes(),receiver.getBytes(),msg.getBytes());
    Assert.assertEquals(directMessage1.hashCode(),directMessage.hashCode());
  }

  @Test
  public void testToString() {
    String sender = "sender";
    String receiver = "receiver";
    String msg = "message";
    DirectMessage directMessage1 = new DirectMessage(sender.getBytes(),receiver.getBytes(),msg.getBytes());
    Assert.assertEquals(directMessage1.toString(),directMessage.toString());
  }

  @Test
  public void writeToStream() {
    directMessage.writeToStream(new DataOutputStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
      }
    }));
  }

}