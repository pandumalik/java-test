package ai.inmotion.javatest.controller;

import ai.inmotion.javatest.service.UserService;
import com.itextpdf.text.DocumentException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-github-pdf")
    public ResponseEntity getUserToPdf() throws IOException, DocumentException, URISyntaxException {
        byte[] response = userService.getUserToPdf();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=\"Github User List.pdf\"");
        return new ResponseEntity(response, headers, HttpStatus.OK);
    }

    @GetMapping("/get-simple-pdf")
    public ResponseEntity getSimplePdf() throws IOException, DocumentException, URISyntaxException {
        byte[] response = userService.getSimplePdf();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=\"SimplePdf.pdf\"");
        return new ResponseEntity(response, headers, HttpStatus.OK);
    }
}
