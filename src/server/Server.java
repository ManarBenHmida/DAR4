package server;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    public static void main(String[] args) {
        try {
            int Port = 2812; 
            DatagramSocket socket = new DatagramSocket(Port); 
            System.out.println("Le serveur  attend la connexions"); 
            while (true) { 
                byte[] receiveData = new byte[1024]; 
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket); 

                String Message = new String(receivePacket.getData(), 0, receivePacket.getLength()); 
                InetAddress Address = receivePacket.getAddress(); 
                int clientPort = receivePacket.getPort(); 

                System.out.println("Le message reçu du  " + Address + ":" + Port + ": " + Message); 
               
                String welcomeMessage = "Bienvenu " + Message; 
                byte[] sendData = welcomeMessage.getBytes(); 
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, Address, clientPort); 
                socket.send(sendPacket); 
                byte[] receiveHeure = new byte[1024]; 
                DatagramPacket receivePacketHeure = new DatagramPacket(receiveHeure, receiveHeure.length); 
                socket.receive(receivePacketHeure);

                String MessageHeure = new String(receivePacketHeure.getData(), 0, receivePacketHeure.getLength());
                System.out.println("l'heure du message"+MessageHeure);
                String heureResponse ="Heure:  "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                byte[] sendHeure= heureResponse.getBytes(); 
                DatagramPacket sendPacket1 = new DatagramPacket(sendHeure, sendHeure.length, Address, clientPort);
                socket.send(sendPacket1); 
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
}