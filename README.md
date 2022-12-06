## Telegram bot for [Ksu Audit Fund Accounting System](https://github.com/AndreiDevyaterikov/KsuTimetable)

## Technologies
* Gradle 7.5.1 version [Download Gradle](https://gradle.org/next-steps/?version=7.5.1&format=bin)
* Java 17 version

## Setup
* Set Gradle in setting in Intellij Idea -> File | Settings | Build, Execution, Deployment | Build Tools | Gradle
* Set JDK in project settings
* Build project
* Create bot by Telegram
* Set token and username in application.yaml
```yaml
telegram:
  token: ""
  name: "
```
* Also set url for server's requests
```yaml
request:
  url:
```
* Run project and start using bot with /start command
