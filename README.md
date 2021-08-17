# Desafio Android

## Criando um App de Scroll Infinito pra NASA

### **Descrição do aplicativo**
O desafio é criar um aplicativo para mostrar as fotos de um dos conteúdos mais populares da NASA, o que queremos é que você construa um aplicativo de Scroll Infinito para que possamos apreciar este maravilhoso conteúdo.

### **Recursos**
-   **Visuais**: Optamos  por não dar uma linha de design e fica a critério do candidato qual design escolher.
-   **API**. Exemplo de chamada na API:  `https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY`

|Parameter  |Type 		|Default 	| Description |
|-----------|-----------|-----------|-----------------------------------------------|
|date		|YYY-MM-DD	|today 		|The date of the APOD image to retrieve			|
|hd			|bool		|False      |Retrieve the URL for the high resolution image |
|api_key	|string		|DEMO_KEY	|api.nasa.gov key for expanded usage			|

-   **Ref. conteúdo**: [https://apod.nasa.gov/apod/](https://apod.nasa.gov/apod/ "https://apod.nasa.gov/apod/")

-   Linguagem primaria Kotlin
-   Projeto usando arquitetura MVP 
-   Kotlin Coroutines 
-   Koin para injeção de dependecias 
-   Lottie para aniamações
-   LiveData 

![onboarding_nasa (1)](https://user-images.githubusercontent.com/47648982/129750716-1caf0bd6-4af5-4f80-81a7-80b518708537.jpg)

Exemplo da tela de boas vindas. 

![nasa_home (1)](https://user-images.githubusercontent.com/47648982/129750757-63278e60-f77b-46f0-9510-e23cfd9b02f8.jpg)

Exemplo da tela home do aplicativo. 

![nasa_detatails (1)](https://user-images.githubusercontent.com/47648982/129750821-cf09c6ee-95dd-47f2-ac66-d0bfaf408e7e.jpg)

Assim que clicado em uma imagem abre uma pagina de detalhes com a descrição da foto. 



