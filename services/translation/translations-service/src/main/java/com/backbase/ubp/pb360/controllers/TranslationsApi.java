package com.backbase.ubp.pb360.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/v1/translations")
@RestController
public interface TranslationsApi {


    @GetMapping(value = "", produces = {
            "application/json"
    })
    @ResponseStatus(HttpStatus.OK)
    String getTranslations(@RequestParam(value = "lang", required = true) String lang, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
