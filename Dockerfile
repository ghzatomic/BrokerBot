FROM openjdk:8-jre-alpine
VOLUME /tmp
EXPOSE 8080
ENV TZ=America/Sao_Paulo
ADD ./target/BrokerBot-0.0.1-SNAPSHOT.jar /BrokerBot-0.0.1-SNAPSHOT.jar
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java" , "-server","-XX:+CMSParallelRemarkEnabled","-XX:+UseParNewGC" , "-XX:-OmitStackTraceInFastThrow" , "-XX:SurvivorRatio=2","-XX:+CMSConcurrentMTEnabled", "-XX:CMSMaxAbortablePrecleanTime=10000", "-XX:-UseAdaptiveSizePolicy" , "-Xmx1500m", "-jar", "/BrokerBot-0.0.1-SNAPSHOT.jar"]
