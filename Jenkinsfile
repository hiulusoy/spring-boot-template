pipeline {
    agent any

    environment {
        // Maven ve Docker image ayarları
        MVN_CMD = "mvn clean package -DskipTests"
        IMAGE_NAME = "spring-boot-template-dev"
        IMAGE_TAG = "latest"
        DOCKER_REGISTRY = "localhost" // Yerel registry veya Docker Desktop kullanıyorsanız
        HELM_CHART_PATH = "helm/spring-boot-template"
        VALUES_FILE = "${HELM_CHART_PATH}/values-dev.yaml"
    }

    stages {
        stage('Checkout') {
            steps {
                // Kaynak kodun Git reposundan çekilmesi
                checkout scm
            }
        }
        stage('Build with Maven') {
            steps {
                // Maven ile uygulamayı derliyoruz.
                sh "${MVN_CMD}"
            }
        }
        stage('Docker Build') {
            steps {
                // Docker image oluşturuluyor.
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
            }
        }
        stage('Docker Tag & Push') {
            steps {
                // Yerel ortamda kullanacaksanız image push işlemi opsiyonel olabilir.
                // Örneğin, Docker Desktop kullanıyorsanız, push gerekmeyebilir.
                script {
                    // Eğer özel bir registry kullanıyorsanız, etiketleme ve push komutlarını ekleyin.
                    // sh "docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}"
                    // sh "docker push ${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}"
                    echo "Local environment: Skipping Docker push step."
                }
            }
        }
        stage('Helm Deploy') {
            steps {
                // Helm chart ile Kubernetes cluster'a dağıtım yapılıyor.
                // Bu örnekte, values-dev.yaml dosyası kullanılarak deployment gerçekleştiriliyor.
                sh "helm upgrade --install ${IMAGE_NAME} ${HELM_CHART_PATH} -f ${VALUES_FILE}"
            }
        }
    }

    post {
        always {
            echo "Pipeline tamamlandı."
        }
        failure {
            echo "Pipeline başarısız oldu. Hata detaylarını kontrol edin."
        }
    }
}
