package org.hbrs.se1.ws22.uebung3.view;

import org.hbrs.se1.ws22.uebung3.control.ConcreteMember;
import org.hbrs.se1.ws22.uebung3.control.Container;
import org.hbrs.se1.ws22.uebung3.control.ContainerException;
import org.hbrs.se1.ws22.uebung3.control.Member;

public class Client {
    public static void Main(String[] args) {

        Member x = new ConcreteMember(1);
        Member y = new ConcreteMember(2);
        Member z = new ConcreteMember(3);

        Container con = Container.getInstance();


        try {
            con.addMember(x);
            con.addMember(y);
            con.addMember(z);
        } catch (ContainerException e) {
            e.printStackTrace();
        }


    }
    public static void display(Container con) {
        MemberView.dump(con.getCurrentList());

    }



}