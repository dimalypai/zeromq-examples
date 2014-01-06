import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Subscriber {
  public static void main(String[] args) {
    Context context = ZMQ.context(1);

    Socket socket = context.socket(ZMQ.SUB);
    socket.connect("tcp://localhost:5000");

    socket.subscribe("tech".getBytes());
    socket.subscribe("music".getBytes());

    while (!Thread.currentThread().isInterrupted()) {
      String reply = socket.recvStr(0);
      System.out.println("Received " + reply);
    }

    socket.close();
    context.term();
  }
}

