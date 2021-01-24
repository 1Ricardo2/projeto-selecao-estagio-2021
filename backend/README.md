# Informações adicionais sobre o projeto

    Nota 1: Para testar as funcionalidades do projeto, foi utilizado o programa "Postman".

	Nota 2: Para a variável "foto" do carro, foi utilizado o método de armazenamento por meio do uso de arrays de bytes,
	em que o programa envia ao banco de dados a variável "foto" do tipo "byte[]". Além disso, foram utilizados os métodos "Deflater" e "Inflater".
	O "Deflater" comprimi os bytes enviados para o banco de dados com o intuito de ocuparem menos espaço, enquanto o método "Inflater"
	descomprimi tais bytes quando voltam do banco de dados para o seu uso normal.

#### Funcionamento do código
	
- O primeiro método "@GetMapping" é utilizado para enviar ao "HTML" todos os carros que estão no banco de dados
	para serem visualizados pelo usuário.

	Para testá-lo no Postman, escreva: "http://localhost:8080/api/carros".

- O segundo método "@GetMapping"  é utilizado para enviar ao "HTML" apenas um carro que o usuário escolheu para
	que ele possa visualizar seus detalhes. É requisitado um id do "HTML" para identificar qual carro será devolvido á página.

	Para testá-lo no Postman, escreva: "http://localhost:8080/api/carros/{id}", onde será necessário substituir "{id}" pela
	variável chave primária "id" do carro que deseja visualizar.

- O método "@PostMapping" é utilizado para o "HTML" enviar ao código uma classe do tipo Carro, em que essa classe
	será enviada ao banco de dados como um novo item na tabela.

	Para testá-lo no Postman, escreva: "http://localhost:8080/api/carros", escrevendo os valores desejados para as variáveis da classe.
	A variável "foto" é uma exceção, em que é possível enviá-la com um valor ou nulo, pois o código utilizará de uma imagem
	padrão que será enviada para o banco de dados caso o valor seja nulo. Essa imagem está guadada dentro do projeto em:
	"\src\main\resources\carroImages\".

- O método "@PutMapping" recebe do "HTML" uma classe do tipo Carro, que atualizará os valores da classe que tiver o mesmo "id"
	no banco de dados.
 
	Para testá-lo no Postman, escreva: "http://localhost:8080/api/carros/{id}", em que se necessita
	substituir "{id}" pela variável chave primária "id" do carro que deseja atualizar.

- O primeiro método "@DeleteMapping" é utilizado para apagar do banco de dados uma instância de carro na tabela.
 
	Para testá-lo no Postman, escreva: "http://localhost:8080/api/carros/{id}", onde "{id}" precisa ser substituido pela variável
	chave primária "id" do carro que deseja apagar.

- O segundo método "@DeleteMapping" é utilizado para apagar todas as instâncias de carro da tabela do banco de dados.
 
	Para testá-lo no Postman, escreva "http://localhost:8080/api/carros".