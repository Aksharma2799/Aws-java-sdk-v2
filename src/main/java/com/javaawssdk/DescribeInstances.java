package com.javaawssdk;

//snippet-start:[ec2.java2.describe_instances.import]

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesRequest;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;
import software.amazon.awssdk.services.ec2.model.Instance;
import software.amazon.awssdk.services.ec2.model.Reservation;

/**
* Before running this Java V2 code example, set up your development environment, including your credentials.
*
* For more information, see the following documentation topic:
*
* https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
*/
public class DescribeInstances {

 // private static Global logger;
// private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
 public static void main(String[] args) {

     Region region = Region.US_EAST_1;
     Ec2Client ec2 = Ec2Client.builder()
         .region(region)
         .credentialsProvider(ProfileCredentialsProvider.create())
         .build();

    describeEC2Instances(ec2);
    ec2.close();
 }

 // snippet-start:[ec2.java2.describe_instances.main]
 public static void describeEC2Instances( Ec2Client ec2){

     String nextToken = null;
     try {
         do {
             DescribeInstancesRequest request = DescribeInstancesRequest.builder().build();
             DescribeInstancesResponse response = ec2.describeInstances(request);
             System.out.println("\n 𝘿𝙚𝙨𝙘𝙧𝙞𝙗𝙚 𝙄𝙣𝙨𝙩𝙖𝙣𝙘𝙚");
             System.out.println("------------------------------------------------------------------------------------------------------------------"); 
             System.out.printf("| %-20s| %-28s  | %-16s| %-16s| %-18s|%n", "Instance Id","Image id","Instance type","Instance type","monitoring information");
             System.out.println("------------------------------------------------------------------------------------------------------------------");
             for (Reservation reservation : response.reservations()) {
            	 
                 for (Instance instance : reservation.instances()) {
                     System.out.printf("| %-20s| %-28s  | %-16s| %-16s| %-22s|%n", instance.instanceId(),instance.imageId(), instance.instanceType(),instance.state().name(),instance.monitoring().state());
                     System.out.println("------------------------------------------------------------------------------------------------------------------");

//
//                     System.out.println("Instance Id is : " + instance.instanceId());
//                     System.out.println("--------------------------------------------------");                
//                     System.out.println("Image id is : "+ instance.imageId());
//                     System.out.println("--------------------------------------------------");                
//                     System.out.println("Instance type is : "+ instance.instanceType());
//                     System.out.println("--------------------------------------------------");                
//                     System.out.println("Instance state name is : "+ instance.state().name());
//                     System.out.println("--------------------------------------------------");                
//                     System.out.println("monitoring information is : "+ instance.monitoring().state());
//                     System.out.println("--------------------------------------------------");                
                 }
             }
//             nextToken = response.nextToken();
         } while (nextToken != null);

     } 
     catch (Ec2Exception e) {
//         System.err.println(e.awsErrorDetails().errorCode());
//         System.exit(1);
     }
 }
 // snippet-end:[ec2.java2.describe_instances.main]
 
}