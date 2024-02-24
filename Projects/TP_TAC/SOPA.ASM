.8086
.model small
.stack 2048h

dseg    segment para public 'data'
    	palavras 		db		250 dup(?),0
    	Erro_Open       db      'Erro ao tentar abrir o ficheiro$'
        Erro_Ler_Msg    db      'Erro ao tentar ler do ficheiro$'
        Erro_Close      db      'Erro ao tentar fechar o ficheiro$'
        
		; FICHEIROS 
		Fich         	db      'DADOS.TXT', 0
		Menu_prin		db		'MENU.TXT', 0  								; Menu Principal
		jogo_acabou		db		'GAMEOVER.TXT', 0							; Meno Game_Over
		player_won		db		'WINNER.TXT', 0  							; Menu Winner
		top10fich		db		'TOP10.TXT', 0  							; Menu Top10
		palavrasfich	db		'PALAVRAS.TXT', 0  							; Palavras

		palavraspos		db		'1 01 07|5 00 00|1 03 09|4 11 00|1 00 11|4 01 02|4 06 00|6 10 01|$',0
						
		direccao		db 		0			

		; VARIAVEIS DO JOGO
		flag_ini		db 		0
		n_palavras		db 		8
		contador		db 		0
		contador2		db 		'_'

		conta_tentativas db 1

		conta_tentativas_bck db '_'

		tempo			db		'Tempo: $',0
		encontradas		db		'Encontradas: $', 0
		tentativas		db		'Tentativas: $', 0

		; TRATA DAS HORAS

		STR5	 		DB 		"     "	; String para 12 digitos	
		NUMERO		DB		"                    $",0 	; String destinada a guardar o número lido

		Minutos		dw		0				; Vai guardar os minutos atuais
		Segundos		dw		0				; Vai guardar os segundos atuais
		Minutos_fin		dw		0				; Vai guardar os minutos iniciais
		Segundos_fin		dw		0				; Vai guardar os segundos iniciais
		Old_seg		dw		0				; Guarda os últimos segundos que foram lidos

					
		NUMDIG	db	0	; controla o numero de digitos do numero lido
		MAXDIG	db	4	; Constante que define o numero MAXIMO de digitos a ser aceite

		nomeplayerTEXT	db		'AAA$', 0
		STR2cmp			db 		20 dup(?),'$'					; String de 20 digitos
		STR2BLD			db 		20 dup(?),'$'				; String de 20 digitos
		msg1  			db 		'Palavra Encontrada$',0
		msg2  			db 		'Palavra a encontrar..: $',0
		msg3  			db 		'Selecionado..........: $',0

		tabuleiro		db 		'  * * * * * * * * * * * * * *', 13, 10
						db		12 dup('  * ? ? ? ? ? ? ? ? ? ? ? ? *', 13, 10)
						db 		'  * * * * * * * * * * * * * *$',13, 10
		
		top10Temp			db 	350 dup(?)  ;String utilizada para copia temporaria do top10

		ultimo_num_aleat 	dw 		0
		str_num 			db 		5 dup(?),'$'
	
        HandleFich      dw      0
        car_fich        db      ?
		
		Car				db		32				; Guarda um caracter do Ecran 
		Cor				db		7				; Guarda os atributos de cor do caracter
		
		POSy			db		1				; a linha pode ir de [1 .. 25]
		POSx			db		0				; POSx pode ir [1..80]	
		posX_ini		db		4
		posY_ini		db		2
		auxVar		DB 		?
		username	DB		'Introduza suas iniciais(3 Letras):$';String caso utilizador tenha entrado no TOP10
dseg    ends


cseg    segment para public 'code'
		assume  cs:cseg, ds:dseg


; #############################
goto_xy	macro		POSx,POSy
		mov		ah,02h
		mov		bh,0					; numero da página
		mov		dl,POSx
		mov		dh,POSy

		int		10h	
endm

coloring macro  posx, posy
		goto_xy posX, posy
		mov ah, 09h ; chama fnc
		mov cx, 1	; diz quantos caracteres
		mov bl, 2fh ;	
		int 10h
endm

mostra macro str
	mov ah, 09h
	lea dx, str
	int 21h
endm


Main    Proc

        mov     ax,dseg
        mov     ds,ax
		mov		ax,0B800h
		mov		es,ax
		lea dx, top10fich
		call Copiar_Top10 

