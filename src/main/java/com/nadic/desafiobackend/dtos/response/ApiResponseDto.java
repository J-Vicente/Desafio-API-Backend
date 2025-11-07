package com.nadic.desafiobackend.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponseDto<T> {
        private String status;
        private T data;
        private String message;


        public ApiResponseDto(String status, T data, String message) {
            this.status = status;
            this.data = data;
            this.message = message;
        }

}



