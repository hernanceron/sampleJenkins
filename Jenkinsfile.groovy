pipeline {
    /*
     coloco agente que voyo a usar en este caso agente docker
    */
    agent {
        node {
            label 'docker'
        }
    }

    environment {
        IMAGE = 'sample-django'
        VERSION = '1.0.0'
    }

    stages {

        stage('Build and Publish Image'){
            agent {
                docker {
                    reuseNode true
                    image 'python:3.7'
                }
            }
            when {
                branch 'master'
            }
            steps {
                sh """
                    docker build -t ${IMAGE} .
                    docker tag ${IMAGE} ${IMAGE}:${VERSION}
                    docker push ${IMAGE}:${VERSION}
                """
            }
        }
    }

}