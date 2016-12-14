/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

/**
 *
 * @author ellen
 */
public class Test {

    public void one() {
        System.out.println("one()");
    }

    public void pass() {
        run(this::one);
    }

    public void run(final Function function) {
        function.call();
    }

    @FunctionalInterface
    interface Function {

        void call();
    }
}
