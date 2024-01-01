package com.nareshit.ticketbooking;

import java.util.Base64;

public class EncrptUserPwd {
    public static void main(String[] args) {

        Base64.Encoder encoder = Base64.getMimeEncoder();
        String s = "nareshitadmin:nareshitadmin";
        String eStr = encoder.encodeToString(s.getBytes());
        System.out.println(eStr);

        Base64.Decoder decoder = Base64.getMimeDecoder();
        byte[] bytes = decoder.decode(eStr);
        String res = new String(bytes);
        System.out.println(res);

    }
}
// MIME
/*

1) RFC 2045
2) RFC 4648

 */