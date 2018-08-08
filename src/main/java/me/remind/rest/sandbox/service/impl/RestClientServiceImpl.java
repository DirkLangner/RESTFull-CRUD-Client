package me.remind.rest.sandbox.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.remind.rest.sandbox.dto.GitRepositoryDTO;
import me.remind.rest.sandbox.service.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for the RESTClient
 *
 * IMPORTANT -- THE SERVICE WORKS JUST WITH A PROPER TOKEN FOR GITHUB
 * SETTING IN /src/main/java/configuration/appconfiguration.java!!!
 */
@Slf4j
@Service
public class RestClientServiceImpl implements RestClientService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpHeaders httpHeaders;

    @Value("${application.apiUrl}")
    String url;

    /**
     * checks the user github repositories
     *
     * @return ResponseEntity<?>
     */
    public void restClient() {
        log.info("execute restclient method");
        HttpEntity entity = new HttpEntity(httpHeaders);
        ResponseEntity<GitRepositoryDTO[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, GitRepositoryDTO[].class);
        GitRepositoryDTO[] gitRepositoryDTO = response.getBody();
        List<GitRepositoryDTO> gitRepositoryDTOs = Arrays.stream(gitRepositoryDTO).collect(Collectors.toList());
        gitRepositoryDTOs.forEach(gitRepositoryDTO1 -> System.out.format("\n name of repository: " + gitRepositoryDTO1.getName() + "programming language : " + gitRepositoryDTO1.getRepositoryDTO().getLanguage() + "\n"));
    }
}