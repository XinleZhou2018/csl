package com.csl.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user_auth")
public class UserAuth {
    @Id
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "auth_type")
    private String authType;

    @Column(name = "auth_id")
    private String authId;

    private String credential;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return auth_type
     */
    public String getAuthType() {
        return authType;
    }

    /**
     * @param authType
     */
    public void setAuthType(String authType) {
        this.authType = authType;
    }

    /**
     * @return auth_id
     */
    public String getAuthId() {
        return authId;
    }

    /**
     * @param authId
     */
    public void setAuthId(String authId) {
        this.authId = authId;
    }

    /**
     * @return credential
     */
    public String getCredential() {
        return credential;
    }

    /**
     * @param credential
     */
    public void setCredential(String credential) {
        this.credential = credential;
    }
}