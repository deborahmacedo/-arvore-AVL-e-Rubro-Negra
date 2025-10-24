Implementação de Árvores AVL e Rubro-Negra em Java
Este repositório contém a implementação acadêmica de duas estruturas de dados de árvores de busca binária auto-balanceáveis: a Árvore AVL e a Árvore Rubro-Negra (Red-Black Tree).

O projeto foi desenvolvido em Java e demonstra as operações fundamentais de inserção, remoção e busca, incluindo as lógicas de balanceamento específicas de cada árvore (rotações e coloração).

🌳 Estruturas Implementadas
O projeto é dividido em dois pacotes principais:

1. Árvore AVL (src/AVL)
Uma árvore de busca binária que se auto-balanceia usando o fator de balanceamento (FB) de cada nó. As rotações (simples e duplas) são aplicadas durante a inserção e remoção para garantir que a diferença de altura entre as subárvores de qualquer nó seja no máximo 1.

Funcionalidades:

inserir(int valor): Adiciona um novo nó e realiza as rotações (se necessário) para manter o balanceamento.

remover(int valor): Remove um nó e realiza as rotações (se necessário) para rebalancear a árvore.

buscar(int valor): Procura por um valor na árvore.

imprimir(): Métodos para visualização da árvore.

2. Árvore Rubro-Negra (src/RubroNegra)
Uma árvore de busca binária que se auto-balanceia usando um conjunto de regras baseadas em cores (Vermelho ou Preto) atribuídas a cada nó. As regras garantem que o caminho mais longo da raiz até qualquer folha não seja mais que o dobro do caminho mais curto.

Funcionalidades:

inserir: Adiciona um novo nó (inicialmente Vermelho) e aplica as regras de coloração e rotações para corrigir violações.

remover: Remove um nó e aplica casos de rebalanceamento complexos para manter as propriedades da árvore.

buscar: Procura por um valor na árvore.

imprimir(): Método para visualização da árvore.

💻 Tecnologias Utilizadas
Linguagem: Java
IDE: IntelliJ IDEA (evidenciado pela pasta .idea)

🚀 Como Executar
O projeto contém a Main separadas para testar cada estrutura de dados.

Clone o repositório:

Bash

git clone https://github.com/deborahmacedo/-arvore-AVL-e-Rubro-Negra.git

Abra o projeto em sua IDE Java (ex: IntelliJ IDEA ou Eclipse).

Para testar a Árvore AVL:

Execute o arquivo src/AVL/Main.java.

Para testar a Árvore Rubro-Negra:

Execute o arquivo src/RubroNegra/Main.java.
