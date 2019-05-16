/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author stygianlgdonic
 */
public class Main {

    public static void main(String args[]) throws Exception {

        String filename;

        while (true) {

            //create server socket on port 5000
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("Waiting for request");

            Socket s = ss.accept();

            System.out.println("Connected With " + s.getInetAddress().toString());
            
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            filename = din.readUTF();

            ClientThread ct = new ClientThread();
            ct.server(ss, filename, din, dout);

            din.close();
            dout.close();
            s.close();
            ss.close();
        }
    }


}
