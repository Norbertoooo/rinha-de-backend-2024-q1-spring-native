Write-Host "---------------iniciando script---------------"

Write-Host "--------------parando e excluindo containers em execução---------------"
docker-compose -f .\docker\rinha-compose.yml down

Write-Host "---------------limpando e gerando novo .jar---------------"
mvn clean install -DskipTests

Write-Host "---------------removendo imagem docker anterior---------------"
docker rmi rinha-de-backend-2024-q1-spring

Write-Host "---------------construindo imagem docker---------------"
docker build . -t rinha-de-backend-2024-q1-spring

Write-Host "---------------iniciando containers---------------"
docker-compose -f .\docker\rinha-compose.yml up -d

