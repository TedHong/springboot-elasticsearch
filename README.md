# ElasticSearch in Springboot
1. SpringBoot
   - version : 3.1.0
2. Defendency
- 필수 의존성
```
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
```
3. 기본 파일
    - ElasticSearchConfig.java : ElasticSearch 서버와 연결을 담당하는 설정파일
    - SearchResultRepo.java : ElasticsearchRepository 를 상속받는 인터페이스
    - SearchResultDocument.java : Document 정의 클래스
    - SearchResultController.java : REST Controller 
    - SearchResultService.java : SearchResultRepo 와 연계하는 서비스 클래스
