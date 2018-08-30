package com.nuist.exception;

import com.nuist.ui.Invoker;

import java.io.Serializable;

/**
 * 全局异常
 *
 * @author LwolveJ
 */
public class MyException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1102591083084086915L;

    public MyException() {
        super();
        Invoker.show(new ExceptionFrame(""));
    }

    public MyException(String message) {
        super(message);
        Invoker.show(new ExceptionFrame(message));
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
        Invoker.show(new ExceptionFrame(message));
    }
}
