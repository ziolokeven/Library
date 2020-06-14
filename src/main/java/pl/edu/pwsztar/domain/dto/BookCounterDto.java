package pl.edu.pwsztar.domain.dto;

import java.io.Serializable;

public class BookCounterDto implements Serializable {

    private final long counter;

    public BookCounterDto(long counter) {
        this.counter = counter;
    }

    public long getCounter() {
        return counter;
    }

}
