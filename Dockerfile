FROM eclipse-temurin:17-jre-focal as builder
#docker image prune --filter label=stage=builder
LABEL stage=builder
WORKDIR application
COPY target/next-boot.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

################

FROM gcr.io/distroless/java17-debian11:latest
MAINTAINER xiaoyureed <rainx000@qq.com>
WORKDIR /application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./

ENV JVM_OPTS="-Xmx256m -Xms256m" \
    HALO_WORK_DIR="/root/.halo2" \
    SPRING_CONFIG_LOCATION="optional:classpath:/;optional:file:/root/.halo2/" \
    TZ=Asia/Shanghai

#RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime \
#    && echo $TZ > /etc/timezone

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java ${JVM_OPTS} -Djava.security.egd=file:/dev/./urandom org.springframework.boot.loader.JarLauncher ${0} ${@}"]
