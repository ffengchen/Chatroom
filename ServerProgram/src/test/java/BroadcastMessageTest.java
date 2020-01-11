
import static org.junit.Assert.assertArrayEquals;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BroadcastMessageTest {

  BroadcastMessage broadcastMessage;
  private byte[] sender;
  private byte[] msg;

  @Before
  public void setUp() throws Exception {
    sender = "sender".getBytes();
    msg = "message".getBytes();
    broadcastMessage = new BroadcastMessage(sender, msg);
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

    Assert.assertFalse(broadcastMessage.equals(new BroadcastMessageTest()));
  }

  @Test
  public void testEqualsC() {
    String sender = "sender";
    String msg = "message1";
    BroadcastMessage broadcastMessage1 = new BroadcastMessage(sender.getBytes(), msg.getBytes());
    Assert.assertFalse(broadcastMessage1.equals(broadcastMessage));
  }

  @Test
  public void testEqualsD() {
    String sender = "sender1";
    String msg = "message";
    BroadcastMessage broadcastMessage1 = new BroadcastMessage(sender.getBytes(), msg.getBytes());
    Assert.assertFalse(broadcastMessage1.equals(broadcastMessage));
  }

  @Test
  public void testEqualsE() {

    Assert.assertFalse(broadcastMessage.equals(null));
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