Menu:
		call		apaga_ecran
		goto_xy		0,0

		lea			dx, Menu_prin  		
		call		imp_Ficheiro  		

		mov  ah, 07h 					
  		int  21h
  		cmp  al, '1' 					
  		je   inicio 					
  		cmp  al, '2' 					
  		je   top10 						
		cmp  al, '3' 					
		je  sair 						
		jmp Menu 	

inicio:
		mov flag_ini, 0
		xor di, di
reset:
		mov STR2BLD[di],  0
		mov STR2cmp[di],  0
		
		inc di
		cmp di, 15
		jb reset
		
		xor di, di

		mov contador, 0
		mov contador2, '_'

		mov AH, 2CH             ; Buscar a hORAS
		int 21H    

		XOR AX,AX
		mov AL, DH              ; segundos para al
		mov Segundos, AX		; guarda segundos na variavel correspondente
		
		XOR AX,AX
		mov AL, CL              ; Minutos para al
		mov Minutos, AX         ; guarda MINUTOS na variavel correspondente

		XOR BL, BL
		mov bl, 60
		mul bl

		add AX, Segundos
		adc al, 0
			 
		mov segundos, ax


		call	apaga_ecran


		lea		dx, palavrasfich		; ler de PALAVRAS.txt para a matriz
		call	LER_FICH

		xor		cx, cx
		mov 	cl, n_palavras

		xor 	bx, bx

		mov 	posX, 32
		mov 	posY, 2

coluna_dados:	
		goto_xy	posX, POSy
		lea     dx, palavras[bx]
		call	imp_String

		add 	bl, 20
		adc		bh, 0

		inc 	POSy

		loop 	coluna_dados

		inc 	POSy
		goto_xy	posX, POSy
		lea 	dx, tempo
		call 	imp_String

		inc 	POSy
		goto_xy	posX, POSy
		lea 	dx, encontradas
		call 	imp_String

		inc 	POSy
		goto_xy	posX, POSy
		lea 	dx, tentativas
		call 	imp_String

		mov al, conta_tentativas
		add al, 30h
		goto_xy 44, 13
		mov		ah, 02h					; IMPRIME caracter da posição no canto
		mov		dl, al	
		int		21H	

		goto_xy	2, 16
		MOSTRA 	msg2
		goto_xy	2, 17
		MOSTRA 	msg3

		call Preenche_Tabuleiro

		goto_xy	0,1

		lea		dx, tabuleiro
		call	imp_String

		call Imp_Palavras

		mov 	al, posX_ini
		mov 	POSx, al
		mov 	al, posY_ini
		mov 	POSy, al

		call	assinala_P

		;jmp inicio
		;cmp 	flag_ini, 1
		;je		inicio
		call Compara_Top10

		mov  	ah, 07h 				; Espera para que o utilizador clique em alguma coisa
		int  	21h	   
	   	jmp		menu


top10:

		call	apaga_ecran
		goto_xy 0,0
		;lea  	dx, top10fich   		; carregar para dx o ficheiro que queremos imprimir
    	;call 	imp_Ficheiro   			; imprimir o ficheiro
		lea dx, top10fich
		call Copiar_Top10

		mov  	ah, 07h 				; Espera para que o utilizador clique em alguma coisa
		int  	21h	   
		jmp		menu

sair:
		call		apaga_ecran
        goto_xy 	0,0
		mov			ah,4CH   			;parte do Interrupt para sair
		int			21H   				;Interrupt para sair
		ret

Main	endp

apaga_ecran	proc
		xor		bx,bx
		mov		cx,25*80
		
apaga:	mov		byte ptr es:[bx],' '
		mov		byte ptr es:[bx+1],7
		inc		bx
		inc 	bx
		loop	apaga
		ret
apaga_ecran	endp

assinala_P	PROC

		push ax
		push bx
		push dx
		push si
		push di

CICLO:			
		goto_xy	POSx,POSy				; Vai para nova posição
		mov 	ah, 08h
		mov		bh,0					; numero da página
		int		10h		
		mov		Car, al					; Guarda o Caracter que está na posição do Cursor
		mov		Cor, ah					; Guarda a cor que está na posição do Cursor
		
		goto_xy	78,0					; Mostra o caractereque estava na posição do AVATAR
		mov		ah, 02h					; IMPRIME caracter da posição no canto
		mov		dl, Car	
		int		21H			
	
		goto_xy	POSx,POSy				; Vai para posição do cursor
		
