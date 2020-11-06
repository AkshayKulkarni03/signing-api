package com.signing.demo.signingapi.controller;

import com.signing.demo.signingapi.model.SigningOverview;
import com.signing.demo.signingapi.model.SigningOverviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public ResponseEntity<SigningOverview> createTransaction(@RequestBody SigningOverviewRequest signingOverviewRequest) {
        HttpHeaders httpHeaders = getHttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SigningOverviewRequest> entity = new HttpEntity<>(signingOverviewRequest, httpHeaders);

        return restTemplate.postForEntity(evidosUrl, entity, SigningOverview.class);
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
    public ResponseEntity<SigningOverview> getTransaction(@PathVariable("id") String id) {
        return restTemplate.getForEntity(evidosUrl + id, SigningOverview.class);
    }

    @GetMapping(path = "/{id}/file/{fileName}")
    public void getSignedDocument(@PathVariable("id") String id, @PathVariable("fileName") String fileName) {
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Application", applicationKey);
        httpHeaders.set("Authorization", authKey);
        return httpHeaders;
    }
}
