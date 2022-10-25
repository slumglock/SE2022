package org.hbrs.se1.ws22.uebung3.view;

import org.hbrs.se1.ws22.uebung3.control.Member;

import java.util.List;


public class MemberView {



    public static void dump(List<Member> liste){
        System.out.print("Alle IDs der aktuell gespeicherten Obejkte im Container:\n");
        for(Member x:liste){
            System.out.print(x.toString());
        }
    }









}
