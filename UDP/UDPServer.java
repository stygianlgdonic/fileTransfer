/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import java.net.*;
import java.io.*;

/**
 *
 * @author stygianlgdonic
 */
public class UDPServer {

    public static void main(String[] args) {
        // TODO code application logic here
        DatagramSocket D_socket = null;
        /* if (args.length <1 ){
        System.out.println("Usage: Java UDP Server <Port Number>");
        System.exit(1);
    }*/
        try {
            int socketNumber = 5214;
            D_socket = new DatagramSocket(socketNumber);
            byte[] buffer = new byte[1000];
            byte[] message = "Hi There".getBytes();
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                D_socket.receive(request);

                DatagramPacket reply = new DatagramPacket(message, message.length, request.getAddress(),
                        request.getPort());
                D_socket.send(reply);
            }
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
