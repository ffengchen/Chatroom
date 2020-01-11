import static org.junit.Assert.*;

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

/**
 * Test DirectMessage class.
 */
public class DirectMessageTest {
  DirectMessage directMessage;
  private byte[] sender;
  private byte[] receiver;
  private byte[] msg;

  @Before
  public void setUp() throws Exception {
    sender = "sender".getBytes();
    receiver = "receiver".getBytes();
    msg = "message".getBytes();
    directMessage = new DirectMessage(sender,receiver,msg);
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
  public void testEqualsA() {

    Assert.assertEquals(directMessage,directMessage);
  }

  @Test
  public void testEqualsB() {

    Assert.assertFalse(directMessage.equals(null));
  }

  @Test
  public void testEqualsC() {
    String sender = "sender1";
    String receiver = "receiver";
    String msg = "message";
    DirectMessage directMessage1 = new DirectMessage(sender.getBytes(),receiver.getBytes(),msg.getBytes());
    Assert.assertFalse(directMessage1.equals(directMessage));
  }

  @Test
  public void testEqualsD() {
    String sender = "sender";
    String receiver = "receiver1";
    String msg = "message";
    DirectMessage directMessage1 = new DirectMessage(sender.getBytes(),receiver.getBytes(),msg.getBytes());
    Assert.assertFalse(directMessage1.equals(directMessage));
  }

  @Test
  public void testEqualsE() {
    String sender = "sender";
    String receiver = "receiver";
    String msg = "message1";
    DirectMessage directMessage1 = new DirectMessage(sender.getBytes(),receiver.getBytes(),msg.getBytes());
    Assert.assertFalse(directMessage1.equals(directMessage));
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
  public void writeToStream() throws IOException {
    FileOutputStream fileOutputStream = new FileOutputStream("src/test/java/directMessage.bin");
    DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
    FileInputStream fileInputStream = new FileInputStream("src/test/java/directMessage.bin");
    DataInputStream dataInputStream = new DataInputStream(fileInputStream);
    directMessage.writeToStream(dataOutputStream);

    int identifier = dataInputStream.readInt();
    dataInputStream.readChar();
    int senderLen = dataInputStream.readInt();
    dataInputStream.readChar();
    byte[] senderName = new byte[senderLen];
    dataInputStream.readFully(senderName);
    dataInputStream.readChar();
    int receiverLen = dataInputStream.readInt();
    dataInputStream.readChar();
    byte[] receiverName = new byte[receiverLen];
    dataInputStream.readFully(receiverName);
    dataInputStream.readChar();
    int messageLen = dataInputStream.readInt();
    byte[] message = new byte[messageLen];
    dataInputStream.readChar();
    dataInputStream.readFully(message);
    Assert.assertEquals(MessageIdentifiers.DIRECT_MESSAGE, identifier);
    Assert.assertEquals(sender.length, senderLen);
    assertArrayEquals(senderName, sender);
    Assert.assertEquals(receiver.length, receiverLen);
    assertArrayEquals(receiverName, receiver);
    Assert.assertEquals(msg.length, messageLen);
    assertArrayEquals(msg, message);
  }

}