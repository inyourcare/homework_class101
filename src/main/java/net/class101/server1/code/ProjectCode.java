package net.class101.server1.code;

import lombok.Getter;

public class ProjectCode {

    @Getter
    public enum ProductKind {
        Class("C"), Kit("K");

        String code;

        ProductKind(String code) {
            this.code = code;
        }
    }

    @Getter
    public enum CommandCode {
        Order("O"), Quit("Q"), SPACE(" ");

        String code;

        CommandCode(String code) {
            this.code = code;
        }
    }

    @Getter
    public enum SalesState {
        Success("S", "Success"), Fail("F", "Fail");

        String code;
        String msg;

        SalesState(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
}
