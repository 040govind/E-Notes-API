package com.enotes.enotes_api.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericResponse {
  private HttpStatus responseStatus;
  private String status;
  private String message;
  private Object data;

  public ResponseEntity<?> create(){
    Map<String,Object> map = new LinkedHashMap<>();
    map.put("status",status);
    map.put("message",message);
    if(!ObjectUtils.isEmpty(data)){
        map.put("data",data);
    }
    return new ResponseEntity<>(map,responseStatus);
  }

}
