# Comandos
|Sistema   |Ficheiros   |Editor
|---|---|---
|exit   |pwd   |cat   
|login   |ls   |more   
|sudo   |mkdir   |nano   
|man   |rmdir   |grep
|id   |cp   |sort 
|passwd   |mv   |wc
|   |rm   |  

ls ~ | wc -w -> vê as pastas/ficheiros existentes e manda o resultado para wc contar o número de palavras existentes, que corresponde ao número de ficheiros/pastas.  <br /><br />
cat alunos.txt | grep jose |wc -l -> conta o número de alunos cujo nome é jose.  <br /><br />
ls -l > texto.txt -> envia o output do ls para um fiheiro de texto.

___
# Diretorias  
- / -> raíz
- ~ -> pasta pessoal
- . -> pasta atual
- .. -> pasta superior
- | -> pipe
- ">> >" -> redirecionamento

# Comandos 2
- grep -> imprime linhas com o padrão especificado, ^ é o início de linha e $ é o fim de linha  
grep "^[Aa]na" texto.txt -> procura todos os nomes começados por A ou a dentro do ficheiro texto.txt
- whereis é bom para procurar a localização de coisas como comandos -> whereis pwd
- find ~ -name texto.txt -> procura na pasta pessoal o ficheiro com o nome texto.txt

# Permissões
-  -rw-r--r-- 1 ianmoone ianmoone 1094 Oct 19 09:26 apontamentos.md  
temos o nome do ficheiro, a data da ultima edição (Oct 19 09:26), o tamanho (1094), grupo (ianmoone), dono (ianmoone), permissões (-rw-r--r--) e o tipo (-)  

- 
|rwx   |rw-   |r--
|---|---|---
|aplica-se ao user   |aplica-se ao grupo   |aplica-se aos outros users  
|read, write and execute|read and write|read only  
<br />

- chown aluno.so2 texto.txt -> muda a ownership do ficheiro texto.txt para o grupo so2
  
- rw-rw-r-- -> chmod u=rwx, go-w texto.txt -> o user fica com rwx e o grupo com read only -> rwxr--r--

- chmod 740 texto.txt -> 7 aplica-se ao user, 4 ao grupo e 0 aos outros. 7 dá rwx, 4 dá rw e 0 retira todas as permissões

# Filtros
- head -3 texto.txt -> mostra as primeiras 3 linhas do texto.txt  
<br />
- tail -3 texto.txt -> mostra as últimas 3 linhas do texto.txt (de baixo para cima)  
<br />
- tail +3 texto.txt -> mostra desde a linha 3 até ao fim do texto.txt  
<br />
- ls -lS | head -2 | tail -1 -> dá o ficheiro que ocupa mais espaço na pasta onde o ls é feito  
<br />
- cut ls -lS | head -2 | tail -1 | cut -c 23-26, 40- -> corta da coluna 23 à 26 e da 40 para a frente (mostra o tamanho e o nome do ficheiro)  
<br />  
- cat texto.txt | cut -f 1 -d " " -> corta a coluna 1 de cada linha após o espaço e imprime no terminal  
<br />
- cat texto.txt | tr " " "," -> troca os espaços por virgulas  
<br />
- ls -l | tr -s " " | cut -d " " -f 5,9 -> tr comprime os espaços a mais e é imprimido no ecrã o tamanho e o nome dos ficheiros

# Processos

- Crtl+z - suspender um processo  
- Crtl+c - interromper um processo  

### Comandos

- ps - ver os processos a decorrer  
- kill - enviar um sinal para um processo através do seu PID ou número de processo
- jobs - ver os processos em background
- & - colocar uma aplicação a correr em background  
- fg - voltar a um job que esteja em foreground
- bg - colocar o job a correr em background
- execl - correr um programa atrvés de código em C com uma lista de comandos dada por nós

### Notas
- Ao usar _execl("getenv", "getenv", "NOME", NULL);_ no ex3_f3, o processo pai morre e o filho acaba de correr, logo tudo o que existir depois desta chamada na função pai não é executado.