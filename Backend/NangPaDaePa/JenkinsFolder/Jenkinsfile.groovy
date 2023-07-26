pipeline {
    agent any
    stages {
        stage('Prepare') {
            steps {
                git branch: 'master',
                    url: 'https://lab.ssafy.com/s09-webmobile1-sub2/S09P12B202.git'
            }

            post {
                success {
                    sh 'echo "Successfully Cloned Repository"'
                }
                failure {
                    sh 'echo "Fail Cloned Repository"'
                }
            }
        }

        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                sh  './gradlew --warning-mode=all --stacktrace clean build -x test'


                sh 'ls -al ./build'
            }
            post {
                success {
                    echo 'gradle build success'
                }

                failure {
                    echo 'gradle build failed'
                }
            }
        }
        stage('Test') {
            steps {
                echo  '테스트 단계와 관련된 몇 가지 단계를 수행합니다.'
            }
        }
        stage('Prune Docker data') {
            steps {
                sh 'echo "Prune Docker data"'
                sh 'docker system prune -a --volumes -f'
            }

            post {
                success {
                    sh 'echo "Prune Docker data Success"'
                }
                failure {
                    sh 'echo "Prune Docker data Fail"'
                }
            }
        }

        stage('Docker Build'){
            steps{
                sh 'echo " Image Bulid Start"'
                sh 'docker build . -t s09-webmobile1-sub2/S09P12B202'
            }
            post {
                success {
                    sh 'echo "Bulid Docker Image Success"'
                }

                failure {
                    sh 'echo "Bulid Docker Image Fail"'
                }
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([string(credentialsId: 'dockerHubPwd', variable: 'dockerHubPwd')]) {
                    sh "docker login -u s09-webmobile1-sub2 -p ${dockerHubPwd}"
                }
                sh 'docker push s09-webmobile1-sub2/S09P12B202'
            }

            post {
                success {
                    echo 'Docker Push success'
                }

                failure {
                    echo 'Docker Push failed'
                }
            }
        }
        stage('Docker Deploy'){
            steps{
                sh 'docker-compose up -d --build'
                sh 'docker-compose ps'
            }
            post {
                success {
                    echo 'docker-compose success'
                }

                failure {
                    echo 'docker-compose failed'
                }
            }
        }
    }
}