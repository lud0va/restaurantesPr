package common;

import io.vavr.collection.List;

public class CustomerException extends Exception {

    public CustomerException() {

        super(Constants.NOELEMNS);
    }
}