LER_SETA:	
		call 	LE_TECLA
		cmp		ah, 1
		je		ESTEND
		
		cmp 	AL, 27					; ESCAPE
		JE		fim
		cmp		AL, 13
		je		ASSINALA
		jmp		LER_SETA
		
ESTEND:	cmp 	al,48h
		jne		BAIXO
		
		dec		POSy					;cima

		goto_xy POSx, POSy
		mov 	ah, 08h
		mov		bh,0
		int		10h	
		cmp     al, 2AH		
		je		outofboundcima

		jmp		CICLO

outofboundcima:
	inc 	POSy
	jmp 	ciclo	

BAIXO:	cmp		al,50h
		jne		ESQUERDA
		
		inc 	POSy					;Baixo

		goto_xy POSx, POSy
		mov 	ah, 08h
		mov		bh,0
		int		10h	
		cmp     al, 2AH		
		je		outofboundbaixo

		jmp		CICLO

outofboundbaixo:
	dec 	POSy
	jmp 	ciclo	

ESQUERDA:
		cmp		al,4Bh
		jne		DIREITA

		dec		POSx					;Esquerda
		dec		POSx					;Esquerda

		goto_xy POSx, POSy
		mov 	ah, 08h
		mov		bh,0
		int		10h	
		cmp     al, 2AH	
		je		outofboundesquerda
		
		jmp		CICLO

outofboundesquerda:
	inc 	POSx
	inc 	POSx
	jmp 	ciclo	

DIREITA:
		cmp		al,4Dh
		jne		LER_SETA 

		inc		POSx					;Direita
		inc		POSx					;Direita

		goto_xy POSx, POSy
		mov 	ah, 08h
		mov		bh,0
		int		10h	
		cmp     al, 2AH		
		je		outofbounddireita

		jmp		CICLO

outofbounddireita:
		dec 	POSx
		dec 	POSx
		jmp 	ciclo	

				; int 10,9 - Write Character and Attribute at Cursor Position
				; AH = 09
				; AL = ASCII character to write
				; BH = display page  (or mode 13h, background pixel value)
				; BL = character attribute (text) foreground color (graphics)
				; CX = count of characters to write (CX >= 1)
ASSINALA:

		goto_xy	POSx,POSy

		mov		bl, cor
		not		bl
		mov		cor, bl
		mov 	ah, 09h
		mov		al, car
		mov		bh, 0
		mov		cx, 1
		int		10h

		xor ax, ax

		cmp conta_tentativas_bck, '_'
		jne compara_tentativas

		mov conta_tentativas_bck, 1 ; inicia backup

compara_tentativas:		

		mov al, conta_tentativas
		mov ah, conta_tentativas_bck
		cmp al, ah
		je continua1

		mov flag_ini, 1
		jmp fim


continua1:

		cmp contador2,'_'  ; ve se n'ao foi inicializado
		jne compara_contadores

		mov contador2, 0  ; inicializa a 0
		jmp muda_palavra

compara_contadores:				
		mov al, contador				; contador geral que se altera quando se enontra uma palavra
		mov ah, contador2				
		cmp al, ah						; se forem diferentes, foi porque o contador mudou e ]e preciso mudar a palavra
		jne continua2

muda_palavra:
		call Copia_Palavra
		inc contador2
		xor si, si
		
continua2:
		goto_xy	25, 16
		MOSTRA 	STR2cmp
		xor 	ax, ax
		mov 	al, car
		mov 	STR2BLD[si], al
		inc 	si
		goto_xy 25, 17			
		MOSTRA STR2BLD
		
		

		call Compara_Palavras
		cmp contador, 1
		je FIM

		jmp		CICLO

FIM:	
		
		inc conta_tentativas_bck
		call Compara_Top10

		pop di
		pop si
		pop dx
		pop bx
		pop ax
		
		;call main
		ret
assinala_P	ENDP

