
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BroadcastMessageTest {

  BroadcastMessage broadcastMessage;

  @Before
  public void setUp() throws Exception {
    String sender = "sender";
    String msg = "message";
    broadcastMessage = new BroadcastMessage(sender.getBytes(), msg.getBytes());
  }

  @Test
  public void testEquals() {
    String sender = "sender";
    String msg = "message";
    BroadcastMessage broadcastMessage1 = new BroadcastMessage(sender.getBytes(), msg.getBytes());
    Assert.assertEquals(broadcastMessage1, broadcastMessage);
  }

  @Test
  public void testEqualsA() {
    Assert.assertEquals(broadcastMessage, broadcastMessage);
  }

  @Test
  public void testEqualsB() {
    String sender = "sender";
    String msg = "message1";
    BroadcastMessage broadcastMessage1 = new BroadcastMessage(sender.getBytes(), msg.getBytes());
    Assert.assertFalse(broadcastMessage1.equals(broadcastMessage));
  }

  @Test
  public void testEqualsC() {
    String sender = "sender1";
    String msg = "message";
    BroadcastMessage broadcastMessage1 = new BroadcastMessage(sender.getBytes(), msg.getBytes());
    Assert.assertFalse(broadcastMessage1.equals(broadcastMessage));
  }

  @Test
  public void testEqualsD() {
    String sender = "sender1";
    String msg = "message1";
    BroadcastMessage broadcastMessage1 = new BroadcastMessage(sender.getBytes(), msg.getBytes());
    Assert.assertFalse(broadcastMessage1.equals(broadcastMessage));
  }

  @Test
  public void testEqualsE() {

    Assert.assertFalse(broadcastMessage.equals(null));
  }

  @Test
  public void writeToStream() {
    broadcastMessage.writeToStream(new DataOutputStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
      }
    }));
  }

  @Test
  public void testHashCode() {
    String sender = "sender";
    String msg = "message";
    BroadcastMessage broadcastMessage1 = new BroadcastMessage(sender.getBytes(), msg.getBytes());
    Assert.assertEquals(broadcastMessage1.hashCode(), broadcastMessage.hashCode());
  }

  @Test
  public void testToString() {
    String sender = "sender";
    String msg = "message";
    BroadcastMessage broadcastMessage1 = new BroadcastMessage(sender.getBytes(), msg.getBytes());
    Assert.assertEquals(broadcastMessage1.toString(), broadcastMessage.toString());
  }


}