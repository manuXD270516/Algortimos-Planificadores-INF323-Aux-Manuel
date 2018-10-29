import Cola.java;

public class PlanficadorCP{
   
    int N=10; // cantidad de colas (ejemplo el valor de 10, puede ser cualquier otro)
    Cola[] Q=new Cola[N]; // 10 colas supuestamente, con 100 procesos cada una
    PCB PRUN=new PCB();

    // EXAMEN FINAL INF323 SA (1/2018) pregunta 3
    int k=1;
    void planificador_final_SA_1_2018_q3(){
        PRUN.Reg=CPU.Reg;
        int i=PRUN.Prioirdad;
        i=getColaAdecuada(i);
        PRUN.Prioirdad=i;
        Q[i].meter(PRUN);
        PRUN=Q[k].sacar();
        Q[k].c--;
        k=nextCola_1(k);
        CPU.Reg=PRUN.Reg;
    }

    int getColaAdecuada(int i){
        if (Q[i].c>0){
            return i;
        } else {
            return nextCola_1(i);
        }
    }

    int nextCola_1(int x){
        if (x==N){
            return 1;
        } else {
            return x+1;
        }
    }

    // EXAMEN FINAL SA (2/2017) pregunta 2
    int k=1;
    void planificador_final_SA_2_2017_q2(){
        PRUN.Reg=CPU.Reg;
        int i=encolar(PRUN.Prioirdad);
        PRUN.Prioirdad=i;
        Q[i].meter(PRUN);
        PRUN=Q[k].sacar();
        k=nextCola_1(x);
        CPu.Reg=PRUN.Reg;
    } 

    int encolar(int x){
        if (x==1){
            return N;
        } else if (x==N){
            return 1;
        } else {
            return x;
        }
    }

    // EXAMEN PRIMER PARCIAL SA (1/2018) pregunta 2
    int k=N;
    void planificador_1parcial_SA_1_2018_q2(){
        if (PRUN.Prioirdad==1){
            FreeMem(PRUN);
        } else {
            PRUN.Reg=CPU.Reg;
            int i=encolar_V2(PRUN.Prioirdad);
            PRUN.Prioirdad=i;
            Q[i].meter(PRUN);
        }
        PRUN=Q[k].sacar();
        k=nextCola_2(k);
        CPU.Reg=PRUN.Reg;
    }

    int encolar_V2(int i){
        return nextCola_2(i);
    }
    int nextCola_2(int x){
        if (x==1){
            return 1;
        } 
        return x-1;
    }


    // EXAMEN PRIMER PARCIAL SC (1/2017) PREGUNTA 2
    int V[]=new int[N]; // vector de N enteros ya cargado
    int k=1;
    int cqp=0; // q's por proceso
    int cqc=0; // q's por cola
    void planificador_1parcial_Sc_1_2017_q2(){
        cqp++;
        if (cqp==getQuantum(PRUN.PID) || Finalizo(PRUN)){
            if (finalizo(PRUN)){
                FreeMem(PRUN);
            } else {
                PRUN.Reg=CPU.Reg;
                int i=PRUN.Prioirdad;
                Q[i].meter(PRUN);
            }
            PRUN=Q[k].sacar();
            cqc++;
            if (cqc==V[k]){
                k=nextCola_1(k);
            }
            cqp=0;
            CPU.Reg=PRUN.Reg;
        }
    }
    int getQuantum(int pid){
        if (pid%2==0){
            return V[pid];
        }
        return 1;
    }

    // EXAMEN PRIMER PARCIAL SC (1/2017) PREGUNTA 3
    int k=1;
    int suc1=1; int suc2=3;
    int cqp=0; // q's por proceso
    void planificador_1parcial_SA_1_2017_q3(){
        cqp++;
        if (cqp==getQBySucesion(PRUN.Prioirdad)){
            PRUN.Reg=CPU.Reg;
            int i=PRUN.Prioirdad;
            Q[i].meter(PRUN);
            PRUN=Q[k].sacar();
            k=3-k; // altera cola 1,2,1,2,1,2,.. asi sucesivamente
            cqp=0;
            CPU.Reg=PRUN.Reg;
        }
    }

    int getQBySucesion(int i){
        int sucAct;
        if (i%2==0){ // cola 2
            sucAct=suc2;
            if (suc2==1){
                suc2=3;    
            } else {
                suc2--;
            }
        } else { // cola 1
            sucAct=suc1;
            suc1=(suc1%3)+1;
        }
        return sucAct;
    }


    // EXAMEN DE AYUDANTIA (2018) PREGUNTA 4
    int k=1;
    int cqp=0;
    void planificador_testAux_2018_q4(){
        cqp++;
        if (cqp==getQbyPriori(PRUN.Prioirdad)||Finalizo(PRUN)){
            if (Finalizo(PRUN)){
                FreeMem(PRUN);
            } else {
                PRUN.Reg=CPU.Reg;
                int i=PRUN.Prioirdad;
                Q[nextCola_1(i)].meter(PRUN);
            }
            PRUN=Q[k].sacar();
            k=nextCola_1(k);
            cqp=0;
            CPU.Reg=PRUN.Reg;
        }
    }

    int getQbyPriori(int i){
        if (i==k){
            return 2;
        }
        return 1;
    }

    // EXAMEN
}