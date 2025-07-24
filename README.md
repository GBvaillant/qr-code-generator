# QR Code Generator API

Este projeto é uma API desenvolvida com **Spring Boot** que gera códigos QR a partir de textos ou URLs enviados via requisição HTTP. A imagem gerada do QR code é armazenada em um bucket do **S3** (simulado com o **LocalStack**) e a URL da imagem é retornada como resposta.

## 🚀 Tecnologias Utilizadas

* Java 21
* Spring Boot
* ZXing (para geração de QR codes)
* AWS SDK v2 (para integração com S3)
* LocalStack (para simulação de serviços AWS)

## 🧪 Como testar

### Pré-requisitos

* Java 21
* Maven
* Docker (para rodar a aplicação e LocalStack)

### Clone o repositório

```bash
git clone https://github.com/GBvaillant/qr-code-generator.git
cd qr-code-generator

```

### Subir a Aplicação + LocalStack com S3

```bash
docker compose up --build
```

### Criar bucket manualmente

```bash
aws --endpoint-url=http://localhost:4566 s3 mb s3://qr-code-bucket-test
```

> Certifique-se de que o `endpoint`, `region`, `accessKey` e `secretKey` estejam configurados corretamente no seu `application.properties` ou via configuração do SDK.

### Enviar requisição via Postman

* Método: `POST`
* URL: `http://localhost:8080/qrcode`
* Body (raw / JSON):

```json
{
  "text": "https://github.com/GBvaillant/qr-code-generator"
}
```

### Resposta:

```json
{
  "text": "http://localstack:4566/qr-code-bucket-test/262a6c64-37c8-40eb-828d-888fe9aa46d8"
}
```

## QR-CODE Gerado 📷
![QR Code Example](./assets/qr-code-image.png)


Feito por [Gabriel Vaillant](https://github.com/GBvaillant)
