Write-Host "---------------iniciando script---------------"

Write-Host "--------------parando e excluindo containers em execução---------------"
docker-compose -f .\docker\rinha-compose.yml down

Write-Host "---------------limpando e gerando novo .jar---------------"
mvn clean install -DskipTests

Write-Host "---------------removendo imagem docker anterior---------------"
docker rmi rinha-de-backend-2024-q1-spring:0.0.1-SNAPSHOT

Write-Host "---------------construindo imagem docker nativa com graalvm---------------"
mvn -Pnative spring-boot:build-image -DskipTests

Write-Host "---------------iniciando containers---------------"
docker-compose -f .\docker\rinha-compose.yml up -d

