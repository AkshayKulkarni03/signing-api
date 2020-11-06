package com.signing.demo.signingapi.controller;

import com.signing.demo.signingapi.model.SigningOverviewRequest;
import com.signing.demo.signingapi.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.util.Arrays;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/transaction")
public class TransactionsController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${signing.app.evidos.base.url}")
    private String evidosUrl;

    @Value("${signing.app.evidos.application.key}")
    private String applicationKey;

    @Value("${signing.app.evidos.auth.key}")
    private String authKey;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> createTransaction(@RequestBody SigningOverviewRequest signingOverviewRequest) {
        HttpHeaders httpHeaders = getHttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SigningOverviewRequest> entity = new HttpEntity<>(signingOverviewRequest, httpHeaders);

        return restTemplate.postForEntity(evidosUrl, entity, Transaction.class);
    }

    @PutMapping(path = "/{id}/file/{fileName}", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> attachFileForSigning(@PathVariable("id") String id, @PathVariable("fileName") String fileName,
                                                     @RequestParam("file") MultipartFile file) {
        try {
            HttpHeaders httpHeaders = getHttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_PDF);

            HttpEntity<byte[]> entity = new HttpEntity<>(file.getBytes(), httpHeaders);

            ResponseEntity<Object> responseEntity = restTemplate.exchange(evidosUrl + id + "/file/" + fileName, HttpMethod.PUT, entity, Object.class);
            return ResponseEntity.status(responseEntity.getStatusCode()).build();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(path = "/{id}/start")
    public ResponseEntity<Void> startTransaction(@PathVariable("id") String id) {
        HttpHeaders httpHeaders = getHttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Object> responseEntity = restTemplate.exchange(evidosUrl + id + "/start", HttpMethod.PUT, entity, Object.class);
        return ResponseEntity.status(responseEntity.getStatusCode()).build();
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> getTransaction(@PathVariable("id") String id) {
        HttpEntity<Object> entity = new HttpEntity<>(getHttpHeaders());
        return restTemplate.exchange(evidosUrl + id, HttpMethod.GET, entity, Transaction.class);
    }

    @GetMapping(path = "/{id}/file/{fileName}")
    public ResponseEntity<StreamingResponseBody> getSignedDocument(@PathVariable("id") String id, @PathVariable("fileName") String fileName) {
        HttpHeaders httpHeaders = getHttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
        HttpEntity<Object> entity = new HttpEntity<>(httpHeaders);

        StreamingResponseBody stream = out -> {
            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(evidosUrl + id + "/file/" + fileName, HttpMethod.GET, entity, byte[].class);
            System.out.println(responseEntity.getBody());
            out.write(responseEntity.getBody());
        };

        HttpHeaders responseHttpHeaders = new HttpHeaders();
        responseHttpHeaders.setContentType(MediaType.APPLICATION_PDF);
        responseHttpHeaders.add("content-disposition", "inlinloe;filename=" + fileName);
        responseHttpHeaders.setContentDispositionFormData(fileName, fileName);
        responseHttpHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return ResponseEntity.ok().headers(responseHttpHeaders).body(stream);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Application", applicationKey);
        httpHeaders.set("Authorization", authKey);
        return httpHeaders;
    }
}
