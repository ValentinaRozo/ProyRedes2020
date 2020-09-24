package Interface;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import Entities.ICMP;
//import Interface.Pantalla;
import java.net.InetAddress;
import jpcap.*;
import jpcap.packet.*;
import jpcap.packet.EthernetPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.TCPPacket;

import javax.swing.*;

public class Principal {
    //private static Pantalla Pantall;
    private static ICMP icm;

    public static void main(String[] foo) throws Exception {
        int numero = 0;
        //Pantall = new Pantalla();
        //JFrame frame = new JFrame("Principal");
        //frame.setContentPane(new Pantalla().getPanel1());
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        //frame.setVisible(true);
        do {
            Scanner reader = new Scanner(System.in);
            System.out.println("Bienvenido, ¿Qué desea enviar?");
            System.out.println("1. ICMP");
            System.out.println("2. ARP");
            numero = reader.nextInt();

            if (numero == 1) {
                //Busca los dispositivos
                NetworkInterface[] devices = JpcapCaptor.getDeviceList();
                JpcapSender sender=JpcapSender.openDevice(devices[0]);
                ICMPPacket icmp = new ICMPPacket();

                String ipDestino = "192.168.0.9";
                String ipOrigen = "192.168.0.6";
                icmp.type=ICMPPacket.ICMP_ECHO;
                icmp.seq=1000;
                icmp.id=999;
                icmp.orig_timestamp=123;
                icmp.trans_timestamp=456;
                icmp.recv_timestamp=789;
                icmp.setIPv4Parameter(0,false,false,false,0,false,false,false,0,1010101,100,IPPacket.IPPROTO_ICMP,
                        InetAddress.getByName(ipOrigen),InetAddress.getByName(ipDestino));
                icmp.data="data".getBytes();

                EthernetPacket ether=new EthernetPacket();
                ether.frametype=EthernetPacket.ETHERTYPE_IP;
                ether.src_mac=new byte[]{(byte)0,(byte)1,(byte)2,(byte)3,(byte)4,(byte)5};
                ether.dst_mac=new byte[]{(byte)0,(byte)6,(byte)7,(byte)8,(byte)9,(byte)10};
                icmp.datalink=ether;

                sender.sendPacket(icmp);
                sender.close();



            }  /*if (numero == 2) {
                
                NetworkInterface [] dispositivos;
                JpcapCaptor capturador;
                JpcapSender sender;
                dispositivos = JpcapCaptor.getDeviceList();
                for (int i = 0; i< dispositivos.length; i++){
                    System.out.println(i);
                }
                ARPPacket arp = new ARPPacket();

            }
*/

            InetAddress inet;

/*
            inet = InetAddress.getByAddress(new byte[] { (byte)192, (byte)168, 0, 6 });
            System.out.println("Sending Ping Request to " + inet);
            System.out.println(inet.isReachable(5000) ? "Host is reachable" : "Host is NOT reachable");

            inet = InetAddress.getByAddress(new byte[] { (byte) 192,(byte)168, 0, 9 });
            System.out.println("Sending Ping Request to " + inet);
            System.out.println(inet.isReachable(5000) ? "Host is reachable" : "Host is NOT reachable");

*/


        }while(numero > 2 || numero < 1);
    }
}