Ler_TEMPO PROC	
 
		PUSH AX
		PUSH BX
		PUSH CX
		PUSH DX
	
		PUSHF
		
		mov AH, 2CH             ; Buscar a hORAS
		int 21H                 
		
		XOR AX,AX
		mov AL, DH              ; segundos para al
		mov Segundos_fin, AX		; guarda segundos na variavel correspondente

		XOR AX,AX
		mov AL, CL              ; Minutos para al
		mov Minutos_fin, AX         ; guarda MINUTOS na variavel correspondente
		
		XOR BX, BX 
		mov bl, 60
		mul bl						; multiplica os minutos por 60

		add AX, Segundos_fin  
		adc ah, 0

		mov segundos_fin, ax

		XOR BX, BX
		mov bx, segundos

		sub segundos_fin, bx

		POPF
		POP DX
		POP CX
		POP BX
		POP AX
 		ret 
Ler_TEMPO   ENDP 

Trata_Horas PROC

		PUSHF
		PUSH AX
		PUSH BX
		PUSH CX
		PUSH DX		

		call 	Ler_TEMPO				; Horas MINUTOS e segundos do Sistema

		cmp 	segundos, 0
		jne 	continua

continua:
		
		mov		AX, Segundos_fin
		cmp		AX, Old_seg			; VErifica se os segundos mudaram desde a ultima leitura
		je		fim_horas			; Se a hora não mudou desde a última leitura sai.
		mov		Old_seg, AX			; Se segundos são diferentes actualiza informação do tempo 	
		
		mov 	ax,Segundos_fin	
		mov 	bl, 100     
		div 	bl
		add 	al, 30h		
		mov 	STR5[0],al			
		
		mov 	AL,AH	
		mov 	AH, 0
		mov 	bl, 10     
		div 	bl
		add 	al, 30h				; Caracter Correspondente às dezenas
		add		ah,	30h				; Caracter Correspondente às unidades
		mov 	STR5[1],al			; 
		mov 	STR5[2],ah
		mov 	STR5[3],'s'		
		mov 	STR5[4],'$'	
		GOTO_XY	39,11
		MOSTRA	STR5 				
		
						
fim_horas:		
		goto_xy	POSx,POSy			; Volta a colocar o cursor onde estava antes de actualizar as horas
		
		POPF
		POP DX		
		POP CX
		POP BX
		POP AX
		ret		
			
Trata_Horas ENDP

LE_TECLA	PROC

sem_tecla:
		call Trata_Horas
		mov	AH,0BH
		int 21h
		cmp AL,0
		je	sem_tecla

		mov		ah,08h
		int		21h
		mov		ah,0
		cmp		al,0
		jne		SAI_TECLA
		mov		ah, 08h
		int		21h
		mov		ah,1

SAI_TECLA:	
		ret

LE_TECLA	ENDP


imp_String proc
	;output the string
	;loaded in dx 
	mov AH, 09H
	int 21h

sai:
	ret

imp_String	endp

;########################################################################
;ROTINA PARA IMPRIMIR FICHEIRO NO ECRAN
imp_Ficheiro	Proc
;abre ficheiro
        mov     ah,3dh
        mov     al,0
        ;lea     dx,Fich 					; em vez da funcao ser so para um ficheiro, antes de ser chamada indica-se o ficheiro a ser imprimido	 
        int     21h
        jc      erro_abrir
        mov     HandleFich, ax
        jmp     ler_ciclo

erro_abrir:
        mov     ah,09h
        lea     dx,Erro_Open
        int     21h
        jmp     sai

ler_ciclo:
        mov     ah,3fh
        mov     bx,HandleFich
        mov     cx,1
        lea     dx,car_fich
        int     21h
		jc		erro_ler
		cmp		ax,0		;EOF?
		je		fecha_ficheiro
        mov     ah,02h
		mov		dl,car_fich
		int		21h
		jmp		ler_ciclo

erro_ler:
        mov     ah,09h
        lea     dx,Erro_Ler_Msg
        int     21h

fecha_ficheiro:
        mov     ah,3eh
        mov     bx,HandleFich
        int     21h
        jnc     sai

        mov     ah,09h
        lea     dx,Erro_Close
        Int     21h
sai:
		ret
imp_Ficheiro	endp


LER_FICH	PROC

		;abre ficheiro
        mov     ah,3dh
        mov     al,0
		mov		di,0
        int     21h
        jc      erro_abrir
        mov     HandleFich,ax
        jmp     ler_ciclo

erro_abrir:
        mov     ah,09h
        lea     dx,Erro_Open
        int     21h
        jmp     sai

