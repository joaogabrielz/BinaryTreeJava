/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arvorebinary;


public class Arvore {
    
    private tipoNo raiz;
    
    private int altura;

    public Arvore(){
        this.raiz = null;
        this.altura = 0;
    }
    
    public tipoNo getRaiz() {
        return raiz;
    }

    public void setRaiz(tipoNo raiz) {
        this.raiz = raiz;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    
    public void add(int valor){
        
        tipoNo novo = new tipoNo();
        novo.setValor(valor);
        
        if(this.raiz == null){
            this.raiz = novo;
        }
        else{           
          tipoNo aux = this.raiz;          
          
          while(true){
              if(novo.getValor() < aux.getValor()){ // novo < aux
                  if(aux.getEsq() != null){
                      aux = aux.getEsq();
                  }
                  else{
                      aux.setEsq(novo);
                      break;
                  }
              }
              else{ // > or =
                  if(aux.getDir() != null){
                      aux = aux.getDir();
                  }
                  else{
                      aux.setDir(novo);
                      break;
                  }
              }            
          } //adicionando ou na esq ou na dir break;    
          
       }     
             
        System.out.println("Valor [ " + novo.getValor() + " ] inserido..");
    }
    
    
    public void inOrdem(tipoNo aux){
        
        if(aux != null){
            inOrdem(aux.getEsq()); //aux q é a esq agr imprime toda ate ser nula
            System.out.println(aux.getValor());
            inOrdem(aux.getDir());
        }
    }
    
    public void preOrdem(tipoNo aux){
        
        if(aux != null){
            System.out.println(aux.getValor());
            preOrdem(aux.getEsq());    
            preOrdem(aux.getDir());
        }     
        
    }
    
    public void posOrdem(tipoNo aux){
        
        if(aux != null){          
            posOrdem(aux.getEsq());    
            posOrdem(aux.getDir());
            System.out.println(aux.getValor());
        }

    }
    
    
    
    public int altura(tipoNo auxRaiz){
    //caminho mais longo entre raiz e folha
    //maior distancia q existe entre raiz e folha
     
      if(auxRaiz == null){
          return -1; // 0 or -1
      }
      else{
        int esq = altura(auxRaiz.getEsq()); //altura sub arvore esq
        int dir = altura(auxRaiz.getDir()); // altura sub arvore dir
        
        if(esq > dir){
            return esq + 1; //se esq 
        }
        else{
            return dir + 1;
        }      
      }     
    }
    
    
    public void remove(int valor){
              
      if(this.raiz == null) {
          System.out.println("\n Arvore Vazia! \n");
          return;
      }  
      
        tipoNo aux = this.raiz;
        tipoNo paiAux = null; 
        
     //  Busca valor na arvore 
     
        while(aux != null){
            
            if(aux.getValor() == valor){ // achou valor sai do loop
                break;
            }
            else if(valor < aux.getValor()){ //enquanto nao achou caminha ate ele
                paiAux = aux;
                aux = aux.getEsq();
            }
            else{ 
                paiAux = aux;
                aux = aux.getDir();
            }           
        } 
        
         /*
            Ao sair do loop       
            aux tem ref o valor que eu digitei
            PaiAux pai do aux
            Se esta removendo raiz, aux == valor e paiAux == null
            ou seja:
            aux tem ref ao valor a ser eliminado
            paiAux tem ref ao No pai de aux
            e se aux for a raiz, paiAux vai ser nulo
         */
        
        if(aux != null){ // se valor existe
             
            
         // Case Aux sem filhos, é folha remove
         
          if(aux.getEsq() == null && aux.getDir() == null){ 
                
             // System.out.println("\n >> case Sem filhos\n");

              if(paiAux != null){ //se nao é a raiz

                  if(aux.getValor() < paiAux.getValor()){ 
                      paiAux.setEsq(null);   //faz o pai setar o filho nulo         
                  }
                  else{ 
                      paiAux.setDir(null); 
                  }
              }

              else{ // se valor é a raiz
                  this.raiz = null;
              }
         }
          
          
          
        // case Aux tem filho a Esquerda, sem filho a direita
          
         else if(aux.getDir() == null){
             
            // System.out.println("\n >> case Filho a esq \n");
             
              if(paiAux == null){           // se é raiz
                  this.raiz = aux.getEsq();
              }
              else if(paiAux.getEsq() == aux){ // se aux é filho a esq do paiAux
                  paiAux.setEsq(aux.getEsq());
              }
              else{                         // se aux é filho a Dir do paiAux
                  paiAux.setDir(aux.getEsq());
              }
         }
        
         
         
         // case Aux tem filho a Direita, sem filho a Esq
         
         else if(aux.getEsq() == null){
             
            // System.out.println("\n >> case Filho a dir \n");
             
              if(paiAux == null){           // se é raiz
                  this.raiz = aux.getDir();
              }
              else if(paiAux.getEsq() == aux){ // se aux é filho a esq do paiAux
                  paiAux.setEsq(aux.getDir());
              }
              else{                         // se aux é filho a Dir do paiAux
                  paiAux.setDir(aux.getDir());
              }
         }
          
          
         // se possui mais de um filho, é avo ou outro grau maior de parentesco
         
         else{
           // System.out.println("\n >> case 2 filhos \n"); 
            
            //No mais a esquerda da SubArvore Direita do No que quer remover
            tipoNo sucessor = menorSubArvDir(aux);
            
            if(paiAux == null){
                this.raiz = sucessor;
            }
            else if(paiAux.getEsq() == aux){
                paiAux.setEsq(sucessor);
            }
            else{
                paiAux.setDir(sucessor);
            }
            
            //ponteiro Esq do sucessor recebe os filhos a esq do Aux 
            sucessor.setEsq(aux.getEsq());
                                 
         }
          
          
         }                                      
        else{
            System.out.println("\n Elemento não esta na arvore");
        }
    }
    
    
    
 // O sucessor é o Nó mais a esquerda da subarvore a direita do No que foi passado como parametro do metodo
    
    public tipoNo menorSubArvDir(tipoNo auxRef){ // referencia ao aux que é o valor q quer remover
        
        tipoNo paiSucessor = auxRef;
        tipoNo sucessor = auxRef;
        tipoNo atual = auxRef.getDir(); //vai Sub arvore Direita
        
        
        while(atual != null){ //ent nao chegar no No mais a esquerda
            paiSucessor = sucessor;
            sucessor = atual;
            atual = atual.getEsq(); // vai pra esq
        }
        
        /*
         agora sucessor será o No mais a Esq da subArvore a direita
         paiSucessor sera o pai do sucessor
         auxRef é o No q deve ser eliminado       
        */
        
        // se sucessor nao é o filho a direita do Nó que deverá ser eliminado
        if(sucessor != auxRef.getDir()){ 
                 
            // pai herda os filhos do sucessor que sempre serão a direita
            paiSucessor.setEsq(sucessor.getDir());          
            
            /*
            Sucessor nuncapode ter filhos a esq
            ele sempre será o no mais a esquerda da subArvore Direita do No que quer apagar
            e sucessor sempre sera o filho a Esquerda do PaiSucessor
            */
                    
             // Guarda referencia a direita do sucessor
            // p quando eleasssumir a posicao correta da arvore
            sucessor.setDir(auxRef.getDir());    
        }    
        
        return sucessor;
    }
    
    
    public void busca(int elemento){
        
        tipoNo aux = this.raiz;
        boolean achou = false;
        
        while(aux != null){
            
            if(elemento  == aux.getValor()){
            System.out.println("\nEl: [ "+ elemento + " ] foi encontrado..");
            achou = true;
            break;
            }
            else if(elemento < aux.getValor()){// valopr < q aux(cabeca) // ando..
                aux = aux.getEsq();
            }
            else{ // maior
                aux = aux.getDir();
            }
        }
        
        if(!achou)
            System.out.println("\nElemento: [ "+ elemento + " ] Não encontrado!");
     
    }
    
    
    public void menu(){
        int opcao=0,value;

	System.out.print("\n------------ Menu ------------\n");
	while(opcao!=-1){
            opcao = Input.readInt(
                    "\nDigite a opcao: "
                    + "\n(1) Inserir"
                    + "\n(2) Remover"
                    + "\n(3) Buscar"
                    + "\n(4) Imprimir In-Ordem"
                    + "\n(5) Imprimir Pré-Ordem"
                    + "\n(6) Imprimir Pós-Ordem"
                    + "\n(7) Altura da Arvore"     
                    + "\n(-1) Sair.. "
                    + "\nOpcao: ");
            
            switch(opcao){
			
                case 1: 
                System.out.println("\ninserir valores?[0] ou Arvore Manual[1]: ");
                int opt = Input.readInt();
                   switch(opt){
                       case 0:
                        System.out.print("Valor: ");
                        value = Input.readInt();
                        System.out.println("");
                        add(value);
                       break;
                       default:                            
                        add(12);                  
                        add(7);
                        add(3);
                        add(9);
                        add(8);
                        add(11);
                        add(15);              
                        add(14);
                        add(16);
                       break;
                     
                   }                                              
		break;
                
                case 2:
                    System.out.print("\nValor a remover: ");
                    value = Input.readInt();
                    remove(value);
                break;
                
                case 3:
                    System.out.print("\nValor para buscar: ");
                    value = Input.readInt();
                    busca(value);
                break;
                
                case 4:
                    inOrdem(this.getRaiz());
                break;
                
                case 5:
                    preOrdem(this.getRaiz());
                break;
                
                case 6:
                    posOrdem(this.getRaiz());
                break;
                
                case 7:
                    int res = altura(this.getRaiz());
                    this.altura = res;
                    
                    if(this.raiz != null){
                        if(this.altura != 0){
                          System.out.println("\n Altura da arvore: " + this.getAltura());
                        } 
                        else{
                          System.out.println("\n Altura = 0");
                        }
                    }
                    else{
                    System.out.println("\n Arvore Vazia!");
                    }
                    
                break;
                
                case -1: //sair
                    System.out.println("\nSaindo...!");
		break;
				
		default:
                    System.out.println("\nOpcao invalida!");
		break;
            }
        }
    }
}

