/**
 * Copyright (c) 2015 Intel Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.trustedanalytics.servicebroker.hdfs.config.kerberos;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.trustedanalytics.hadoop.config.client.AppConfiguration;
import org.trustedanalytics.hadoop.config.client.Configurations;
import org.trustedanalytics.hadoop.config.client.Property;
import org.trustedanalytics.hadoop.config.client.ServiceInstanceConfiguration;
import org.trustedanalytics.servicebroker.hdfs.config.Profiles;

@Configuration
public class KerberosConfig {

  @Bean
//  @Profile(Profiles.CLOUD)
  public KerberosProperties getKerberosProperties() throws IOException {
    AppConfiguration helper = Configurations.newInstanceFromEnv();
    ServiceInstanceConfiguration krbConf = helper.getServiceConfig("kerberos-service");

    return new KerberosProperties(
        krbConf.getProperty(Property.KRB_KDC).get(),
        krbConf.getProperty(Property.KRB_REALM).get(),
        krbConf.getProperty(Property.USER).get(),
        krbConf.getProperty(Property.PASSWORD).get());
	  
	  //TODO
//	  String kdc="10.1.235.97";
//	  String realm="NODE.DC1.CONSUL";
//	  String user="hdfs";
//	  String password="hdfs";
//	  return new KerberosProperties(kdc, realm, user, password);
  }
}
