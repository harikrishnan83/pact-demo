package com.polarizer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TestController {
    @RequestMapping(value = "/", produces = {"application/json"})
    public @ResponseBody
    String index() {
        return "{\"active\": true, \"name\": \"Hari Krishnan\"}";
    }
}
