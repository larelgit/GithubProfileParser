# 🐙 GitHub Profile Parser

> CLI utility for fetching and displaying GitHub user profiles right from the terminal.  
> Without opening a browser. Quick and simple.

![Java](https://img.shields.io/badge/Java-8+-orange?logo=openjdk)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?logo=apachemaven)
![GitHub API](https://img.shields.io/badge/GitHub-REST%20API-181717?logo=github)

---

## 🚀 Quick start

### 1. Clone the repository

```bash
git clone https://github.com/larelgit/GithubProfileParser.git
cd GithubProfileParser
```

### 2. Install dependencies & Build

This project uses Maven to manage dependencies (like Google Gson).

```bash
mvn clean install
```

### 3. Look up your first profile!

Run the main class using Maven:

```bash
mvn exec:java -Dexec.mainClass="org.example.GitHubProfileAnalyzer"
```

---

## 📖 Usage

### Basic profile lookup

When you run the application, it will wait for your input. Type any valid GitHub username and press Enter to see their public statistics:

```text
Enter GitHub username: octocat
User Info:
Username: octocat
Name: The Octocat
Company: @github
Location: San Francisco
Bio: N/A
Public Repos: 8
Followers: 13500
Following: 9
```

### Error Handling

If a user doesn't exist, the tool safely handles the `404` response:

```text
Enter GitHub username: thisuserdoesnotexist123456
User not found
```

---

## 📁 Project structure

```text
├── src/main/java/org/example/
│   └── GitHubProfileAnalyzer.java  # Main CLI loop, HTTP client, and JSON data model
├── pom.xml                         # Maven configuration and dependencies (Gson)
└── .gitignore                      # Git ignore rules
```

---

## ⚙️ Requirements

- **Java**: JDK 8 or higher
- **Build Tool**: Apache Maven
- **Network**: Active internet connection to reach `api.github.com`

---

## 🛡️ Security & API Limits

- Connects strictly to the official public `https://api.github.com/users/` endpoint.
- Because it uses unauthenticated requests, it is subject to GitHub's standard rate limits for unauthenticated API access (up to 60 requests per hour per IP).
