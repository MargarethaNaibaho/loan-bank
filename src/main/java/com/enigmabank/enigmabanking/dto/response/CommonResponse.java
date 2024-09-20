package com.enigmabank.enigmabanking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonResponse <T>{
    private String message;
    private Integer statusCode;
    private T data;
//    private PagingResponse paging;
}
