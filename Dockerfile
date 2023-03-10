FROM openjdk:11
ENV jarName="springbootTemplate.jar"
COPY build/libs/$jarName $jarName
ENTRYPOINT java -Duser.timezone=GMT+8 -jar  $jarName