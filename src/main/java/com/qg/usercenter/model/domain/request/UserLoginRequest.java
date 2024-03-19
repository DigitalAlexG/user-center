package com.qg.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * User info for Login.
 *
 * @author qiang
 */
@Data
public class UserLoginRequest implements Serializable {


    private static final long serialVersionUID = -8840122825117079700L;

    private String userAccount;

    private String userPassword;

}
