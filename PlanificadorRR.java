
import Cola.java;
import PCB.java;
import CPU.java;
/*
 CODIGO DE AYUDA DE LOS PLANIFICADORES
 ROUND ROBIN Y COLAS DE PRIORIDAD

*/

class PlanficadorRR{

    // variables globales (solo de lectura)
    int N=100; // 100 procesos en teoria como maximo
    Cola Q=new Cola(); // cola Q
    PCB PRUN=new PCB();

    // PCB's guardados en un vector (mesa examinadora sem 5-2016)
    PCB[] V=new PCB[N];

    // EXAMEN PRIMER PARCIAL SA (1/2018) PREGUNTA 3
    // DEQUE (COLA MODIFICADA)
    boolean encolarNormal=true; int sucesion=0;
    void planificador_1parcial_SA_1_2018_q3(){
        if (Finalizo(PRUN)){
            FreeMem(PRUN);
        } else {
            PRUN.Reg=CPU.Reg;
            if (encolarNormal){
                Q.meterFinal(PRUN); 
            } else {
                Q.meterFrente(PRUN);
            }
        } 
        sucesion++;
        if (encolarNormal){
            PRUN=Q.sacarFrente();
        } else {
            PRUN=Q.sacarFinal();
        }
        if (sucesion%2==0){
            encolarNormal=!encolarNormal;
        }
        CPU.Reg=PRUN.Reg;
    }

    // PLANIFICADOR QUE ASIGNA QUANTUMS DE ACUERDO A LA SUCESION 1,0,1,0
    boolean darQ=true;
    void Planficador_sucesion_101010_(){
        if (Finalizo(PRUN)){
            FreeMem(PRUN);
        } else {
            PRUN.Reg=CPU.Reg;
            Q.meter(PRUN);
        }
        PRUN=Q.sacar();
        if (!darQ){
            Q.meter(PRUN);
            PRUN=Q.sacar();
        } 
        darQ=!darQ; // cambiar al sgte termino de la sucesion
        CPU.Reg=PRUN.Reg;

    }
    
    // EXAMEN DE AYUDANTIA (2018) PREGUNTA 3
    // estado de ready si ya estuvo 3 veces en wait
    void planificador_testAux_2018_q3(){
        PRUN.Reg=CPU.Reg;
        Q.meter(PRUN);
        PRUN=getPCB();
        CPU.Reg=PRUN.Reg;
    }

    PCB getPCB(){
        PCB p=Q.sacar();
        p.waitCount++;
        while (p.waitCount<=3){
            Q.meter(p);
            p=Q.sacar();
            p.waitCount++;
        }
        return p;
    }
    
    // EXAMEN PRIMER PARCIAL SC (2/2016) pregunta 2
    int cont=0;
    void planificador_1parcial_Sc_2_2016_q2(){
        cont++;
        if (cont==PRUN.c){
            PRUN.c--;
            PRUN.Reg=CPU.Reg;
            if (PRUN.c==0){
                FreeMem(PRUN);
            } else {
                Q.meter(PRUN);
            }
            PRUN=Q.sacar();
            cont=0;
            CPU.Reg=PRUN.Reg;
        }
    }

    // EXAMEN MESA EXAMINADORA SM (5/2016) pregunta 1
    int cont=0; 
    int k=0; // indice de posiciones en el vector de procesos
    void planificador_mesa_SM_5_2016_q1(){
        cont++;
        if (cont==getQByVectorPCB(k)||PRUN.PID==-1){
            if (PRUN.PID==-1){
                FreeMem(PRUN);
            } else {
                PRUN.Reg=CPU.Reg;
            }
            PRUN=V[k];
            k=nextPos(k);
            cont=0;
            CPU.Reg=PRUN.Reg;
        }
    }

    int getQByVectorPCB(int i){
        if (i%2==0){
            return 2;
        }
        return 1;
    }
}