ler_ciclo:
        mov     ah,3fh
        mov     bx,HandleFich
        mov     cx,1
        lea     dx,car_fich
        int     21h
		jc		erro_ler
		cmp		ax,0		
		je		fecha_ficheiro
		mov		dl, car_fich
		mov		palavras[di],dl
		inc		di
		jmp		ler_ciclo

erro_ler:
        mov     ah,09h
        lea     dx,Erro_Ler_Msg
        int     21h

fecha_ficheiro:
        mov     ah,3eh
        mov     bx,HandleFich
        int     21h
        jnc     sai

        mov     ah,09h
        lea     dx,Erro_Close
        Int     21h

sai:	
		ret
		
LER_FICH	endp


Preenche_Tabuleiro proc

; primeira posição: x - 4 || y - 2
; Tam X - 
; Tam Y - 

		push si

		xor si, si


ciclo:

		inc si

		cmp tabuleiro[si], 36		; até encontrar o $
		je 	sai

		cmp tabuleiro[si], 63		; compara com ?
		jne ciclo

calcula_aleatorio:

		call CalcAleat
		pop ax

		cmp al, 41h
		jb calcula_aleatorio
		cmp al, 5ah
		ja calcula_aleatorio
		
		mov tabuleiro[si], al 

		jmp ciclo	

SAI:
		pop si
		ret

Preenche_Tabuleiro endp 


Imp_Palavras proc
		;Imprimir no tabuleiro

		push ax
		push bx
		push cx
		push si
		push di
		

		xor ax, ax
		xor bx, bx
		xor cx, cx
		xor si, si
		xor di, di
		
ciclo:
		mov posx, 0
		mov posy, 0


		mov al, palavraspos[si]
		mov direccao, al
		inc si
		inc si
		
		mov al, palavraspos[si]
		inc si
		sub al,30h
		mov bl,10
		mul bl
		mov ah, palavraspos[si]
		sub ah,30h
		add al,ah

		; ajustar ao tabuleiro
		mov bl, 2
		mul bl

		mov POSx,al
		add POSx, 4
		; primeira posicao está em al

		inc si
		inc si
		mov al, palavraspos[si]
		inc si
		sub al,30h
		mov bl,10
		mul bl
		mov ah, palavraspos[si]
		sub ah,30h
		add al,ah
		mov POSy,al
		add POSy, 2
		inc si
		inc si

		; escrever  na grelha

drt:
		cmp direccao, '1'
		jne esq

escreve_drt:
		mov al,palavras[di]
		cmp al,'.'
		je fimPal	
		inc di
		goto_xy posX, posY
	
		mov		ah, 02h					; IMPRIME caracter da posição no canto
		mov		dl, al	
		int		21H	

		inc POSx
		inc posX
		
		jmp escreve_drt

esq:
		cmp direccao, '2'
		jne cima

escreve_esq:

		mov al,palavras[di]
		cmp al,'.'
		je fimPal	
		inc di
		goto_xy posX, posY
	
		mov		ah, 02h					; IMPRIME caracter da posição no canto
		mov		dl, al	
		int		21H	

		dec posX
		dec posX
		
		jmp escreve_esq


cima:
		cmp direccao, '3'
		jne baixo

escreve_cima:

		mov al,palavras[di]
		cmp al,'.'
		je fimPal	
		inc di
		goto_xy posX, posY
	
		mov		ah, 02h					; IMPRIME caracter da posição no canto
		mov		dl, al	
		int		21H	

		dec POSy
		
		jmp escreve_cima

baixo:
		cmp direccao, '4'
		jne baixo_drt

escreve_baixo:

		mov al,palavras[di]
		cmp al,'.'
		je fimPal	
		inc di
		goto_xy posX, posY
	
		mov		ah, 02h					; IMPRIME caracter da posição no canto
		mov		dl, al	
		int		21H	

		inc POSy
		
		jmp escreve_baixo

baixo_drt:
		cmp direccao, '5'
		jne baixo_esq

escreve_baixo_drt:

		mov al,palavras[di]
		cmp al,'.'
		je fimPal	
		inc di

		goto_xy posX, posY
	
		mov		ah, 02h					; IMPRIME caracter da posição no canto
		mov		dl, al	
		int		21H	

		inc POSy
		inc posX
		inc posX
		
		jmp escreve_baixo_drt	

baixo_esq:
		cmp direccao, '6'
		jne cima_esq

