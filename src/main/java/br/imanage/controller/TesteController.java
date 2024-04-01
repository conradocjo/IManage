package br.imanage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    @RequestMapping(value = "/teste", method = RequestMethod.GET)
    public String teste() {
        return "Hello";
    }
}
