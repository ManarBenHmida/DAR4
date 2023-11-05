package client;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // nouvelle socket UDP
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost"); 
            int serverPort = 2812; 
            Scanner scanner = new Scanner(System.in);
            System.out.println("Saisir votre prénom :\n");
            String message = scanner.nextLine();
            // Convertit la saisie de l'utilisateur en tableau de bytes
            byte[] sendData = message.getBytes(); 
            // (les données à envoyer, l'adresse du serveur ,port)
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket); 
            // Convertion en une chaîne de caractères
            String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Le message du serveur: " + serverResponse);
            System.out.println("L'adresse du serveur: " + receivePacket.getAddress());
            System.out.println("Le port du serveur: " + receivePacket.getPort());
            String heureMessage = "quelle heure est-il?";
            byte[] sendheureMessage = heureMessage.getBytes();
            DatagramPacket sendPacketHeure = new DatagramPacket(sendheureMessage, sendheureMessage.length, serverAddress, serverPort);
            clientSocket.send(sendPacketHeure);
            byte[] receiveHeure = new byte[1024];
            DatagramPacket receivePacket1 = new DatagramPacket(receiveHeure, receiveHeure.length);
            clientSocket.receive(receivePacket1); 
            String serverResponse1 = new String(receivePacket1.getData(), 0, receivePacket1.getLength());
            System.out.println(serverResponse1);
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
}