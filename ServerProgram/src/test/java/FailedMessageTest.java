import static org.junit.Assert.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FailedMessageTest {
  private FailedMessage failedMessage;
  private byte[] messageA;
  private byte[] messageB;

  @Before
  public void setUp() throws Exception {
    messageA = "Failed.".getBytes();
    messageB = "Crashed.".getBytes();
    failedMessage = new FailedMessage(messageA);
  }

  @Test
  public void writeToStream() throws IOException {
    FileOutputStream fileOutputStream = new FileOutputStream("src/test/java/failedMessage.bin");
    DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
    FileInputStream fileInputStream = new FileInputStream("src/test/java/failedMessage.bin");
    DataInputStream dataInputStream = new DataInputStream(fileInputStream);
    failedMessage.writeToStream(dataOutputStream);

    int identifier = dataInputStream.readInt();
    dataInputStream.readChar();
    int len = dataInputStream.readInt();
    dataInputStream.readChar();
    byte[] msg = new byte[len];
    dataInputStream.readFully(msg);
    Assert.assertEquals(MessageIdentifiers.FAILED_MESSAGE, identifier);
    Assert.assertEquals(messageA.length, len);
    assertArrayEquals(messageA, msg);
  }

  @Test
  public void testEqualsA() {
    Assert.assertEquals(failedMessage, failedMessage);
  }

  @Test
  public void testEqualsB() {
    Assert.assertEquals(failedMessage, new FailedMessage(messageA));
  }

  @Test
  public void testEqualsC() {
    Assert.assertFalse(failedMessage.equals(null));
  }

  @Test
  public void testEqualsD() {
    Assert.assertFalse(failedMessage.equals(new FailedMessage(messageB)));
  }

  @Test
  public void testHashCode() {
    Assert.assertEquals(failedMessage.hashCode(), new FailedMessage(messageA).hashCode());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("Protocol.FailedMessage{" +
        "MessageIdentifier=" + MessageIdentifiers.FAILED_MESSAGE +
        ", messageSize=" + messageA.length +
        ", message=" + Arrays.toString(messageA) +
        '}', failedMessage.toString());
  }
}