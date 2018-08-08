package me.remind.rest.sandbox.dto;

import lombok.Data;

/**
 * DTO of the for request to the GitHub API
 */
@Data
public class GitRepositoryDTO {

    RepositoryDTO repositoryDTO = new RepositoryDTO();

    private String name;
}
