pipeline {
    agent none
    stages {
        stage('Build') {
            parallel {
                stage('JDK1.8') {
                    agent { docker 'openjdk:8-jdk' }
                    steps {
                        sh './mvnw -fae verify -Pjava8'
                    }
                }
                stage('JDK11') {
                    agent { docker 'openjdk:11-jdk' }
                    steps {
                        sh './mvnw -fae verify -Pjava11'
                    }
                }
            }
        }
    }

}