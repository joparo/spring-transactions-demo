package org.joparo.spring.demo;

import java.nio.charset.Charset;

public class Test {

    public static void main(String[] args) {
        byte[] utf8bytes = "ä".getBytes();
        System.out.println(utf8bytes.length);
        for(int i = 0; i<utf8bytes.length; i++) {
            System.out.print(utf8bytes[i]);
        }
        System.out.println("");
        System.out.println("And now in iso");
        byte[] isoBytes = "ä".getBytes(Charset.forName("iso-8859-1"));
        System.out.println(isoBytes.length);
        for(int i = 0; i<isoBytes.length; i++) {
            System.out.print(isoBytes[i]);
        }
        System.out.println("");
        System.out.println("ISO-8859-1 bytes read as UTF-8: " + new String(isoBytes));
        System.out.println("UTF-8 bytes read as ISO-8859-1: " + new String(utf8bytes, Charset.forName("iso-8859-1")));
    }


}