escreve_baixo_esq:

		mov al,palavras[di]
		cmp al,'.'
		je fimPal	
		inc di
		goto_xy posX, posY
	
		mov		ah, 02h					; IMPRIME caracter da posição no canto
		mov		dl, al	
		int		21H	

		inc POSy
		dec posX
		dec posX
		
		jmp escreve_baixo_esq		

cima_esq:
		cmp direccao, '7'
		jne cima_drt

escreve_cima_esq:

		mov al,palavras[di]
		cmp al,'.'
		je fimPal	
		inc di
		goto_xy posX, posY
	
		mov		ah, 02h					; IMPRIME caracter da posição no canto
		mov		dl, al	
		int		21H	

		dec POSy
		dec posX
		dec posX
		
		jmp escreve_cima_esq	

cima_drt:
		cmp direccao, '8'
		jne sai

escreve_cima_drt:

		mov al,palavras[di]
		cmp al,'.'
		je fimPal	
		inc di
		goto_xy posX, posY
	
		mov	ah, 02h					; IMPRIME caracter da posição no canto
		mov	dl, al	
		int	21H	

		dec POSy
		inc posX
		inc posX
		
		jmp escreve_cima_drt	

fimPal:
		inc di
		cmp palavras[di],'$'
		jne fimPal
		
		inc di
		inc di
		inc di

		inc cl
		cmp cl, 8
		jne ciclo

SAI:	
		
		pop di
		pop si
		pop cx
		pop bx
		pop ax

		ret

Imp_Palavras endp

Imp_Cores proc
		;Imprimir no tabuleiro

		push ax
		push bx
		push si
		push di
		push cx
		push dx
		

		xor ax, ax
		xor bx, bx
		xor si, si
		xor di, di

		mov bl, contador
		mov al, 8
		mul bl
		mov si, ax

		mov bl, contador
		mov al, 20
		mul bl
		mov di, ax

		mov al, palavraspos[si]
		mov direccao, al
		inc si
		inc si
		
		mov al, palavraspos[si]
		inc si
		sub al,30h
		mov bl,10
		mul bl
		mov ah, palavraspos[si]
		sub ah,30h
		add al,ah

		; ajustar ao tabuleiro
		mov bl, 2
		mul bl

		mov POSx,al
		add POSx, 4
		; primeira posicao está em al

		inc si
		inc si
		mov al, palavraspos[si]
		inc si
		sub al,30h
		mov bl,10
		mul bl
		mov ah, palavraspos[si]
		sub ah,30h
		add al,ah
		mov POSy,al
		add POSy, 2
		inc si
		inc si

		; escrever  na grelha

drt:
		cmp direccao, '1'
		jne esq

escreve_drt:
		mov al,palavras[di]
		cmp al,'.'
		je sai_drt	
		inc di
		
		COLORING posX, posy

		inc POSx
		inc posX
		
		jmp escreve_drt

esq:
		cmp direccao, '2'
		jne cima

escreve_esq:

		mov al,palavras[di]
		cmp al,'.'
		je sai_esq	
		inc di
		
		COLORING posx, posy

		dec posX
		dec posX
		
		jmp escreve_esq


cima:
		cmp direccao, '3'
		jne baixo

escreve_cima:

		mov al,palavras[di]
		cmp al,'.'
		je sai_cima	
		inc di
		
		COLORING posx, posy


		dec POSy
		
		jmp escreve_cima

baixo:
		cmp direccao, '4'
		jne baixo_drt

escreve_baixo:

		mov al,palavras[di]
		cmp al,'.'
		je sai_baixo	
		inc di
		
		COLORING posx, posy


		inc POSy
		
		jmp escreve_baixo

baixo_drt:
		cmp direccao, '5'
		jne baixo_esq

escreve_baixo_drt:

		mov al,palavras[di]
		cmp al,'.'
		je sai_baixo_drt	
		inc di

		COLORING posx, posy

		inc POSy
		inc posX
		inc posX
		
		jmp escreve_baixo_drt	

baixo_esq:
		cmp direccao, '6'
		jne cima_esq

escreve_baixo_esq:

		mov al,palavras[di]
		cmp al,'.'
		je sai_baixo_esq	
		inc di
		
		COLORING posx, posy

		inc POSy
		dec posX
		dec posX
		
		jmp escreve_baixo_esq		

