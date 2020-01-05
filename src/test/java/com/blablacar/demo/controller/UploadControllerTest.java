package com.blablacar.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UploadControllerTest {

    @Test
    void getPointsFromFile() throws Exception {

        Path path = Paths.get("src/test/resources/testOk.txt");
        byte[] content = Files.readAllBytes(path);

        MultipartFile file = new MockMultipartFile("testOk.txt", "testOk.txt", "text/plain", content);

        UploadController controller = new UploadController();
        RedirectAttributes redirectAttributes = new RedirectAttributes() {
            @Override
            public RedirectAttributes addAttribute(String s, Object o) {
                return null;
            }

            @Override
            public RedirectAttributes addAttribute(Object o) {
                return null;
            }

            @Override
            public RedirectAttributes addAllAttributes(Collection<?> collection) {
                return null;
            }

            @Override
            public RedirectAttributes mergeAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public RedirectAttributes addFlashAttribute(String s, Object o) {
                return null;
            }

            @Override
            public RedirectAttributes addFlashAttribute(Object o) {
                return null;
            }

            @Override
            public Map<String, ?> getFlashAttributes() {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public boolean containsAttribute(String s) {
                return false;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };
        List<String> list =  controller.getPointsFromFile(file, redirectAttributes);

        String firstExcepted = "Point : Abscisse=1, Ordonnée=3, Direction=NORTH -";
        String secondExcepted = "Point : Abscisse=5, Ordonnée=1, Direction=EAST -";

        //On s'assure que les points retournés sont
        assertEquals(firstExcepted, list.get(0));
        assertEquals(secondExcepted, list.get(1));
    }

    @Test
    void getPointsFromWrongFile() throws Exception {
        Path path = Paths.get("src/test/resources/testNotOk");
        byte[] content = Files.readAllBytes(path);

        MultipartFile file = new MockMultipartFile("testNotOk.txt", "testNotOk.txt", "text/plain", content);

        UploadController controller = new UploadController();
        RedirectAttributes redirectAttributes = new RedirectAttributes() {
            @Override
            public RedirectAttributes addAttribute(String s, Object o) {
                return null;
            }

            @Override
            public RedirectAttributes addAttribute(Object o) {
                return null;
            }

            @Override
            public RedirectAttributes addAllAttributes(Collection<?> collection) {
                return null;
            }

            @Override
            public RedirectAttributes mergeAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public RedirectAttributes addFlashAttribute(String s, Object o) {
                return null;
            }

            @Override
            public RedirectAttributes addFlashAttribute(Object o) {
                return null;
            }

            @Override
            public Map<String, ?> getFlashAttributes() {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public boolean containsAttribute(String s) {
                return false;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };
        List<String> list =  controller.getPointsFromFile(file, redirectAttributes);

        //Le format du fichier n'étant pas adapté, aucune donnée concernant les points ne peut être retournée
        assertNull(list);
    }
}