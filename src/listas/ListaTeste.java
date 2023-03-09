package listas;

public class ListaTeste {
	public static void main(String[] args) {
		
		Lista list = new Lista(); // cria o cont�iner de List
		
		// insere inteiros na lista
		list.insereNoInicio(-1);
		list.insereNoInicio(0);
		list.insereNoFim(1);
		list.insereNoFim(5);
		list.insereNoInicio(3);
                list.print();
		
                try {
                    list.removeFromPosicao(2);
                    
                    list.print();
                    
                    list.insertAtPosicao(2, 0);
                    
                    list.print();
                    
                    System.out.println(list.buscaElemento(3));

                } catch (Exception e) {
                    e.printStackTrace();
                }
		
		
	}// fim de main
}// fim da classe ListTest