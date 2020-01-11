import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class OperationTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void connectResponse() {
    DataOutputStream dos = new DataOutputStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
      }
    });
    Map<String, DataOutputStream> clients = new HashMap<>();
    byte[] username = "username".getBytes();
    Operation.connectResponse(dos,clients,username );
  }

  @Test
  public void connectResponse1() {
    DataOutputStream dos = new DataOutputStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
      }
    });
    Map<String, DataOutputStream> clients = new HashMap<>();
    byte[] username = "username".getBytes();
    clients.put("username",dos);
    Operation.connectResponse(dos,clients,username );
  }

  @Test
  public void insultResponse() {
    DataOutputStream dataOutputStream = new DataOutputStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
      }
    });
    Map<String, DataOutputStream> clients = new HashMap<>();
    byte[] username = "username".getBytes();
    byte[] toUsername = "toUsername".getBytes();
    Operation.insultResponse(dataOutputStream,clients,username,toUsername);
  }

  @Test
  public void insultResponse1() {
    DataOutputStream dataOutputStream = new DataOutputStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
      }
    });
    Map<String, DataOutputStream> clients = new HashMap<>();
    byte[] username = "username".getBytes();
    byte[] toUsername = "toUsername".getBytes();
    clients.put("username",dataOutputStream);
    Operation.insultResponse(dataOutputStream,clients,username,toUsername);
  }

  @Test
  public void directMsgResponse() {
    DataOutputStream dataOutputStream = new DataOutputStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
      }
    });
    Map<String, DataOutputStream> clients = new HashMap<>();
    byte[] username = "username".getBytes();
    byte[] toUsername = "toUsername".getBytes();
    byte[] message = "message".getBytes();
    Operation.directMsgResponse(dataOutputStream,clients,username,toUsername,message);
  }

  @Test
  public void directMsgResponse1() {
    DataOutputStream dataOutputStream = new DataOutputStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
      }
    });
    Map<String, DataOutputStream> clients = new HashMap<>();
    byte[] username = "username".getBytes();
    byte[] toUsername = "toUsername".getBytes();
    byte[] message = "message".getBytes();
    clients.put("username",dataOutputStream);
    Operation.directMsgResponse(dataOutputStream,clients,username,toUsername,message);
  }

  @Test
  public void broadcastResponse() {
    DataOutputStream dataOutputStream = new DataOutputStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
      }
    });
    Map<String, DataOutputStream> clients = new HashMap<>();
    byte[] username = "username".getBytes();
    byte[] message = "message".getBytes();
    Operation.broadcastResponse(dataOutputStream,clients,username,message);
  }

  @Test
  public void broadcastResponse1() {
    DataOutputStream dataOutputStream = new DataOutputStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
      }
    });
    Map<String, DataOutputStream> clients = new HashMap<>();
    byte[] username = "username".getBytes();
    byte[] message = "message".getBytes();
    clients.put("username",dataOutputStream);
    Operation.broadcastResponse(dataOutputStream,clients,username,message);
  }

  @Test
  public void queryResponse() {
    DataOutputStream dataOutputStream = new DataOutputStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
      }
    });
    Map<String, DataOutputStream> clients = new HashMap<>();
    byte[] username = "username".getBytes();
    Operation.queryResponse(dataOutputStream,clients,username);
  }

  @Test
  public void queryResponse1() {
    DataOutputStream dataOutputStream = new DataOutputStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
      }
    });
    Map<String, DataOutputStream> clients = new HashMap<>();
    byte[] username = "username".getBytes();
    clients.put("username",dataOutputStream);
    Operation.queryResponse(dataOutputStream,clients,username);
  }

  @Test
  public void disconnectResponse() {
    DataOutputStream dataOutputStream = new DataOutputStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
      }
    });
    Map<String, DataOutputStream> clients = new HashMap<>();
    byte[] username = "username".getBytes();
    Operation.disconnectResponse(dataOutputStream,clients,username);
  }

  @Test
  public void disconnectResponse1() {
    DataOutputStream dataOutputStream = new DataOutputStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
      }
    });
    Map<String, DataOutputStream> clients = new HashMap<>();
    byte[] username = "username".getBytes();
    clients.put("username", dataOutputStream);
    Operation.disconnectResponse(dataOutputStream,clients,username);
  }

  @Test
  public void showOnlineUsers() {
    Map<String, DataOutputStream> clients = new HashMap<>();
    Operation.showOnlineUsers(clients);
  }
}