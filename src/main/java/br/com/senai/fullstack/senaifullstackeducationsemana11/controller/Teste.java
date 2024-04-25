package br.com.senai.fullstack.senaifullstackeducationsemana11.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Teste {

  @Secured("false")
  @GetMapping
  public String test() {
    return "TESTE";
  }

}
