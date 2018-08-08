package me.remind.rest.sandbox.dto;

import lombok.Data;

import java.util.UUID;

/**
 * DTO to handle request for users
 */
@Data
public class UserDTO {

    private UUID id;

    private String firstName;

    private String lastName;

    private String position;

    private String gitHubURL;
}
