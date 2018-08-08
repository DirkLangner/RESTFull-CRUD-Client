package me.remind.rest.sandbox.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * A User
 */
@Entity
@Getter
@Setter
public class User extends AbstractEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "git_hub_url", nullable = false)
    private String gitHubURL;

    @Column(name = "git_hub_id")
    private String gitHubId;
}
