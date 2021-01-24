# Informa��es adicionais sobre o projeto

    Nota 1: Para testar as funcionalidades do projeto, foi utilizado o programa "Postman".

	Nota 2: Para a vari�vel "foto" do carro, foi utilizado o m�todo de armazenamento por meio do uso de arrays de bytes,
	em que o programa envia ao banco de dados a vari�vel "foto" do tipo "byte[]". Al�m disso, foram utilizados os m�todos "Deflater" e "Inflater".
	O "Deflater" comprimi os bytes enviados para o banco de dados com o intuito de ocuparem menos espa�o, enquanto o m�todo "Inflater"
	descomprimi tais bytes quando voltam do banco de dados para o seu uso normal.

#### Funcionamento do c�digo
	
- O primeiro m�todo "@GetMapping" � utilizado para enviar ao "HTML" todos os carros que est�o no banco de dados
	para serem visualizados pelo usu�rio.

	Para test�-lo no Postman, escreva: "http://localhost:8080/api/carros".

- O segundo m�todo "@GetMapping"  � utilizado para enviar ao "HTML" apenas um carro que o usu�rio escolheu para
	que ele possa visualizar seus detalhes. � requisitado um id do "HTML" para identificar qual carro ser� devolvido � p�gina.

	Para test�-lo no Postman, escreva: "http://localhost:8080/api/carros/{id}", onde ser� necess�rio substituir "{id}" pela
	vari�vel chave prim�ria "id" do carro que deseja visualizar.

- O m�todo "@PostMapping" � utilizado para o "HTML" enviar ao c�digo uma classe do tipo Carro, em que essa classe
	ser� enviada ao banco de dados como um novo item na tabela.

	Para test�-lo no Postman, escreva: "http://localhost:8080/api/carros", escrevendo os valores desejados para as vari�veis da classe.
	A vari�vel "foto" � uma exce��o, em que � poss�vel envi�-la com um valor ou nulo, pois o c�digo utilizar� de uma imagem
	padr�o que ser� enviada para o banco de dados caso o valor seja nulo. Essa imagem est� guadada dentro do projeto em:
	"\src\main\resources\carroImages\".

- O m�todo "@PutMapping" recebe do "HTML" uma classe do tipo Carro, que atualizar� os valores da classe que tiver o mesmo "id"
	no banco de dados.
 
	Para test�-lo no Postman, escreva: "http://localhost:8080/api/carros/{id}", em que se necessita
	substituir "{id}" pela vari�vel chave prim�ria "id" do carro que deseja atualizar.

- O primeiro m�todo "@DeleteMapping" � utilizado para apagar do banco de dados uma inst�ncia de carro na tabela.
 
	Para test�-lo no Postman, escreva: "http://localhost:8080/api/carros/{id}", onde "{id}" precisa ser substituido pela vari�vel
	chave prim�ria "id" do carro que deseja apagar.

- O segundo m�todo "@DeleteMapping" � utilizado para apagar todas as inst�ncias de carro da tabela do banco de dados.
 
	Para test�-lo no Postman, escreva "http://localhost:8080/api/carros".