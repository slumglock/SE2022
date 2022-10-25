package org.hbrs.se1.ws22.uebung3.control;

public class ContainerException extends Exception {

    public Integer id;

    @Override
    public void printStackTrace() {
        System.out.println("Das Member Objekt mit der ID " + id + " ist bereits vorhanden! ");
    }

    public void setExceptionID(Integer id){
        this.id = id;
    }


}