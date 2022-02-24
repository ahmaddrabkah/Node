FROM openjdk:8-jre-alpine

WORKDIR ${application.workdir}
RUN cp ${application.dependencies} ${application.dependencies}
RUN cp ${project.build.finalName}.jar ${project.build.finalName}.jar

ENTRYPOINT ["/usr/bin/java", "-jar", "${project.build.finalName}.jar"]
EXPOSE 5000

