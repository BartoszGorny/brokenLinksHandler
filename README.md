# Broken Links Checker

A Java application that checks for broken links on a webpage using Selenium WebDriver and Log4j for logging.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Usage](#usage)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Introduction

This project is a simple tool to verify the links on a webpage and check if they are broken. It uses Selenium WebDriver to navigate the webpage and Log4j for logging the results.

## Features

- Uses Selenium WebDriver for navigating and finding links.
- Logs results using Log4j.
- Runs in headless mode, suitable for CI/CD pipelines.
- Outputs logs to both console and a file.

## Prerequisites

- Java Development Kit (JDK) 8 or higher.
- Maven for dependency management.
- ChromeDriver (compatible with your Chrome browser version).
- A webpage to test (e.g., `https://example.com`).

## Setup

1. **Clone the repository:**

    ```sh
    git clone https://github.com/yourusername/broken-links-checker.git
    cd broken-links-checker
    ```

2. **Configure ChromeDriver:**

    Download ChromeDriver from [here](https://sites.google.com/a/chromium.org/chromedriver/downloads) and place it in a known location. Update the path in the code:

    ```java
    System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
    ```

3. **Install dependencies:**

    ```sh
    mvn clean install
    ```

4. **Configure Log4j:**

    Ensure `log4j2.xml` is placed in the `src/main/resources` directory.

## Usage

1. **Run the application:**

    ```sh
    mvn exec:java -Dexec.mainClass="com.yourpackage.BrokenLinksChecker"
    ```

2. **View logs:**

    - **Console output:** Logs are displayed in the console.
    - **Log file:** Logs are saved to `logs/app.log`.

## Configuration

### Log4j Configuration

You can configure the logging behavior by editing `log4j2.xml` located in the `src/main/resources` directory. Here is an example configuration:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n"/>
        </Console>
        <File name="File" fileName="logs/app.log">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n"/>
        </File>
    </Appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="File"/>
        </Root>
    </Loggers>
</Configuration>
```

## Contributing

Contributions are always welcome!

## License

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)

## Contact

If you have any feedback, please reach out to us at qa.itfactor@gmail.com