cima_esq:
		cmp direccao, '7'
		jne cima_drt

escreve_cima_esq:

		mov al,palavras[di]
		cmp al,'.'
		je sai_cima_esq	
		inc di
		
		COLORING posx, posy

		dec POSy
		dec posX
		dec posX
		
		jmp escreve_cima_esq	

cima_drt:
		cmp direccao, '8'
		jne sai

escreve_cima_drt:

		mov al,palavras[di]
		cmp al,'.'
		je sai_cima_drt	
		inc di
		
		COLORING posx, posy

		dec POSy
		inc posX
		inc posX
		
		jmp escreve_cima_drt	


sai_drt:
	dec posx
	dec posx
	jmp sai

sai_esq:
	inc posx
	inc posx
	jmp sai

sai_baixo:
	dec posy
	dec posy
	jmp sai

sai_cima:
	inc posy
	inc posy
	jmp sai

sai_baixo_drt:
	dec posy
	dec posx
	dec posx
	jmp sai

sai_baixo_esq:
	dec posy
	inc posx
	inc posx
	jmp sai

sai_cima_drt:
		inc POSy
		dec posX
		dec posX
	jmp sai

sai_cima_esq:
		inc POSy
		inc posX
		inc posX
	jmp sai

SAI:	
		pop dx
		pop cx
		pop di
		pop si
		pop bx
		pop ax
		ret

Imp_Cores endp


CalcAleat proc near

		sub		sp,2
		push	bp
		mov		bp,sp
		push	ax
		push	cx
		push	dx	
		mov		ax,[bp+4]
		mov		[bp+2],ax

		mov		ah,00h
		int		1ah

		add		dx,ultimo_num_aleat
		add		cx,dx	
		mov		ax,65521
		push	dx
		mul		cx
		pop		dx
		xchg	dl,dh
		add		dx,32749
		add		dx,ax

		mov		ultimo_num_aleat,dx

		mov		[BP+4],dx

		pop		dx
		pop		cx
		pop		ax
		pop		bp
		ret
CalcAleat endp

Compara_Palavras proc
inicio:
		push di
		push bx
		push cx

		xor di, di
		xor bx, bx
		
		xor cx, cx
		mov cl, 15

compara:
		
		mov bl, STR2BLD[di]
		mov bh,	STR2cmp[di]

		cmp bh, '.'
		je msg

		inc di
		cmp bl, bh
		je compara

		cmp bl, 0
		jne inc_tent

		jmp fim

inc_tent:
		inc conta_tentativas
		jmp fim		

msg: 
		goto_xy 2,19
		MOSTRA msg1
		xor di, di
		
reset:
		mov STR2BLD[di],  0
		mov STR2cmp[di],  0
		
		inc di
		loop reset
		call Imp_Cores
		inc contador

		mov al, contador
		add al, 30h
		goto_xy 45, 12
		mov		ah, 02h					; IMPRIME caracter da posição no canto
		mov		dl, al	
		int		21H	

fim:	
		mov al, conta_tentativas
		add al, 30h
		goto_xy 44, 13
		mov		ah, 02h					; IMPRIME caracter da posição no canto
		mov		dl, al	
		int		21H	

		pop cx
		pop bx
		pop di

		ret
Compara_Palavras endp

Copia_Palavra proc

inicio:
		push ax
		push bx
		push di
		push si

		xor ax, ax
		xor bx, bx
		xor di, di
		xor si, si

		mov al, 20
		mov bl, contador
		mul bl

		mov di, ax

copia:		
		mov al, palavras[di]
		mov STR2cmp[si], al
		inc di
		inc si
		cmp al, '.'
		jne copia

fim:	pop si
		pop di
		pop bx
		pop ax
		ret
Copia_Palavra endp

Copiar_Top10	PROC
		;abre ficheiro
        mov     ah,3dh
        mov     al,0
		mov		di,0
        int     21h
        jc      erro_abrir
        mov     HandleFich,ax
        jmp     ler_ciclo

erro_abrir:
        mov     ah,09h
        lea     dx,Erro_Open
        int     21h
        jmp     sai

ler_ciclo:
        mov     ah,3fh
        mov     bx,HandleFich
        mov     cx,1
        lea     dx,car_fich
        int     21h
		jc		erro_ler
		cmp		ax,0		
		je		fecha_ficheiro
		mov		dl, car_fich
		mov		top10Temp[di],dl
		inc		di
		jmp		ler_ciclo

