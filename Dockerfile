FROM openjdk:8
ADD target/api-0.0.1-SNAPSHOT.jar httpApi.jar
ADD checkDb.sh checkDb.sh
RUN bash -c 'chmod +x checkDb.sh'
EXPOSE 8080
ENTRYPOINT ["/bin/bash","/checkDb.sh"]