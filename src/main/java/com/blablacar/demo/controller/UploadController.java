package com.blablacar.demo.controller;

import com.blablacar.demo.core.Lawn;
import com.blablacar.demo.core.Point;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UploadController {

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if(file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message","Merci de sélectionner un objet à charger");
            return "redirect:uploadStatus";
        }

        if(file.getOriginalFilename() == null || !file.getOriginalFilename().endsWith(".txt")){
            redirectAttributes.addFlashAttribute("message","Merci de sélectionner un fichier au bon format");
            return "redirect:uploadStatus";
        }

        try {

            List<String> list = getPointsFromFile(file, redirectAttributes);

            if(list == null || list.isEmpty()){
                //Si la liste est nulle ou vide c'est que les données du fichier sont erronées.
                return "redirect:/uploadStatus";
            }
            StringBuilder sb = new StringBuilder();

            for(String s : list) {
                sb.append(s);
            }

            redirectAttributes.addFlashAttribute("message", "Positions des points sur la pelouse : "
            + sb.toString() + "'");

        } catch (Exception e) {
            //Si une exception est lancée c'est que les données du fichier sont incorrectes
            redirectAttributes.addFlashAttribute("message","Les données dans le fichier sont incorrectes");
            return "redirect:/uploadStatus";
        }

        return "redirect:/uploadStatus";

    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    List<String> getPointsFromFile(MultipartFile file, RedirectAttributes redirectAttributes) throws Exception{

        List<String> output = new ArrayList<>();

        BufferedReader br;
        Lawn lawn;
        String line;
        InputStream is = file.getInputStream();
        br = new BufferedReader(new InputStreamReader(is));

        //La première ligne du fichier concerne les dimensions de la pelouse
        String lawnDimensions = br.readLine();

        String[] dimensions = lawnDimensions.split(" ");

        if(dimensions.length != 2) {
            redirectAttributes.addFlashAttribute("message","Les données pour les dimensions de la pelouse sont incorrectes");
            return null;
        } else {
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);
            lawn = new Lawn(width, height);
        }

        //Ensuite, on parcoure les autres lignes du fichier, les lignes suivantes peuvent être recoupées par paquet de deux
        while((line = br.readLine() )!= null) {
            //La première ligne concerne la position initiale du point
            List<String> input = new ArrayList<>();
            input.add(line);
            //La seconde ligne concerne les instructions pour les déplacements du point sur la pelouse
            line = br.readLine();
            input.add(line);
            lawn.addPoint(input);
        }


        //Ici la liste des points avec leur position finale
        List<Point> listOfPoints = lawn.getPoints();

        for (Point p : listOfPoints) {
            output.add(p.toString());
        }

        return output;
    }
}
