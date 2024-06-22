---

# Aaio Java SDK

Aaio Java SDK предоставляет удобный способ взаимодействия с API. Вы можете найти примеры использования в тестах.
Получить нужные ключи нужно в кабинете и в настройке магазина (для shop id)

## Установка

### Gradle

1. Добавьте репозиторий в ваш файл settings.gradle:
```groovy
repositories {    
    maven { url "https://jitpack.io" }
}
```    

2. Добавьте зависимость в ваш файл build.gradle:
```groovy
    dependencies {
        implementation 'com.github.itskekoff:AaioPaymentsSDK:1.2.1'
    }
```    

### Maven

1. Добавьте репозиторий в ваш файл pom.xml:
```xml
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
```    

2. Добавьте зависимость в ваш файл pom.xml:
```xml
    <dependency>
        <groupId>com.github.itskekoff</groupId>
        <artifactId>AaioPaymentsSDK</artifactId>
        <version>1.2.1</version>
    </dependency>
```    

## Примеры использования

Примеры использования SDK можно найти в тестах. Вот базовый пример, как начать работу с SDK:

```java
import ru.itskekoff.aaio.client.AaioClient;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AaioClient client = new AaioClient("api_key", "secret_key_no_1", "shop_id");

        System.out.println(client.createBalanceInfoRequest());
    }
}
```

---
