# thy-common
```
common libraries for Java
```

# build
```
date && mvn clean javadoc:jar deploy -P release,gpg && date
or
date && mvn clean deploy -P release,gpg && date
```

# note
```
must use number, do not use variable
```

# usage
```
<!-- https://mvnrepository.com/artifact/com.geektcp.common/thy-common-config -->
<dependency>
    <groupId>com.geektcp.common</groupId>
    <artifactId>thy-common-config</artifactId>
    <version>1.0.7</version>
</dependency>

<!-- https://mvnrepository.com/artifact/com.geektcp.common/thy-common-core -->
<dependency>
    <groupId>com.geektcp.common</groupId>
    <artifactId>thy-common-core</artifactId>
    <version>1.0.7</version>
</dependency>

<!-- https://mvnrepository.com/artifact/com.geektcp.common/thy-common-spring -->
<dependency>
    <groupId>com.geektcp.common</groupId>
    <artifactId>thy-common-spring</artifactId>
    <version>1.0.7</version>
</dependency>

<!-- https://mvnrepository.com/artifact/com.geektcp.common/thy-common-log -->
<dependency>
    <groupId>com.geektcp.common</groupId>
    <artifactId>thy-common-log</artifactId>
    <version>1.0.7</version>
</dependency>

```