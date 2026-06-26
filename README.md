

1.iniciar docker desktop y crear 
base de datos en temrinal powershell de vsl code

docker compose up mysql
(debe dar mensaje en logs casi al final que dice "ready for connections")

2. iniciar ms individualmente terminal pwshell de vsl code

docker compose up ms-eureka
------------------------------
docker compose up ms-producto
docker compose up ms-cliente
docker compose up ms-usuario
docker compose up ms-inventario
docker compose up ms-venta
docker compose up ms-pedido
docker compose up ms-envio
docker compose up ms-postventa
docker compose up ms-descuento
docker compose up ms-tienda

3. estando creadas imagenes y contenedores (tambien en docker desktop) probar apis y endpoints

http://localhost:8081/doc/swagger-ui/index.html
http://localhost:8082/doc/swagger-ui/index.html
http://localhost:8083/doc/swagger-ui/index.html
http://localhost:8084/doc/swagger-ui/index.html
http://localhost:8085/doc/swagger-ui/index.html
http://localhost:8086/doc/swagger-ui/index.html
http://localhost:8087/doc/swagger-ui/index.html
http://localhost:8088/doc/swagger-ui/index.html
http://localhost:8089/swagger-ui/index.html
http://localhost:8090/doc/swagger-ui/index.html


--muestra json con lista de registros guardados desde navegador swagger


--levantar eureka y docker por separado

ms-usuario
http://localhost:8081/api/v1/usuarios

ms-cliente
http://localhost:8082/api/v1/clientes

ms-producto
http://localhost:8083/api/v1/productos

ms-inventario
http://localhost:8084/api/v1/stock

--hateoas endpoints

http://localhost:8083/api/v1/productos


Correr ms-descuento con eureka local sin docker

--Terminal:

cd MicroServicioDescuento

C:\Users\PC\Downloads\apache-maven-3.9.16-bin\apache-maven-3.9.16\bin\mvn.cmd spring-boot:run

http://localhost:8089/swagger-ui/index.html

--IMPLEMENTACION:

Comentamos ms-descuento en docker-compose.yml

Configuramos application.yml con IP 127.0.0.1 en lugar de hostname

Ejecutamos ms-descuento localmente con Maven

Se registró en Eureka correctamente