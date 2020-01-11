import static org.junit.Assert.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test sendInsult class.
 */
public class SendInsultTest {
SendInsult sendInsult;
  @Before
  public void setUp() throws Exception {
    String sender = "sender";
    String receiver = "receiver";
    sendInsult = new SendInsult(sender.getBytes(),receiver.getBytes());
  }

  @Test
  public void testEquals() {
    String sender = "sender";
    String receiver = "receiver";
    SendInsult sendInsult1 = new SendInsult(sender.getBytes(),receiver.getBytes());
    Assert.assertEquals(sendInsult1,sendInsult);
  }

  @Test
  public void testEquals1() {

    Assert.assertEquals(sendInsult,sendInsult);
  }

  @Test
  public void testEquals2() {

    Assert.assertNotEquals(sendInsult,null);
  }

  @Test
  public void testEquals3() {

    Assert.assertNotEquals(sendInsult,new SendInsultTest());
  }

  @Test
  public void testEquals4() {
    String sender = "sender1";
    String receiver = "receiver";
    SendInsult sendInsult1 = new SendInsult(sender.getBytes(),receiver.getBytes());
    Assert.assertNotEquals(sendInsult1,sendInsult);
  }
  @Test
  public void testEquals5() {
    String sender = "sender";
    String receiver = "receiver1";
    SendInsult sendInsult1 = new SendInsult(sender.getBytes(),receiver.getBytes());
    Assert.assertNotEquals(sendInsult1,sendInsult);
  }

  @Test
  public void testEquals6() {
    String sender = "Sender";
    String receiver = "receiver";
    SendInsult sendInsult1 = new SendInsult(sender.getBytes(),receiver.getBytes());
    Assert.assertNotEquals(sendInsult1,sendInsult);
  }

  @Test
  public void writeToStream() {
    sendInsult.writeToStream(new DataOutputStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
      }
    }));
  }

  @Test
  public void testEquals7() {
    String sender = "sender";
    String receiver = "Receiver";
    SendInsult sendInsult1 = new SendInsult(sender.getBytes(),receiver.getBytes());
    Assert.assertNotEquals(sendInsult1,sendInsult);
  }




  @Test
  public void testHashCode() {
    String sender = "sender";
    String receiver = "receiver";
    SendInsult sendInsult1 = new SendInsult(sender.getBytes(),receiver.getBytes());
    Assert.assertEquals(sendInsult1.hashCode(),sendInsult.hashCode());
  }

  @Test
  public void testToString() {
    String sender = "sender";
    String receiver = "receiver";
    SendInsult sendInsult1 = new SendInsult(sender.getBytes(),receiver.getBytes());
    Assert.assertEquals(sendInsult1.toString(),sendInsult.toString());
  }
}