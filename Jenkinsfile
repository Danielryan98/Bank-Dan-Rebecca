pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Checkout'){
            steps {
                // Get some code from a GitHub repository
                git branch: 'main', url:'https://github.com/Danielryan98/Bank-Dan-Rebecca'
            }
        }
        stage('Compile'){
            steps{
                sh "mvn clean compile"
            }
        }
        stage('Test'){
            steps {
                sh "mvn test"
            }
        }
        stage('Package') {
            steps {
                sh "mvn package -Dmaven.test.skip=true package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
