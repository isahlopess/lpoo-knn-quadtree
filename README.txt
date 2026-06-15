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
[X] Atividade A1: Refatoração da classe Quadtree para utilizar Tipos Genéricos (<P extends Point2>). (Concluída com sucesso)
[X] Atividade A2: Criação da classe KNN e implementação dos algoritmos de busca espacial (findNeighbors e forEachNeighbor) na Quadtree. (Concluída com sucesso)
[X] Atividade A3: Criação da classe Particle2 herdando de Point2 para suportar atributos de cor RGB (Polimorfismo). (Concluída com sucesso)
[X] Atividade A4: Aplicação de teste (Main.java) gerando partículas, aplicando Expressões Lambda como filtro de cor nas buscas e formatando a saída de dados. (Concluída com sucesso)
[X] Atividade A5 (Bônus): Adaptação da classe gráfica QuadtreeViewer utilizando curingas de tipos genéricos (wildcards <?>) para suportar a visualização da árvore. (Concluída com sucesso)
[X] Atividade A6: Vídeo de apresentação abordando a codificação, explicação das estruturas espaciais, lambdas e execução do programa. (Concluída com sucesso)

-----------------------------------------------------------
RESUMO DA ARQUITETURA E FUNCIONAMENTO:
-----------------------------------------------------------
Este projeto implementa uma estrutura de dados espacial (Árvore Quaternária) 
focada em alta performance para resolver o problema de K-Nearest Neighbors (KNN). 
O código foi organizado rigorosamente nos pacotes estipulados pela ementa:

1. Pacote "lpoo.geom":
Contém o núcleo geométrico do projeto. A classe Quadtree foi refatorada para 
garantir tipagem segura em tempo de compilação utilizando Generics (<P extends Point2>). 
A classe KNN isola a lógica de ordenação e limite (K) dos pontos mais próximos encontrados. 
Para comprovar o polimorfismo, criamos a classe Particle2, que estende Point2 e adiciona 
atributos RGB. A interface funcional PointFunc<P> foi implementada para ditar o contrato 
de aceitação dos filtros de busca.

2. Pacote "lpoo.math":
Fornece suporte matemático ao projeto, em especial a classe Real, que encapsula a 
margem de erro (epsilon) necessária para comparações precisas de coordenadas de ponto flutuante,
evitando falhas nas bordas das AABBs (Axis-Aligned Bounding Boxes).

3. Motor de Testes e Interação (Pacote "app"):
A classe Main orquestra a aplicação. Ela realiza a leitura do arquivo de coordenadas 
("points.txt"), instancia os objetos do tipo Particle2 injetando cores de forma randômica 
e constrói a árvore genérica. Através da interface robusta encapsulada na classe Console, 
o usuário pode interagir com o sistema.

4. Destaque Técnico (Expressões Lambda e Poda Espacial):
Atendendo à exigência A4, utilizamos Expressões Lambda para instanciar a interface 
PointFunc<P>, filtrando as buscas por raio e por vizinhos (KNN) para retornar exclusivamente 
partículas de uma cor específica (ex: Vermelho). Além disso, a busca recursiva implementa 
"poda geométrica" (descartando quadrantes inteiros da Quadtree caso a distância mínima da 
caixa até o alvo seja superior à maior distância do KNN atual ou ao Raio), garantindo a 
performance esperada da estrutura de dados.