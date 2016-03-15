FROM daocloud.io/java:8

ENV TIME_ZONE=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime && echo $TIME_ZONE > /etc/timezone

ENV MAVEN_VERSION 3.3.3

RUN curl -fsSL http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar xzf - -C /usr/share \
  && mv /usr/share/apache-maven-$MAVEN_VERSION /usr/share/maven \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ADD . /app

WORKDIR /app

RUN mvn install  -D maven.test.skip=true

RUN mv ./target/hdfs-broker-1.0.jar .
RUN mv ./target/lib .

EXPOSE 8080

ENV SERVICE_NAME=hdfs-broker

CMD ["java","-jar","hdfs-broker-1.0.jar"]

