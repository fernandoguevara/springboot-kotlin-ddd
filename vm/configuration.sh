
sudo apt update -y &&
sudo apt upgrade -y &&
sudo apt install docker.io -y &&

#identity service to auth our users and services
sudo docker run -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin -p 8080:8080 -d jboss/keycloak &&
#logging visualization from our services
sudo docker run -d -p 9200:9200 -p 5601:5601 nshou/elasticsearch-kibana &&

#You need one database, commment out the one you dont need

#Postgres
sudo docker run -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 postgres
#SQL Server
#sudo docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=IsThisIt2001' -p 1433:1433 -d mcr.microsoft.com/mssql/server:2017-CU8-ubuntu


