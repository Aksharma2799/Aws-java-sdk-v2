package com.javaawssdk;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
//import software.amazon.awssdk.services.iam.model.AttachedPermissionsBoundary;
import software.amazon.awssdk.services.iam.model.IamException;
import software.amazon.awssdk.services.iam.model.ListUsersRequest;
import software.amazon.awssdk.services.iam.model.ListUsersResponse;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.User;
// snippet-end:[iam.java2.list_users.import]

/**
 * Before running this Java V2 code example, set up your development environment, including your credentials.
 *
 * For more information, see the following documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class ListUsers {
    public static void main(String[] args) {

        Region region = Region.AWS_GLOBAL;
        IamClient iam = IamClient.builder()
            .region(region)
            .credentialsProvider(ProfileCredentialsProvider.create())
            .build();

        listAllUsers(iam );
//       System.out.println("\n Done");
//        iam.close();
    }

    // snippet-start:[iam.java2.list_users.main]
    public static void listAllUsers(IamClient iam ) {

        try {

            boolean done = false;
            String newMarker = null;

            while(!done) {
                ListUsersResponse response;
                if (newMarker == null) {
                    ListUsersRequest request = ListUsersRequest.builder().build();
                    response = iam.listUsers(request);
                } else {
                    ListUsersRequest request = ListUsersRequest.builder()
//                        .marker(newMarker)
                        .build();

                    response = iam.listUsers(request);
                }
                System.out.println("𝗟𝗶𝘀𝘁 𝗼𝗳 𝗨𝘀𝗲𝗿𝘀");
                System.out.println("--------------------------------------------------"); 
                System.out.printf("| %-16s| %-28s  |%n", "user","Last login");


                for(User user : response.users()) {
//                    System.out.format("\n Retrieved user  : %s", user.userName());
//                    System.out.format("\n LastPasswordUsed  : %s", user.passwordLastUsed());
                      System.out.println("--------------------------------------------------");
                	  System.out.printf("| %-16s| %-28s  |%n ",user.userName(),user.passwordLastUsed());
//                    AttachedPermissionsBoundary permissionsBoundary = user.permissionsBoundary();
//                    if (permissionsBoundary != null)
//                        System.out.format("\n Permissions boundary details %s", permissionsBoundary.permissionsBoundaryTypeAsString());
                }
                System.out.println("--------------------------------------------------");
                if(!response.isTruncated()) {
                    done = true;
                } else {
//                    newMarker = response.marker();
                }
            }

        }
        catch (IamException e) {
//            System.err.println(e.awsErrorDetails().errorMessage());
//            System.exit(1);
        }
    }
    // snippet-end:[iam.java2.list_users.main]
}