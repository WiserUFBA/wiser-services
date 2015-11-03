# SmartUFBA Web Services
---
[![Build Status](https://travis-ci.org/WiserUFBA/wiser-services.svg?branch=master)](https://travis-ci.org/WiserUFBA/wiser-services)

### Introdução

Este repositorio contém todos os Web Services OSGi, que nós utilizamos em nossa sala,
organizados e comentados o suficiente para que você possa criar um novo serviço facilmente.

### Criando novos serviços

Para criar um novo serviço, recomendo primeiramente clonar este repositório em seu computador,
após clonado, você deve abrir uma das pastas do projeto (qualquer uma que contenha um 'pom.xml'
é válida visto que o 'pom.xml' da nossa pasta principal tem um link para as subpastas) e
depois selecionar um projeto, você também pode abrir o projeto diretamente, lembrando-se apenas
que ao criar um novo serviço você deve adicionar um 'modulo' no 'pom.xml' pai da pasta de
projetos que você está criando, esse passo é de extrema importância para manter a estrutura do
projeto.

#### Instruções básicas

Existem 3 passos básicos para criar um novo serviço, com a exceção do passo ainda mais básico
de adicionar o modulo no 'pom.xml' anterior a pasta do seu novo serviço.

1. Criar uma classe que represente a informação obtida pelo serviço, e uma classe que represente
o serviço criado.

2. Modificar o 'pom.xml' na raiz do seu projeto de acordo com os comentários, fazendo mudanças
sempre que necessitar incluir uma nova dependência ou ter que adicionar algum plugin, porém não
se esquecendo de seguir a estrutura básica que consiste em adicionar as dependências importantes
dentro do 'MANIFEST' do seu serviço.

3. Modificar o 'beans.xml' dentro da pasta 'src/main/resources' conforme recomendação oferecida
pelos comentários.

Apesar de existerem apenas esses 3 passos básicos para a criação de um novo serviço, há um passo
extra que não foi informado acima, porém é de extrema importância, caso esteja se utilizando do
maven para fazer o deploy destes serviços no KARAF, que consiste em fazer o deploy do package
final ao repositório do maven utilizado. Caso queira dirija-se ao repositório
'SmartUFBA/wiser-mvn-repo' para mais informações.

### Observações

Apesar que em versões antigas destes mesmos serviços, existir uma recomendação para o uso do
'maven-assembly-plugin' para o deploy do serviço, não necessitando a adição das dependências ao
seu container OSGi, essa não é uma boa prática já que faz com que os serviços ocupem muito
espaço de forma desnecessária, além de criar uma certa redundância em relação aos bundles.

A prática correta a adição de novas dependências a um projeto é a de adiciona-las ao container
OSGi permitindo que diferentes Serviços Web possam se utilizar de classes em comum.


