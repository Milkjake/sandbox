package com.sandbox.demo.controller;

import com.sandbox.demo.dao.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.nio.file.FileSystems;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("/sandbox/")
public class SandboxController {
    private TemplateEngine templateEngine;

    @Autowired
    public SandboxController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @RequestMapping("/test")
    public ModelAndView test() {
        ModelAndView model = new ModelAndView("home");
        model.addObject("name", "John");

        return model;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/pdf")
    public ResponseEntity<byte[]> pdf() throws Exception {
        byte[] contents = generatePdf();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String filename = "test.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, no-cache");
        headers.setAccessControlAllowOrigin("http://localhost:3000");
        List<HttpMethod> httpMethods = new ArrayList<>();
        httpMethods.add(HttpMethod.GET);
        headers.setAccessControlAllowMethods(httpMethods);
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }

    private byte[] generatePdf() throws Exception {
        Data data = exampleDataForJohnDoe();

        Context context = new Context();
        context.setVariable("data", data);

        String renderedHtmlContent = templateEngine.process("template", context);

        ITextRenderer renderer = new ITextRenderer();

        String baseUrl = FileSystems
                .getDefault()
                .getPath("src", "main", "resources", "templates")
                .toUri()
                .toURL()
                .toString();

        renderer.setDocumentFromString(renderedHtmlContent, baseUrl);
        renderer.layout();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        renderer.createPDF(baos);
        baos.close();

        return baos.toByteArray();
    }

    private Data exampleDataForJohnDoe() {
        Data data = new Data();
        data.setFirstname("John");
        data.setLastname("Doe");
        data.setStreet("Example Street 1");
        data.setZipCode("12345");
        data.setCity("Example City");
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
        data.setDate(date);
        return data;
    }
}
