package com.sasa.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class SellerInfo {

    //卖家ID
    @Id
    private String sellerId;
    private String username;
    private String password;
    private String openid;
    private Date createTime;
    private Date updateTime;


}
