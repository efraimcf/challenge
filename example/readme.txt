Para executar o sistema:

Compile utilizando o Maven;

$ mvn clean package

mova o arquivo para a pasta webapps da instância do Tomcat. (Testado sob Apache Tomcat 8);

Execute o script database.sql contido dentro da pasta 'db' do projeto;

Inicie o tomcat e acesse de acordo com a configuração (verificando pela versão básica, o endereço padrão seria http://localhost:8080/challenge/)

para testes, utilize a biblioteca curl, com os seguintes comandos:

para envio do arquivo com os dados:

$ curl -v -include --form file=@map.txt http://localhost:8080/challenge/registergraph

para utilizar o método de menor caminho, utilize o comando abaixo, substituindo para os valores solicitados:

$ curl -XGET 'http://localhost:8080/challenge/delivery?source=[String]&target=[String]&autonomy=[Double]&gasCost=[Double]'