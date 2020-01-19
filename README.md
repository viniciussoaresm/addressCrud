# Crud Basico API

Crud Basico de Endereço com integração com Google Maps

Linguagem : JAVA
Framework : Spring Framework
Porta: 8093


# cURL

Criação:
*Caso um dos campos Latitudes e longitudes for nulo ou vazio é utilizado o CEP para buscar os mesmo vai API Google

curl -X POST \
  http://localhost:8093/v1/address \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 93f71c93-b3d5-2b17-bc4c-6a1fef6291dd' \
  -d '{
"streetName":"TESTE",
"number":"35",
"complement":null,
"neighbourhood":"TESTE2",
"city":"TESTE",
"state":"TESTE",
"country":"TESTE",
"zipcode":"13064806",
"latitude":"",
"longitude":""
}'

Modificação:
curl -X PUT \
  http://localhost:8093/v1/address/3 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 6b71f389-19e2-9f65-d533-4a9cc2e463e0' \
  -d '{
"streetName":"aaa",
"number":"35",
"complement":null,
"neighbourhood":"aaaa",
"city":"aaaa",
"state":"aaa",
"country":"TESTE",
"zipcode":"12344221",
"latitude":"1",
"longitude":"2"
}'

Busca de Todos Registros:
curl -X GET \
  http://localhost:8093/v1/address \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 70be2798-f42b-7aad-e5da-7b557c854a1d'
  
  Busca Por ID:
  
  curl -X DELETE \
  http://localhost:8093/v1/address/3 \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 1b54804c-6629-bb99-3aad-b7d0dcb24111'
  



