package org.btc.aws;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeSecurityGroupsResponse;
import software.amazon.awssdk.services.ec2.model.SecurityGroup;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;

@SpringBootApplication
public class SecurityGroupApplication {

	  public static void main(String[] args) {
	       try {
	    	   Region region = Region.US_EAST_1;
	 	      Ec2Client ec2 = Ec2Client.builder()
	 	          .region(region)
	 	          .credentialsProvider(ProfileCredentialsProvider.create())
	 	          .build();
	           DescribeSecurityGroupsResponse response = ec2.describeSecurityGroups();
	           for(SecurityGroup group : response.securityGroups()) {
	              System.out.printf(
	                  "Found Security Group with id %s, \n" +
	                          "vpc id %s \n" +
	                          "and description %s\n",
	                  group.groupId(),
	                  group.vpcId(),
	                  group.description());
	           }

	       } catch (Ec2Exception e) {
	           System.err.println(e.awsErrorDetails().errorMessage());
	           System.exit(1);
	       }
	  }
}