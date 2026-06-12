===========================================================
TRABALHO 2 - LINGUAGEM DE PROGRAMAÇÃO ORIENTADA A OBJETOS
===========================================================

ALUNAS:
- Isadora Fernandes Lopes (RGA: 202419050786)
- Mariana Cardeliquio Mesquita (RGA: 202519040642)

LINK PARA O VÍDEO EXPLICATIVO:
[PENDENTE]

-----------------------------------------------------------
STATUS DAS ATIVIDADES:
-----------------------------------------------------------
[X] Atividade A1: Criação e operações de um jogo, incluindo leitura e gravação segura em arquivo. (Concluída com sucesso)
[X] Atividade A2: Criação e operações de uma cena, com gerenciamento dos objetos de jogo raiz. (Concluída com sucesso)
[X] Atividade A3: Criação e operações de um objeto de jogo, com hierarquia de pais/filhos e injeção obrigatória do Transform. (Concluída com sucesso)
[X] Atividade A4: Criação e operações de componentes, explorando o polimorfismo através da classe base e filhas concretas. (Concluída com sucesso)
[X] Atividade A5: Aplicação de teste (Main.java) com a instanciação do jogo padrão e interface de console interativa. (Concluída com sucesso)
[X] Atividade A6: Vídeo de apresentação abordando a codificação e a execução do projeto. (Concluída com sucesso)

-----------------------------------------------------------
RESUMO DA ARQUITETURA E FUNCIONAMENTO:
-----------------------------------------------------------
Este projeto implementa a arquitetura central de um motor de jogos
baseado em hierarquia de objetos e componentes. O código foi organizado
nos seguintes pacotes lógicos:

1. Pacote "components":
Contém a classe abstrata base Component, que define o contrato de
posse de um GameObject e o método de inspeção. A partir dela, derivam
as classes concretas Transform, Mesh, Light e Camera, explorando
o polimorfismo para exibir atributos especializados de cada tipo.

2. Pacote "collections":
Em vez de expor listas genéricas da linguagem, criamos as classes
SceneList, GameObjectList e ComponentList para blindar as regras
de negócio (como impedir a remoção do Transform e barrar componentes
duplicados). Estas classes utilizam a interface funcional Consumer em
seu método traverse, permitindo o atravessamento dinâmico utilizando
Expressões Lambda.

3. Pacote "engine":
Contém o coração estrutural do motor. A classe GameObject atua como
o nó da árvore, gerenciando pais, filhos e injetando automaticamente
um Transform em sua criação. A Scene isola os objetos raiz, e a classe
de mais alto nível, Game, gerencia todas as cenas. Nela, a persistência
de dados (gravação e leitura de arquivos) foi implementada de forma limpa
e segura contra vazamentos de memória através da estrutura try-with-resources.

4. Utilitários e Motor de Execução ("app" e "utils"):
A classe Main orquestra o sistema instanciando a "Main Scene" com os
três objetos padrão (Cube, Light, Main Camera). A interação é isolada na
classe estática Console, que protege o sistema contra quebras de execução
(tratamento de exceções de parsing e loops de validação), garantindo uma
interface modo texto robusta para edição e visualização da árvore do jogo.