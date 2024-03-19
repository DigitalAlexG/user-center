package com.qg.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * user id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * user name
     */
    private String userName;

    /**
     * user account
     */
    private String userAccount;

    /**
     * avatar url
     */
    private String avatarUrl;

    /**
     * gender
     */
    private Integer gender;

    /**
     * user password
     */
    private String userPassword;

    /**
     * phone
     */
    private String phone;

    /**
     * email
     */
    private String email;

    /**
     * user status
     */
    private Integer userStatus;

    /**
     * create time
     */
    private Date createTime;

    /**
     * update time
     */
    private Date updateTime;

    /**
     * whether to delete a user
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}