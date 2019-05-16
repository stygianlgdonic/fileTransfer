/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;

/**
 *
 * @author stygianlgdonic
 */
public class ClientThread extends Thread {
    
    public void server(ServerSocket ss, String filename,
            DataInputStream din, DataOutputStream dout) {

        try {
            String str = "";

            str = din.readUTF();
            System.out.println("SendGet....Ok");

            if (!str.equals("stop")) {

                System.out.println("Sending File: " + filename);
                dout.writeUTF(filename);
                dout.flush();

                File f = new File(filename);
                FileInputStream fin = new FileInputStream(f);
                long sz = (int) f.length();

                byte b[] = new byte[1024];

                int read;

                dout.writeUTF(Long.toString(sz));
                dout.flush();

                System.out.println("Size: " + sz);
                System.out.println("Buf size: " + ss.getReceiveBufferSize());

                while ((read = fin.read(b)) != -1) {
                    dout.write(b, 0, read);
                    dout.flush();
                }
                fin.close();

                System.out.println("..ok");
                dout.flush();
            }
            dout.writeUTF("stop");
            System.out.println("Send Complete");
            dout.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occured");
        }

    }

}
