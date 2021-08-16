package com.nsarvar;


import com.nsarvar.cmd.Cmd;

public class Application {
    public static void main(String[] args) {
        Cmd cmd = new Cmd();
        String result = cmd.call();
        System.out.println("Result file: " + result);
    }
}
