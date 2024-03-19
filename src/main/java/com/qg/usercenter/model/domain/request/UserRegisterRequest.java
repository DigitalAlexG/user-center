package com.qg.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * User info for register.
 *
 * @author qiang
 */
@Data
public class UserRegisterRequest implements Serializable {


    private static final long serialVersionUID = -1422481357995683627L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;
}
