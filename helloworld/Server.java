import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Server {
  public static void main (String[] args) {
    Context context = ZMQ.context(1);

    Socket socket = context.socket(ZMQ.REP);
    socket.bind("tcp://localhost:5000");

    while (!Thread.currentThread().isInterrupted()) {
      String request = socket.recvStr(0);
      System.out.println("Received request: [" + request + "].");
      socket.send("World", 0);
    }

    socket.close();
    context.term();
  }
}

