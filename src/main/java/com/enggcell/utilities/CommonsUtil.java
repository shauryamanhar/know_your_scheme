/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enggcell.utilities;

import com.enggcell.Constants.TransactionStatusEnum;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author 1003
 */
@Component
@Scope(value = "request")
public class CommonsUtil {

    public String getIPAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
 /**
     * Transaction Status
     *
     * @return
     */
    public static List<String> transactionStatus() {
        List<String> list = new ArrayList<>();
        list.add(TransactionStatusEnum.SUCCESS.toString());
        list.add(TransactionStatusEnum.FAILED.toString());
        list.add(TransactionStatusEnum.CANCELED.toString());
        return list;
    }
}
