package br.com.gubee.interview.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class StandardError {
    private Instant timeStamp;
    private Integer status;
    private String error;
    private String message;
}
