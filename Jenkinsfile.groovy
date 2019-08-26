pipeline {
    /*
     coloco agente que voyo a usar en este caso agente docker
    */
    agent any

    environment {
        IMAGE = 'sample-django'
        VERSION = '1.0.0'
    }

    stages {
        stage('Build and Publish Image'){           
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