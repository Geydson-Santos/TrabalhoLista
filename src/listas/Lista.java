package listas;

//definicao da classe List
public class Lista {
	private ListaNo primeiroNo;// Declara variavel de instancia com o nome de
								// PrimeiroN�
	private ListaNo ultimoNo;// Declara variavel de instancia com o nome de
								// UltimoN�
	private String nome; // String como 'lista' usada na impressao
	
	private int tamanhoLista;

	// construtor cria List vazia com 'list' como o nome
	public Lista() {
		this("Lista");
		this.tamanhoLista = 0;
	}// fim do construtor sem argumentos List
		
	// construtor cria uma List vazia com um nome
	public Lista(String nomeDaLista) {
		nome = nomeDaLista;
		primeiroNo = ultimoNo = null;
		this.tamanhoLista = 0;
	}// fim do construtor de um argumento List
		// Insere Object na frente de List

	public void insereNoInicio(Object insertItem) {
		if (estaVazia()) { // first e lastNode referenciam o mesmo objeto
			primeiroNo = ultimoNo = new ListaNo(insertItem);
		} else {
			primeiroNo = new ListaNo(insertItem, primeiroNo);
		}// fim do m�todo insereNoInicio
		
		this.tamanhoLista++;
	}// fim do m�todo insereNoInicio
		
	// insere Object no fim de Lista
	public void insereNoFim(Object insertItem) {
		if (estaVazia()) {
			primeiroNo = ultimoNo = new ListaNo(insertItem);
		} else {
			ultimoNo = ultimoNo.proximoNo = new ListaNo(insertItem);
		}
		this.tamanhoLista++;
	}// fim do m�todo insereNoFim
	
	
	//Insere na posi��o informada
	public void insertAtPosicao(int posicao, Object elemento) {
		if(posicao > 0){
                    if(posicao == 1) insereNoInicio(elemento);
                    else{
                        if(posicao == (tamanhoLista + 1)) insereNoFim(elemento);
                        else{
                            if(posicao < (tamanhoLista + 1)){
                                ListaNo current = primeiroNo;
                                for(int i = 1; i < (posicao - 1); i++){
                                    current = current.proximoNo;
                                }
                                current.proximoNo = new ListaNo(elemento, current.proximoNo);
                                tamanhoLista++;
                            }
                        }
                    }
                }
		
	}
	
	// remove o primeiro n� de List
	public Object removeNoInicio() throws EmptyListException {
		if (estaVazia()) {
			throw new EmptyListException(nome);
		}
		Object removedItem = primeiroNo.data; // recupera dados sendo removidos
		// atualiza refer�ncias primeiroNo e ultimoNo
		if (primeiroNo == ultimoNo) {
			primeiroNo = ultimoNo = null;
		} else {
			primeiroNo = primeiroNo.proximoNo;
		}
		this.tamanhoLista--;
		return removedItem;// retorna dados de n� removidas
	}// fim do m�todo removeNoFim

	public Object removeNoFim() throws EmptyListException{
		if (estaVazia()){
			throw new EmptyListException(nome); //lan�a excess�o se List estiver vazia
		} 
		
		Object removedItem = ultimoNo.data; //recupera dados sendo removidos
		
		//atualiza refer�ncias primeiroNo e ultimoNo
		
		if(primeiroNo == ultimoNo){
			primeiroNo = ultimoNo = null;
		}
		
		else{ //localiza o novo �ltimo n�
			ListaNo current = primeiroNo;
		
			//faz loop enquanto n� atual n�o referencia lastNode
			while(current.proximoNo != ultimoNo){
				current = current.proximoNo;
			} 
			
			ultimoNo =	current; //atual �	novo lastNode
			current.proximoNo = null;
		}//fim do else
		
		this.tamanhoLista--;
		return removedItem; //retorna dados de n� removidos
	}//fim do m�todo removeNoFim
	

	public Object removeFromPosicao(int posicao) throws EmptyListException {
            if (estaVazia()){
                throw new EmptyListException(nome); //lan�a excess�o se List estiver vazia
            }
            
            Object removedItem = null;
            
            if(posicao > 0){
                    if(posicao == 1) removedItem = removeNoInicio();
                    else{
                        if(posicao == tamanhoLista) removedItem = removeNoFim();
                        else{
                            if(posicao < tamanhoLista){
                                ListaNo current = primeiroNo;
                                for(int i = 1; i < (posicao - 1); i++){
                                    current = current.proximoNo;
                                }
                                removedItem = current.data;
                                
                                current.proximoNo = current.proximoNo.proximoNo;
                                tamanhoLista--;
                            }
                        }
                    }
                }

            return removedItem;
	}
        
        public Object removeFromValor(Object elemento) throws EmptyListException {
            if (estaVazia()){
                throw new EmptyListException(nome); //lan�a excess�o se List estiver vazia
            }
            
            ListaNo current = primeiroNo; 
            while(current.proximoNo.data != elemento && current.proximoNo.proximoNo != null){
                current = current.proximoNo;
            }
            if(current.proximoNo.data == elemento){
                current.proximoNo = current.proximoNo.proximoNo;
                tamanhoLista--;
                return elemento;
            }
            
            return null;
        }
	
	
	public boolean buscaElemento(Object elemento) throws EmptyListException {
	    if (estaVazia()){
                throw new EmptyListException(nome); //lan�a excess�o se List estiver vazia
            }
            
            ListaNo current = primeiroNo; 
            while(current.data != elemento && current.proximoNo != null){
                current = current.proximoNo;
            }
            if(current.data == elemento) return true;
            
            return false;
	}
	
	
	// Determina se a lista estiver vazia
	public boolean estaVazia() {
		return primeiroNo == null; // retorna true se List estiver vazia
	}// fim do m�todo estaVazia
		// gera sa�da do conte�do de List

	public void print() {
		if (estaVazia()) {
			System.out.printf("Lista Vazia %s\n", nome);
			System.out.println(imprimeTamanho());
			return;
		}// fim do if
		
		System.out.printf("A lista %s �: ", nome);
		ListaNo current = primeiroNo;
		// enquanto n�o estiver no fim da lista, gera sa�da dos dados do n�
		// atual
		while (current != null) {
			System.out.printf("%s -> ", current.data);
			current = current.proximoNo;
		}// fim do While
		System.out.printf("\n");
		System.out.println(imprimeTamanho());
	} // fim do m�todo print
	
	public String imprimeTamanho(){
		return "Tamanho da Lista: "+this.tamanhoLista;
	}
}// fim da classe List
