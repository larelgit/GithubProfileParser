package org.example;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GitHubProfileAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter GitHub username:");
            String username = scanner.nextLine();

            try {
                GitHubUser user = fetchGitHubUser(username);
                if (user != null) {
                    displayUserInfo(user);
                } else {
                    System.out.println("User not found");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while fetching user data from GitHub");
                e.printStackTrace();
            }
        } finally {
            scanner.close();
        }
    }

    private static GitHubUser fetchGitHubUser(String username) throws IOException {
        URL url = new URL("https://api.github.com/users/" + username);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();

            Gson gson = new Gson();
            return gson.fromJson(content.toString(), GitHubUser.class);
        } else {
            return null;
        }
    }

    private static void displayUserInfo(GitHubUser user) {
        System.out.println("User Info:");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Name: " + user.getName());
        System.out.println("Company: " + (user.getCompany() != null ? user.getCompany() : "N/A"));
        System.out.println("Location: " + (user.getLocation() != null ? user.getLocation() : "N/A"));
        System.out.println("Bio: " + (user.getBio() != null ? user.getBio() : "N/A"));
        System.out.println("Public Repos: " + user.getPublicRepos());
        System.out.println("Followers: " + user.getFollowers());
        System.out.println("Following: " + user.getFollowing());
    }
}


class GitHubUser {
    private String login;
    private String name;
    private String company;
    private String location;
    private String bio;
    private int public_repos;
    private int followers;
    private int following;

    // Getters
    public String getUsername() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getBio() {
        return bio;
    }

    public int getPublicRepos() {
        return public_repos;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }
}