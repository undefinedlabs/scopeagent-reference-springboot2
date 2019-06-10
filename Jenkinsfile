pipeline {
    agent any

    stages {
        stage('Build') {
            parallel {
                stage('JDK1.8') {
                    steps {
                        sh 'JDK=8-jdk JAVA_PROFILE=java8 COMMIT=${GIT_COMMIT} docker-compose -p 8-jdk-${GIT_COMMIT} build --build-arg JDK="8-jdk" --build-arg JAVA_PROFILE="java8" '
                    }
                }
                stage('JDK11') {
                    steps {
                        sh 'JDK=11-jdk JAVA_PROFILE=java11 COMMIT=${GIT_COMMIT} docker-compose -p 11-jdk-${GIT_COMMIT} build --build-arg JDK="11-jdk" --build-arg JAVA_PROFILE="java11" '
                    }
                }
            }
        }

        stage('Test'){
            parallel {
                stage('JDK1.8') {
                    steps {
                        sh 'JDK=8-jdk COMMIT=${GIT_COMMIT} docker-compose -p 8-jdk-${GIT_COMMIT} up --exit-code-from=scopeagent-reference-springboot2:8-jdk-$COMMIT scopeagent-reference-springboot2:8-jdk-$COMMIT'
                    }
                }
                stage('JDK11') {
                    steps {
                        sh 'JDK=11-jdk COMMIT=${GIT_COMMIT} docker-compose -p 11-jdk-${GIT_COMMIT} up --exit-code-from=scopeagent-reference-springboot2:11-jdk-$COMMIT scopeagent-reference-springboot2:11-jdk-$COMMIT'
                    }
                }
            }
        }
    }

    post {
        always {
            sh 'JDK=8-jdk COMMIT=${GIT_COMMIT} docker-compose -p 8-jdk-${GIT_COMMIT} down -v'
            sh 'JDK=11-jdk COMMIT=${GIT_COMMIT} docker-compose -p 11-jdk-${GIT_COMMIT} down -v'
        }
    }

}