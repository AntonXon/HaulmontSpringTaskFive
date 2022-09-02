package com.example.springrest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusRestController {

    @GetMapping
    public ResponseEntity<String> getStatus() {
        return new ResponseEntity<>("I'm OK", HttpStatus.OK);
    }
}
