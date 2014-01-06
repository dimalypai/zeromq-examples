import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Client {
  public static void main (String[] args) {
    Context context = ZMQ.context(1);

    Socket socket = context.socket(ZMQ.REQ);
    socket.connect("tcp://localhost:5000");

    for (int i = 0; i < 10; i++) {
      socket.send("Hello", 0);
      String reply = socket.recvStr(0);
      System.out.println("Received reply " + i + " [" + reply + "]");
    }

    socket.close();
    context.term();
  }
}