erro_ler:
        mov     ah,09h
        lea     dx,Erro_Ler_Msg
        int     21h

fecha_ficheiro:
        mov     ah,3eh
        mov     bx,HandleFich
        int     21h
        jnc     sai

        mov     ah,09h
        lea     dx,Erro_Close
        Int     21h

sai:	
		MOSTRA top10Temp
		ret
		
Copiar_Top10	endp

Compara_Top10  PROC
		mov CX,40				; Numero de vezes que o loop ira ocorrer
		xor si,si				; Limpar vars antes da iniciacao do loop
		xor di,di
		xor bl, bl				; Var aux
		
ONREPEAT:
		mov AL,top10Temp[DI] 		
		mov DL,STR5[SI]
		cmp DL,AL				; Compara elementos do TOP10 e STR5
		JL	LUCKY				; Se um elemento for menor e porque o utilizador teve tempo melhor do que um dos utilizadores 
		inc SI					; Inc das duas vars 
		inc DI
		cmp DI, 80				; Caso DI == 143 e porque o TOP10 ja foi comparado na sua totalidade logo utilizador fez um tempo que o nao classifica 
		JE  FINISHNOLUCK
		cmp SI,4				; Caso SI == 8 e porque o rank atual tem melhor tempo que o utilizador por isso tem de se reniciar o ciclo
		JE  RESETONREPEAT
		loop ONREPEAT
		
RESETONREPEAT:
		mov SI,0			; Volta ao inicio da STR10
		add DI,4			; Passa para o proximo elemento
		inc BL				; inc a nossa variavel aux
		jmp ONREPEAT

FINISHNOLUCK:
		call APAGA_ECRAN		; Utilizador nao entrou no top10,logo da display da mensagem do seu tempo assim como aviso que pode dequalquer das maneiras ver o top10 apartir do menu		
		ret
		
LUCKY:
		mov auxVar,BL		; Guarda-se var aux noutra var aux 
		
		call APAGA_ECRAN
		GOTO_XY 0,0
		
		mov BL,auxVar			; Repoe var aux	
		mov CX,4				; Indica numero de vezes que ocorre o loop
		mov DI,0
		
INPUT:					; Pede ao utilizador as suas iniciais
       	mov AH,1
       	int 21H
		mov nomeplayerTEXT[DI],AL
		inc DI
		loop INPUT

		mov SI,0
		mov DI,0

CALCULA_POS:
		mov 	AL,10			; Numero de elementos a saltar
		mul     BL
		
		mov 	AH,0
		
		mov		DI,AX
		mov 	SI,0
		
		mov 	CX,4			; Numero de elementos a copiar
			
loopARINO:
		mov 	AL,STR5[SI]		; Copia tempo do utilizador para TOP10
		mov 	top10Temp[DI],AL
		inc 	SI
		inc 	DI
		
		mov 	DL,30H
		mov 	AH,02
		int 	21H
		loop	loopARINO
				
loopARINOFASE1:
		mov 	top10Temp[DI],' '
		inc 	DI
		mov		SI,0
		mov 	CX, 4
loopFASE1:	
		mov 	AL,nomeplayerTEXT[SI]			; Copia iniciais
		mov		top10Temp[DI],AL	
		inc		DI
		inc 	SI
		loop	loopFASE1
		jmp 	FIMDOcmpTOP10
			
			
FIMDOcmpTOP10:
		call	GRAVATOP10			; Chama o gravar TOP10
		ret

Compara_Top10 ENDP

GRAVATOP10  PROC
;Apaga velho TOP.txt
		lea DX,top10fich
		mov AH,41H
		int 21H

;Cria novo TOP10
		mov  AH, 3CH
		mov  CX, 0
		lea dx, top10fich
		int  21H
;Guardar handle
		mov  HANDLEFICH, AX
;Escrever Ficheiro
		mov  AH, 40H
		mov  BX, HANDLEFICH
		mov  CX, 80  
		lea dx, top10Temp
		int  21H
;Fecha Ficheiro.
		mov  AH, 3EH
		mov  BX, HANDLEFICH
		int  21H
FIMGRAVATOP10:
		ret	
GRAVATOP10 ENDP

cseg	ends
end     Main           

