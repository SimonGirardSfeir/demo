package com.blablacar.demo.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MultipartException.class)
    public String handleError(MultipartException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return "redirect:/uploadStatus";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String handleNumberFormatException(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Les données du fichier texte sont au mauvais format");
        return "redirect:/uploadStatus";
    }
}

