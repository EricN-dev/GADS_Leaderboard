
package io.ericnjuguna.data_gads.data;


public class SubmitDetails {

    final String firstName;

    final String lastName;

    final String email;

    final String track ;

    final String github;

    public SubmitDetails(String firstName, String lastName, String email, String github) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.github = github;
        this.track = "Android";
    }

}
