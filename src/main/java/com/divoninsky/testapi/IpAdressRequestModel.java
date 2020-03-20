package com.divoninsky.testapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Utility class for helping change IP of SIP
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IpAdressRequestModel {
    private String asteriskServerId;
    private String asteriskServerIpAddress;
}