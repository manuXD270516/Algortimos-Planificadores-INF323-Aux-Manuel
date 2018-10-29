import java.util.Random;
import PCB;
import sun.net.www.content.text.plain;

public class Cola{

    private PCB[] colaLIFO;
    private static final int Max=100; // cantidad de elementos maximos supuestos = 100 elementos
    private int N; // cantidad actual de elementos

    // atributos extras
    public int c=new Random().nextInt(10)+1; // valor entre 1 y 10 (variable)

    public Cola(){
        this.N=0;
        colaLIFO=new PCB[Max]; // cantidad de procesos supuestamente
    }

    public void meter(PCB proceso){
        Q[N]=proceso;
        N++;
    }

    public PCB sacar(){ // saca del inicio
        if (this.N>0) {
            PCB proc=Q[0];
            for (int i = 0; i < N; i++) {
                colaLIFO[i]=colaLIFO[i+1];    
            }
            N--;
            colaLIFO[N]=null;
            return proc;
        } 
        return null;
    }

     // metodos extras para el ejercicio 3 Examen 1er parcial sa 1/2018 (NO ES NECESARIO CODIFICAR EN EL EXAMEN :D)
    public void meterFrente(PCB p){
        for (int i = N; i >= 1; i++) {
            colaLIFO[i]=colaLIFO[i-1];    
        }
        colaLIFO[0]=p;
        N++;
    }

    public void meterFinal(PCB p){
        meter(p);
    }

    public PCB sacarFrente(){
        return sacar();
    }

    public PCB sacarFinal(){
        if (this.N>0){
            N--;
            PCB p=colaLIFO[N];
            colaLIFO[N]=null;
            return p;
        }
        return null;
    }

}