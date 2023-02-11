package com.finalyearapp.agrifarming.controller;

import com.finalyearapp.agrifarming.model.Expense;
import com.finalyearapp.agrifarming.model.User;
import com.finalyearapp.agrifarming.service.UserService;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/reports")
public class PDFController {


    @Autowired
    ServletContext servletContext;
    @Autowired
    private UserService userService;
    private final TemplateEngine templateEngine;

    public PDFController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @RequestMapping(path = "/{id}")
    public String getReportPage(Model model, @PathVariable(value = "id") long id) {
        User user=userService.getUserById(id);
        List<Expense> expenses=user.getExpenses();
        model.addAttribute("orderEntry",expenses);
        return "report";
    }

    @RequestMapping(path = "/pdf/{id}")
    public ResponseEntity<?> getPDF(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id")Long id) throws IOException {

        /* Do Business Logic*/

        User user=userService.getUserById(id);

        /* Create HTML using Thymeleaf template Engine */

        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("orderEntry", user);
        String orderHtml = templateEngine.process("report", context);

        /* Setup Source and target I/O streams */

        ByteArrayOutputStream target = new ByteArrayOutputStream();
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8080");
        /* Call convert method */
        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

        /* extract output as bytes */
        byte[] bytes = target.toByteArray();


        /* Send the response as downloadable PDF */

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);

    }

}

