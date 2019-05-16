/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import java.io.*;
import java.net.*;

/**
 *
 * @author stygianlgdonic
 */
public class UDPClient {

    public static void main(String[] args) {
        DatagramSocket D_socket = null;
        /*( args.length < 3) {
         System.out.println("Usage: Java UDP Client <message> <host name > <Port number>");
         System.exit(1);
    }*/
        try {
            D_socket = new DatagramSocket();
            byte[] msg = "request".getBytes();
            InetAddress host = InetAddress.getByName("localhost");
            int port = 5214;
            DatagramPacket request = new DatagramPacket(msg, msg.length, host, port);
            D_socket.send(request);
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            D_socket.receive(reply);
            System.out.println("Reply:" + new String(reply.getData()));
        } catch (SocketException e) {
            System.out.println("Socket:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (D_socket != null) {
                D_socket.close();
            }
        }
    }
}
