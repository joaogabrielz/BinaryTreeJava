/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arvorebinary;

public class tipoNo {
    
    private int valor;
    
    private tipoNo esq;
    private tipoNo dir;
    
    tipoNo(){
        this.esq = null;
        this.dir = null;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public tipoNo getEsq() {
        return esq;
    }

    public void setEsq(tipoNo esq) {
        this.esq = esq;
    }

    public tipoNo getDir() {
        return dir;
    }

    public void setDir(tipoNo dir) {
        this.dir = dir;
    }
    
    
}
