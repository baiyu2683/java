package com.zh.entity;

import com.zh.model.UserDTOModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author zhangheng
 * @date 2019/12/04
 */
@Getter
@Setter
public class UserPO {

    private String id;
    private String code;
    private String nickname;
    private UserDTOModel.Sex sex;
    private String password;
    private Date createTime;
    private Date modifyTime;
}
